package com.example.bucard.service;

import com.example.bucard.dao.entity.BoxEntity;
import com.example.bucard.dao.entity.UserEntity;
import com.example.bucard.dao.repository.UserRepository;
import com.example.bucard.mapper.UserMapper;
import com.example.bucard.model.dto.*;
import com.example.bucard.model.exception.AlreadyExistException;
import com.example.bucard.model.exception.IncorrectOtpException;
import com.example.bucard.model.exception.NotFoundException;
import com.example.bucard.model.exception.PasswordNotCorrectException;
import com.example.bucard.util.TokenGenerator;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserService {
    private final String URL = "http://gw.soft-line.az/sendsms";

    private final UserRepository userRepository;


    private final LoadingCache<String, Integer> otpCache = CacheBuilder.newBuilder().
        expireAfterWrite(2, TimeUnit.MINUTES)

        .build(new CacheLoader<String, Integer>() {
            public Integer load(String key) {
                return 0;
            }
        });


    private final RestTemplate restTemplate = new RestTemplate();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    public UserDto getUser(Long id) {
//        log.info("ActionLog.getUser.start with id: {}", id);
//        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> {
//            log.error("ActionLog.getUser.error with id: {}", id);
//            throw new NotFoundException("USER_NOT_FOUND with id: " + id);
//        });
//        log.info("ActionLog.getUser.end with id: {}", id);
//        return UserMapper.INSTANCE.mapEntityToDto(userEntity);
//    }

    public UserDto getUser(String token){
        log.info("ActionLog.getUser.start with token: {}", token);
        UserEntity userEntity = userRepository.findByToken(token).orElseThrow(() -> {
            log.error("ActionLog.getUser.error with id: {}", token);
            throw new NotFoundException("USER_NOT_FOUND with id: " + token);
        });
        log.info("ActionLog.getUser.end with token: {}", token);
        return UserMapper.INSTANCE.mapEntityToDto(userEntity);
    }

    public void registerUser(RegisterDto registerDto) throws ExecutionException {
        log.info("ActionLog.registerUser.start");
        if (!userRepository.existsByPhone(registerDto.getPhone())) {
            UserEntity userEntity = UserMapper.INSTANCE.mapRegisterDtoToEntity(registerDto);
            userEntity.setBoxes(new ArrayList<>(List.of(
                BoxEntity.builder()
                    .emoji("😍")
                    .title("Bazaaar")
                    .user(userEntity)
                    .build(),
                BoxEntity.builder()
                    .title("Specials")
                    .emoji("😃")
                    .user(userEntity)
                    .build()
            )
            ));
            userEntity.setToken(TokenGenerator.generateToken(userEntity.getFullName()));
            userRepository.save(userEntity);
            log.info("ActionLog.registerUser.end");
        } else {
            log.error("ActionLog.registerUser.error");
            throw new AlreadyExistException("PHONE_NUMBER_ALREADY_EXIST");
        }

    }

    public void selectPlan(PlanDto planDto) {
        log.info("ActionLog.selectPlan.start");
        UserEntity userEntity = getUserIfExist(planDto.getUserId());
        userEntity.setPlan(planDto.getPlan());
        userRepository.save(userEntity);
        log.info("ActionLog.selectPlan.end");
    }

    public UserDto login(LoginDto loginDto) {
        log.info("ActionLog.login.start");
        UserEntity userEntity = userRepository.findByPhone(loginDto.getPhone())
            .orElseThrow(() -> {
                log.error("ActionLog.login.error");
                throw new NotFoundException("USER_NOT_FOUND with phone number: "
                    + loginDto.getPhone());
            });
        if (!Base64.getEncoder()
            .encodeToString(loginDto.getPassword().getBytes())
            .equals(userEntity.getPassword())) {
            throw new PasswordNotCorrectException("INCORRECT_PASSWORD");
        }
        log.info("ActionLog.login.end");
        return UserMapper.INSTANCE.mapEntityToDto(userEntity);
    }

    public void sendOtp(String phone,String fullName) throws ExecutionException {
        log.info("ActionLog.sendOtp.start");
        Random random = new Random();
        int otp = 1000 + random.nextInt(9000);
        otpCache.put(phone, otp);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String message = "Salam " + fullName + ".\n" +
            "https://www.bucard.az - hesabınızı təsdiqləmək üçün OTP: " + otp +
            "\n" +
            "Bu kodu başqaları ilə bölüşməyin !\n" +
            "Bu kod yalnız bu qeydiyyat prosesində istifadə edilə bilər !\n" +
            "\n" +
            "Təşəkkürlər. BUCARD Komandası";
        URI uri = UriComponentsBuilder.fromUriString(URL)
            .queryParam("user", "bucardazapi")
            .queryParam("password", "HtN0Wcla")
            .queryParam("gsm", phone)
            .queryParam("from", "bucard.az")
            .queryParam("text", message)
            .build().toUri();

        restTemplate.exchange(uri,
            HttpMethod.GET, entity, String.class);
        log.info("ActionLog.sendOtp.end");
    }

    public String verifyOtp(String phone, Integer otp) throws ExecutionException {
        log.info("ActionLog.verifyOtp.start");
        int cacheOtp = otpCache.get(phone);
        if (otp.equals(cacheOtp)) {
            otpCache.invalidate(phone);
            log.info("ActionLog.verifyOtp.end");
            return "success";
        } else {
            log.info("ActionLog.verifyOtp.end");
            throw new IncorrectOtpException("INCORRECT_OTP");
        }
    }

    public void deleteUsers() {
        log.info("ActionLog.deleteUsers.start");
        userRepository.deleteAll();
        log.info("ActionLog.deleteUsers.end");
    }

    private UserEntity getUserIfExist(Long id) {
        return userRepository.findById(id).orElseThrow(() -> {
            log.error("ActionLog.getUserIfExist.error with id: {}", id);
            throw new NotFoundException("USER_NOT_FOUND with id: " + id);
        });
    }
}

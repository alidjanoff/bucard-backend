package com.example.bucard.service;

import com.example.bucard.dao.entity.UserEntity;
import com.example.bucard.dao.repository.UserRepository;
import com.example.bucard.mapper.UserMapper;
import com.example.bucard.model.dto.LoginDto;
import com.example.bucard.model.dto.PlanDto;
import com.example.bucard.model.dto.RegisterDto;
import com.example.bucard.model.dto.UserDto;
import com.example.bucard.model.exception.NotFoundException;
import com.example.bucard.model.exception.PasswordNotCorrectException;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    private final JavaMailSender javaMailSender;
    private LoadingCache<String, Integer> otpCache = CacheBuilder.newBuilder().
        expireAfterWrite(4, TimeUnit.MINUTES)
        .build(new CacheLoader<String, Integer>() {
            public Integer load(String key) {
                return 0;
            }
        });

    public UserService(UserRepository userRepository, JavaMailSender javaMailSender) {
        this.userRepository = userRepository;
        this.javaMailSender = javaMailSender;
    }

    public void registerUser(RegisterDto registerDto) {
        log.info("ActionLog.registerUser.start");
        UserEntity userEntity = UserMapper.INSTANCE.mapRegisterDtoToEntity(registerDto);
        userRepository.save(userEntity);
        log.info("ActionLog.registerUser.end");
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

        return UserMapper.INSTANCE.mapEntityToDto(userEntity);
    }

    public void sendOtp(String mail) {
        log.info("ActionLog.sendOtp.start");
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        otpCache.put(mail, otp);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail);
        message.setSubject("[no-reply] Texnoera Academy");
        message.setText("" + otp);
        message.setFrom("texnoera.academy@gmail.com");
        javaMailSender.send(message);
        log.info("ActionLog.sendOtp.end");
    }

    public String verifyOtp(String mail, Integer otp) throws ExecutionException {
        log.info("ActionLog.verifyOtp.start");
        Integer cacheOtp = otpCache.get(mail);
        if (otp.equals(cacheOtp)) {
            otpCache.invalidate(mail);
            log.info("ActionLog.verifyOtp.end");
            return "success";
        } else {
            log.info("ActionLog.verifyOtp.end");
            return "fail";
        }
    }

    private UserEntity getUserIfExist(Long id) {
        return userRepository.findById(id).orElseThrow(() -> {
            log.error("ActionLog.getUserIfExist.error with id: {}", id);
            throw new NotFoundException("USER_NOT_FOUND with id: " + id);
        });
    }
}

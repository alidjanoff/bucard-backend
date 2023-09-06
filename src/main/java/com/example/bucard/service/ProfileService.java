package com.example.bucard.service;

import com.example.bucard.dao.entity.BoxEntity;
import com.example.bucard.dao.entity.ProfileEntity;
import com.example.bucard.dao.entity.UserEntity;
import com.example.bucard.dao.repository.BoxRepository;
import com.example.bucard.dao.repository.ProfileRepository;
import com.example.bucard.dao.repository.UserRepository;
import com.example.bucard.enums.UserType;
import com.example.bucard.filter.FilterSpecification;
import com.example.bucard.mapper.ProfileMapper;
import com.example.bucard.model.dto.AddToBoxDto;
import com.example.bucard.model.dto.ProfileDto;
import com.example.bucard.model.dto.ProfileResponse;
import com.example.bucard.model.dto.RemoveFromBoxDto;
import com.example.bucard.model.exception.AlreadyExistException;
import com.example.bucard.model.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final BoxRepository boxRepository;
    private final FilterSpecification<ProfileEntity> specification;

    public ProfileService(ProfileRepository profileRepository, UserRepository userRepository, BoxRepository boxRepository, FilterSpecification<ProfileEntity> specification) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
        this.boxRepository = boxRepository;
        this.specification = specification;
    }

    public Page<ProfileResponse> searchProfiles(String filter, Pageable pageable) {
        log.info("ActionLog.searchProfiles.start");
        Page<ProfileEntity> profileEntities =
            profileRepository.findAll(specification.getSpecification(filter), pageable);
        log.info("ActionLog.searchProfiles.end");
        return profileEntities.map(ProfileMapper.INSTANCE::mapEntityToDto);


    }

    public void saveProfile(ProfileDto profileDto) {
        log.info("ActionLog.saveProfile.start");
        profileRepository.save(
            ProfileMapper.INSTANCE.mapDtoToEntity(profileDto)
        );

        log.info("ActionLog.saveProfile.end");
    }


    @Transactional
    public void addToBox(AddToBoxDto addToBoxDto) {
        log.info("ActionLog.addToBox.start");
        if (profileRepository.existsByBoxes_Id(addToBoxDto.getBoxId())) {
            throw new AlreadyExistException("PROFILE_ALREADY_EXIST_IN_BOX");
        }
        UserEntity userEntity = userRepository.findById(addToBoxDto.getUserId()).orElseThrow(() ->
        {
            log.info("ActionLog.addToBox.error user not found");
            throw new NotFoundException("USER_NOT_FOUND");
        });
        ProfileEntity profileEntity = profileRepository.findById(addToBoxDto.getProfileId()).orElseThrow(() ->
        {
            log.info("ActionLog.addToBox.error profile not found");
            throw new NotFoundException("PROFILE_NOT_FOUND");
        });
        boolean check = true;
        boolean isArchived = false;
        for (BoxEntity boxEntity : userEntity.getBoxes()) {
            if (boxEntity.getProfiles().contains(profileEntity)) {
                boxEntity.getProfiles().remove(profileEntity);
                if (boxEntity.getTitle().equals("Bazaaar") || boxEntity.getTitle().equals("Specials")){
                    isArchived = true;
                }
                check = false;
                break;
            }
        }
        if (isArchived || check) {
            profileEntity.setSavedBoxes(profileEntity.getSavedBoxes() + 1);
        }
        profileEntity.setBoxes(new ArrayList<>(List.of(
            BoxEntity.builder().id(addToBoxDto.getBoxId()).build()
        )));

        profileRepository.save(profileEntity);
        log.info("ActionLog.addToBox.end");
    }

    public void removeFromBox(RemoveFromBoxDto removeFromBoxDto) {
        log.info("ActionLog.removeFromBox.start");
        UserEntity userEntity = userRepository.findById(removeFromBoxDto.getUserId()).orElseThrow(() ->
        {
            log.info("ActionLog.addToBox.error user not found");
            throw new NotFoundException("USER_NOT_FOUND");
        });
        System.out.println(userEntity.getBoxes().get(0).getId());
        BoxEntity boxEntity = boxRepository.findById(removeFromBoxDto.getBoxId()).orElseThrow(() ->
        {
            log.info("ActionLog.addToBox.error box not found");
            throw new NotFoundException("BOX_NOT_FOUND");
        });
        ProfileEntity profileEntity = profileRepository.findById(removeFromBoxDto.getProfileId()).orElseThrow(() ->
        {
            log.info("ActionLog.addToBox.error profile not found");
            throw new NotFoundException("PROFILE_NOT_FOUND");
        });

        profileEntity.getBoxes().remove(boxEntity);


        if (profileEntity.getUser().getPlan().equals(UserType.COMPANY)) {
            profileEntity.getBoxes().add(userEntity.getBoxes().stream()
                .filter(e -> e.getTitle().equals("Specials")).findAny().get());

        } else {
            profileEntity.getBoxes().add(userEntity.getBoxes().stream()
                .filter(e -> e.getTitle().equals("Bazaaar")).findAny().get());
        }
        profileEntity.setSavedBoxes(profileEntity.getSavedBoxes() - 1);
        profileRepository.save(profileEntity);
        log.info("ActionLog.removeFromBox.start");
    }

}

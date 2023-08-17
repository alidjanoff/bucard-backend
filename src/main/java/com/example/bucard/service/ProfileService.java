package com.example.bucard.service;

import com.example.bucard.dao.entity.BoxEntity;
import com.example.bucard.dao.entity.ProfileEntity;
import com.example.bucard.dao.repository.ProfileRepository;
import com.example.bucard.mapper.ProfileMapper;
import com.example.bucard.model.dto.AddToBoxDto;
import com.example.bucard.model.dto.ProfileDto;
import com.example.bucard.model.exception.NotFoundException;
import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ProfileService {
    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
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
        ProfileEntity profileEntity = profileRepository.findById(addToBoxDto.getProfileId())
            .orElseThrow(() -> {
                log.error("ActionLog.saveProfile.error with id {}", addToBoxDto.getProfileId());
                throw new NotFoundException("PROFILE_NOT_FOUND with id" + addToBoxDto.getProfileId());
            });
        profileEntity.setSavedBoxes(profileEntity.getSavedBoxes() + 1);
        profileEntity.setBoxes(new ArrayList<>(List.of(BoxEntity.builder()
            .id(addToBoxDto.getBoxId())
            .build())
            )
        );
        profileRepository.save(profileEntity);
        log.info("ActionLog.addToBox.end");
    }
}

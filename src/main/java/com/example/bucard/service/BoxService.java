package com.example.bucard.service;

import com.example.bucard.dao.entity.BoxEntity;
import com.example.bucard.dao.entity.ProfileEntity;
import com.example.bucard.dao.repository.BoxRepository;
import com.example.bucard.mapper.BoxMapper;
import com.example.bucard.model.dto.BoxDto;
import com.example.bucard.model.dto.BoxRequestDto;
import com.example.bucard.model.exception.AlreadyExistException;
import com.example.bucard.model.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BoxService {
    private final BoxRepository boxRepository;

    public BoxService(BoxRepository boxRepository) {
        this.boxRepository = boxRepository;
    }

    public BoxDto createBox(BoxRequestDto boxDto) {
        log.info("ActionLog.createBox.start");


        if (!boxRepository.existsByTitleAndUser_Id(boxDto.getTitle(), boxDto.getUserId())) {
            BoxEntity boxEntity = boxRepository.save(
                BoxMapper.INSTANCE.mapDtoToEntity(boxDto)
            );
            log.info("ActionLog.createBox.end");
            return BoxMapper.INSTANCE.mapEntityToDto(boxEntity);
        } else {
            throw new AlreadyExistException("BOX_ALREADY_EXIST");
        }
    }

    public Boolean checkProfileInBox(Long boxId, Long profileId) {
        log.info("ActionLog.checkProfileInBox.start");
        boolean exists = boxRepository.existsByIdAndProfiles_Id(boxId, profileId);
        log.info("ActionLog.checkProfileInBox.end");
        return exists;
    }

    public void addProfileToBox(Long boxId, Long profileId, Long userId) {
        log.info("ActionLog.addProfileToBox.start");
        BoxEntity boxEntity = boxRepository.findByProfiles_IdAndUser_Id(profileId, userId);
        if (boxEntity != null) {
            boxEntity.getProfiles().stream().map(e -> {
                if (e.getId().equals(profileId)) {
                    e.setId(profileId);
                }
                return e;
            });
            boxRepository.save(boxEntity);
        } else {
            BoxEntity box = boxRepository.findById(boxId).get();
            if (boxEntity.getProfiles() != null) {
                boxEntity.getProfiles().add(ProfileEntity.builder().id(profileId).build());
            } else {
                boxEntity.setProfiles(new ArrayList<>(List.of(ProfileEntity.builder().id(profileId).build())));
            }
            boxRepository.save(box);
        }

    }
}

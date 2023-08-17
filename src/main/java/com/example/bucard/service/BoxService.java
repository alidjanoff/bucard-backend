package com.example.bucard.service;

import com.example.bucard.dao.repository.BoxRepository;
import com.example.bucard.mapper.BoxMapper;
import com.example.bucard.model.dto.BoxRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BoxService {
    private final BoxRepository boxRepository;

    public BoxService(BoxRepository boxRepository) {
        this.boxRepository = boxRepository;
    }

    public void createBox(BoxRequestDto boxDto){
        log.info("ActionLog.createBox.start");
        boxRepository.save(
            BoxMapper.INSTANCE.mapDtoToEntity(boxDto)
        );
        log.info("ActionLog.createBox.end");
    }
}

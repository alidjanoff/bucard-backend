package com.example.bucard.service;

import com.example.bucard.dao.entity.DetailEntity;
import com.example.bucard.dao.entity.InfoEntity;
import com.example.bucard.dao.repository.InfoRepository;
import com.example.bucard.mapper.InfoMapper;
import com.example.bucard.model.dto.InfoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class InfoService {
    private final InfoRepository infoRepository;

    public InfoService(InfoRepository infoRepository) {
        this.infoRepository = infoRepository;
    }

    public List<InfoDto> getInfos(){
        log.info("ActionLog.getInfos.start");
        List<InfoEntity> infoEntities = infoRepository.findAll();
        log.info("ActionLog.getInfos.end");
        return InfoMapper.INSTANCE.mapEntitiesToDtos(infoEntities);
    }

    public void addInfo(InfoDto infoDto){
        log.info("ActionLog.addInfos.start");
        InfoEntity infoEntity = InfoMapper.INSTANCE.mapDtoToEntity(infoDto);
        for (DetailEntity detailEntity: infoEntity.getDetails()) {
            detailEntity.setInfo(infoEntity);
        }
        infoRepository.save(infoEntity);
        log.info("ActionLog.addInfos.end");

    }
}

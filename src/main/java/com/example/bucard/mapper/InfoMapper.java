package com.example.bucard.mapper;

import com.example.bucard.dao.entity.InfoEntity;
import com.example.bucard.model.dto.InfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class InfoMapper {
    public static InfoMapper INSTANCE = Mappers.getMapper(InfoMapper.class);

    public abstract InfoDto mapEntityToDto(InfoEntity infoEntity);

    public abstract List<InfoDto> mapEntitiesToDtos(List<InfoEntity> infoEntities);

    public abstract InfoEntity mapDtoToEntity(InfoDto infoDto);
}

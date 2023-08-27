package com.example.bucard.mapper;

import com.example.bucard.dao.entity.PreRegisterEntity;
import com.example.bucard.model.dto.PreRegisterDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class PreRegisterMapper {
    public static final PreRegisterMapper INSTANCE = Mappers.getMapper(PreRegisterMapper.class);

    public abstract PreRegisterEntity mapDtoToEntity(PreRegisterDto preRegisterDto);
}

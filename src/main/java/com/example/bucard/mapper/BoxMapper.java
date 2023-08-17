package com.example.bucard.mapper;

import com.example.bucard.dao.entity.BoxEntity;
import com.example.bucard.model.dto.BoxDto;
import com.example.bucard.model.dto.BoxRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class BoxMapper {
    public static BoxMapper INSTANCE = Mappers.getMapper(BoxMapper.class);

    @Mapping(target = "profiles", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    public abstract BoxEntity mapDtoToEntity(BoxRequestDto boxDto);
}

package com.example.bucard.mapper;

import com.example.bucard.dao.entity.ProfileEntity;
import com.example.bucard.model.dto.ProfileDto;
import com.example.bucard.model.dto.ProfileResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class ProfileMapper {
    public static ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "boxes", ignore = true)
    @Mapping(target = "id",ignore = true)
    public abstract ProfileEntity mapDtoToEntity(ProfileDto profileDto);

    @Mapping(target = "phone", source = "user.phone")
    @Mapping(target = "fullName",source = "user.fullName")
    public abstract ProfileResponse mapEntityToDto(ProfileEntity profileEntity);
}

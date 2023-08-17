package com.example.bucard.mapper;

import com.example.bucard.dao.entity.UserEntity;
import com.example.bucard.model.dto.RegisterDto;
import com.example.bucard.model.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.Qualifier;
import org.mapstruct.factory.Mappers;

import java.util.Base64;

@Mapper
public abstract class UserMapper {
    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "plan", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "boxes", ignore = true)
    @Mapping(target = "password", source = "password",qualifiedByName = "encodePassword")
    public abstract UserEntity mapRegisterDtoToEntity(RegisterDto registerDto);

    public abstract UserDto mapEntityToDto(UserEntity userEntity);

    @Named(value = "encodePassword")
    protected static String encodePassword(String password){
        return Base64.getEncoder().encodeToString(password.getBytes());
    }
}

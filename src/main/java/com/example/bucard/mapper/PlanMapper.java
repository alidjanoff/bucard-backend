package com.example.bucard.mapper;

import com.example.bucard.dao.entity.PlanEntity;
import com.example.bucard.model.dto.PlanDto;
import com.example.bucard.model.dto.PlanResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class PlanMapper {
    public static PlanMapper INSTANCE = Mappers.getMapper(PlanMapper.class);

    public abstract PlanResponse mapEntityToDto(PlanEntity planEntity);

    public abstract List<PlanResponse> mapEntitiesToDtos(List<PlanEntity> planEntities);
}

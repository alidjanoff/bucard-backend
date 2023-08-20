package com.example.bucard.service;

import com.example.bucard.dao.entity.PlanEntity;
import com.example.bucard.dao.repository.PlanRepository;
import com.example.bucard.mapper.PlanMapper;
import com.example.bucard.model.dto.PlanResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PlanService {
    private final PlanRepository planRepository;

    public PlanService(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    public List<PlanResponse> getPlans(){
        log.info("ActionLog.getPlans.start");
        List<PlanEntity> planEntities = planRepository.findAll();
        log.info("ActionLog.getPlans.end");
        return PlanMapper.INSTANCE.mapEntitiesToDtos(planEntities);
    }

    public void addPlan(PlanResponse planResponse){

    }
}

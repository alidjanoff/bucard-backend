package com.example.bucard.controller;

import com.example.bucard.model.dto.PlanResponse;
import com.example.bucard.service.PlanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/plan")
public class PlanController {
    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @GetMapping
    public List<PlanResponse> getPlans() {
        return planService.getPlans();
    }

    @PostMapping
    public void addPlan(@RequestBody PlanResponse planResponse){

    }
}

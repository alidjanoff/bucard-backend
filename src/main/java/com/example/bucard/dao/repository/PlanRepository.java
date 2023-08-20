package com.example.bucard.dao.repository;

import com.example.bucard.dao.entity.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<PlanEntity,Long> {
}

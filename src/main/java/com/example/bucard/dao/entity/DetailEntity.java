package com.example.bucard.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String detail;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "plan_id")
    private PlanEntity plan;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "info_id")
    private InfoEntity info;
}

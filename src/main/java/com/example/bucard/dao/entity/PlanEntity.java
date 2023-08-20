package com.example.bucard.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "plans")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Integer monthlyPrice;

    private Integer yearlyPrice;

    private Boolean discount;

    private Integer percent;

    @OneToMany(mappedBy = "plan")
    private List<DetailEntity> details;
}

package com.example.bucard.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "infos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "info",cascade = CascadeType.PERSIST)
    private List<DetailEntity> details;
}

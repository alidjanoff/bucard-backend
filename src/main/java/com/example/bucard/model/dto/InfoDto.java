package com.example.bucard.model.dto;

import com.example.bucard.dao.entity.DetailEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InfoDto {
    private Long id;

    private String title;

    private List<DetailDto> details;
}

package com.example.bucard.model.dto;

import com.example.bucard.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanDto {
    private UserType plan;

    private Long userId;
}

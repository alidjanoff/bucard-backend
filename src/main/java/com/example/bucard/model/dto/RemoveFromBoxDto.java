package com.example.bucard.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RemoveFromBoxDto {
    private Long userId;

    private Long profileId;

    private Long boxId;
}

package com.example.bucard.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddToBoxDto {
    private Long profileId;

    private Long boxId;
}

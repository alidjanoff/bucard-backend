package com.example.bucard.model.dto;

import com.example.bucard.enums.UserType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileDto {
    private Long id;

    private Long userId;

    private String profession;

    private Integer savedBoxes;

    private String profileImg;

    private String bannerImg;

    private String location;
}

package com.example.bucard.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileResponse {
    private Long id;

    private String fullName;

    private String phone;

    private String profession;

    private Integer savedBoxes;

    private String profileImg;

    private String bannerImg;

    private String location;
}

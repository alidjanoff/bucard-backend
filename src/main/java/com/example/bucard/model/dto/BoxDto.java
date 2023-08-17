package com.example.bucard.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;


import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoxDto {
    private Long id;

    private String title;

    private String emoji;

    private List<ProfileDto> profiles;
}

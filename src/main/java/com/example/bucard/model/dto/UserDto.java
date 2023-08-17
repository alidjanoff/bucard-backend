package com.example.bucard.model.dto;

import com.example.bucard.dao.entity.BoxEntity;
import com.example.bucard.enums.UserType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class UserDto {
    private Long id;

    private String fullName;

    private String email;

    private String phone;

    private UserType plan;

    private List<BoxDto> boxes;
}

package com.example.bucard.model.dto;

import com.example.bucard.util.ValidPassword;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterDto {
    @NotNull
    @Pattern(regexp = "(\\+994|0)[0-9]{9}")
    private String phone;

    @NotNull
    private String fullName;

    @NotNull
    @ValidPassword
    private String password;
}

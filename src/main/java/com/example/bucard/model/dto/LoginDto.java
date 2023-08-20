package com.example.bucard.model.dto;

import com.example.bucard.util.ValidPassword;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    @NotNull
    @Pattern(regexp = "(\\+994|0)[0-9]{9}")
    private String phone;

    @NotNull
    @ValidPassword
    private String password;
}

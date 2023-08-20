package com.example.bucard.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OtpModel {
    private Integer otp;
    private Short attempts;
}

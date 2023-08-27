package com.example.bucard.controller;

import com.example.bucard.model.dto.PreRegisterDto;
import com.example.bucard.service.PreRegisterService;
import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pre-register/user/create")
public class PreRegisterController {
    PreRegisterService preRegisterService;

    public PreRegisterController(PreRegisterService preRegisterService) {
        this.preRegisterService = preRegisterService;
    }

    @PostMapping("/send-mail")
    public void preRegisterMailVerification(@RequestBody PreRegisterDto preRegisterDto) throws MessagingException {
        preRegisterService.sendMail(preRegisterDto);
    }

    @PostMapping
    public void addMail(@RequestBody PreRegisterDto preRegisterDto){
        preRegisterService.addMail(preRegisterDto);
    }
}

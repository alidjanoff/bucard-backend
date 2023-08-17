package com.example.bucard.service;

import com.example.bucard.model.dto.PreRegisterDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PreRegisterService {
    private final JavaMailSender javaMailSender;

    public PreRegisterService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(PreRegisterDto preRegisterDto){
        log.info("ActionLog.sendMail.start");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(preRegisterDto.getEmail());
        message.setSubject("[no-reply] Texnoera Academy");
        message.setText("test");
        message.setFrom("texnoera.academy@gmail.com");
        javaMailSender.send(message);
        log.info("ActionLog.sendMail.end");
    }
}

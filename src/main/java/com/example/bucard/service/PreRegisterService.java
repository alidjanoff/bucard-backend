package com.example.bucard.service;

import com.example.bucard.dao.repository.PreRegisterRepository;
import com.example.bucard.mapper.PreRegisterMapper;
import com.example.bucard.model.dto.PreRegisterDto;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Locale;

@Service
@Slf4j
public class PreRegisterService {
    private final JavaMailSender javaMailSender;

    private final TemplateEngine templateEngine;

    private final PreRegisterRepository preRegisterRepository;

    public PreRegisterService(JavaMailSender javaMailSender, TemplateEngine templateEngine, PreRegisterRepository preRegisterRepository) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
        this.preRegisterRepository = preRegisterRepository;
    }

    public void sendMail(PreRegisterDto preRegisterDto) throws MessagingException {
        log.info("ActionLog.sendMail.start");
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMultipart multipart = new MimeMultipart();
        MimeBodyPart content = new MimeBodyPart();
        content.setHeader("content-type", "text/html");
        msg.setFrom(new InternetAddress("bucardapp@gmail.com"));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(preRegisterDto.getEmail(), false));
        msg.setSubject("Welcome to bucard");
        String output = this.templateEngine.process("emailTemplate", new Context(Locale.getDefault()));
        content.setContent(output, "text/html; charset=utf-8");
        multipart.addBodyPart(content);
        msg.setContent(multipart);
        javaMailSender.send(msg);
        log.info("ActionLog.sendMail.end");
    }

    public void addMail(PreRegisterDto preRegisterDto){
        log.info("ActionLog.addMail.start");
        preRegisterRepository.save(PreRegisterMapper.INSTANCE.mapDtoToEntity(preRegisterDto));
        log.info("ActionLog.addMail.end");
    }
}

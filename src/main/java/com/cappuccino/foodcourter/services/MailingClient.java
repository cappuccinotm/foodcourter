package com.cappuccino.foodcourter.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Класс, позволяющий отправлять email сообщения
 */
@Service
public class MailingClient {

    private final JavaMailSender mailSender;

    @Value("${mail.from-address}")
    private String applicationEmailAddress;

    public MailingClient(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void sendEmail(SimpleMailMessage mail){
        mail.setFrom(applicationEmailAddress);
        mailSender.send(mail);
    }

}

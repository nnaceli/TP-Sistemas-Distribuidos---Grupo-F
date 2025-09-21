package com.unla.grupoF.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;



@Component
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;

    public void sendNewPassword(
            String to, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@baeldung.com");
        message.setTo(to);
        message.setSubject("TP GRPC - ALTA DE USUARIO");
        message.setText("Hola,\n te informamos que tu usuario ha sido dado de alta y que la contraseña es: " + text
        + "\nPor favor, cambiala al iniciar sesión.\nSaludos.");
        try {
            emailSender.send(message);
        } catch ( Exception e) {
            throw new RuntimeException( " La contraseña no pudo ser comunicada" + e.getMessage() );
        }

    }

}

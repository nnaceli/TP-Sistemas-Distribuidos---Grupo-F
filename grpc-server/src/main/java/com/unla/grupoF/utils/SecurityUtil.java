package com.unla.grupoF.utils;


import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Random;


//Componente para manejo de seguridad:
// * generación de contraseña
// * encriptacion de contraseñas
// * generación de tokens JWT
@Component
public class SecurityUtil {

    @Getter
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public SecurityUtil(){
    }

    public static String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public static boolean checkPassword(String rawPassword, String encodedPasswordFromStorage) {
        return passwordEncoder.matches(rawPassword, encodedPasswordFromStorage);
    }

    public static String randomPassword(int len) {
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();

        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }
}
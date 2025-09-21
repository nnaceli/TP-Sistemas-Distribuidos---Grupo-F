package com.unla.grupoF.utils;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.Set;


//Componente para manejo de seguridad:
// * generación de contraseña
// * encriptacion de contraseñas
// * generación de tokens JWT
@Component
public class SecurityUtil {

    @Getter
    private BCryptPasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String secret;

    public SecurityUtil() throws IOException {
        passwordEncoder = new BCryptPasswordEncoder();
        Properties properties = new Properties();
        properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
        secret = properties.getProperty("jwt.secret"); //no lo tomaba del @Value
    }


    private Key getSigningKey() {
        return new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS256.getJcaName());
    }

    //PERMISOS
    public boolean hasRole(String userRole, Set<String> requiredRole) {
        System.out.println("Verificando si el rol " + userRole + " esta en " + requiredRole);

        return requiredRole.contains(userRole);
    }

    // CONTRASEÑA
    public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public boolean checkPassword(String rawPassword, String encodedPasswordFromStorage) {
        return passwordEncoder.matches(rawPassword, encodedPasswordFromStorage);
    }

    public String randomPassword(int len) {
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();

        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }

    //JWT (Json Web Token)

    public String generateToken(String username, String rol) {
        return Jwts.builder()
                .setSubject(username)
                .claim("rol", rol)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // una hora
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String getRolFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("rol", String.class);
    }
}
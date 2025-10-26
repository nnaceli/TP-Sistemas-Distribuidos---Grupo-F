package com.unla.grupof.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Configuracion de seguridad minima para Spring Boot.
 *
 * NOTA: Esta clase se crea TEMPORALMENTE para deshabilitar la seguridad
 * en la ruta de listado de donaciones y permitir la depuración.
 * En un entorno de produccion, se debe reestablecer la seguridad JWT.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Deshabilita CSRF para APIs REST
            .authorizeRequests()
                // PERMITIMOS EL ACCESO SIN AUTENTICACION A LA RUTA DE LOGIN
                .antMatchers("/api/client/login").permitAll()
                // PERMITIMOS EL ACCESO SIN AUTENTICACION A LA RUTA DEL LISTADO (Temporal)
                .antMatchers("/api/rest/donaciones").permitAll() // <--- CAMBIO CLAVE
                // CUALQUIER OTRA PETICION (excepto Login y Listado) AUN REQUERIRA AUTENTICACION
                .anyRequest().authenticated();
    }
}

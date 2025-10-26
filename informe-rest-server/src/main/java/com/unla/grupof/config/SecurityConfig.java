package com.unla.grupof.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Deshabilita CSRF para APIs REST
            .authorizeRequests()
                .antMatchers("/api/client/login").permitAll()
                .antMatchers("/api/rest/donaciones").permitAll() 
                .anyRequest().permitAll();
    }
}

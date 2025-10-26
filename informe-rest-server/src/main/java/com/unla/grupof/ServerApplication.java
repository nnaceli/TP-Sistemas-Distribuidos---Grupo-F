package com.unla.grupof;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication 
// Debes asegurarte de que el repositorio esté habilitado para que JPA funcione
@EnableJpaRepositories(basePackages = "com.unla.grupoF.repositories")
public class ServerApplication {

    public static void main(String[] args) {
        // La llamada a SpringApplication.run debe estar aquí
        SpringApplication.run(ServerApplication.class, args);
    }

}

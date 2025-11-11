package com.unla.grupoF;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

import com.unla.grupoF.dto.ComentarioEventoDTO;

import org.springframework.context.annotation.Bean;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

@SpringBootApplication
@EnableKafka
public class KafkaApplication {
    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }
}

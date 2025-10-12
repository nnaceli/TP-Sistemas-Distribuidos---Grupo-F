package com.unla.grupoF.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.unla.grupoF.KafkaProducer;
import com.unla.grupoF.dto.EventoDTO;
import org.springframework.stereotype.Service;

@Service
public class EventoExternoServiceProducer {
    private final KafkaProducer kafkaProducer;
    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    public EventoExternoServiceProducer(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    public void publicarEvento(EventoDTO dto) throws Exception{
        if (dto == null) {
            throw new Exception("El cuerpo del request esta incompleto o es nulo");
        }
        String topic = "eventos-solidarios";
        String mensaje = objectMapper.writeValueAsString(dto);
        kafkaProducer.enviarMensaje(topic, mensaje);
    }

    public void bajaEvento(EventoDTO dto) throws Exception{
        if (dto == null) {
            throw new Exception("El cuerpo del request esta incompleto o es nulo");
        }
        String topic = "baja-evento-solidario";
        String mensaje = objectMapper.writeValueAsString(dto);
        kafkaProducer.enviarMensaje(topic, mensaje);
    }


}

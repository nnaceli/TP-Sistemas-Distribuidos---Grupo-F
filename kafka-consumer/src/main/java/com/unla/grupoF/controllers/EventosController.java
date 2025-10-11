package com.unla.grupoF.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unla.grupoF.KafkaProducer;
import com.unla.grupoF.dto.SolicitudDonacionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/eventos")
public class EventosController {
    private final KafkaProducer kafkaProducer;
    private final ObjectMapper objectMapper;

    public EventosController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
        this.objectMapper = new ObjectMapper();
    }

   /* @PostMapping("/eventos")
    public ResponseEntity<String> eventosSolidarios(@RequestBody EventoDTO dto) {
        try {
            String topic = "eventos-solidarios";
            String mensaje = objectMapper.writeValueAsString(dto);
            kafkaProducer.enviarMensaje(topic, mensaje);
            return ResponseEntity.ok("Se envio el mensaje");
        }
        catch(JsonProcessingException e){
            return ResponseEntity.internalServerError().body("Fallo la conversion de DTO a string");
        }
    } */
}

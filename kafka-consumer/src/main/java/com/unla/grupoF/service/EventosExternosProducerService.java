package com.unla.grupoF.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unla.grupoF.dto.ComentarioEventoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventosExternosProducerService {

    // declaracion del topic
    private static final String TOPIC_NAME = "comentarios-eventos";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendComentario(ComentarioEventoDTO comentarioDTO) {
        try {
            // ObjectMapper convierte el DTO a la cadena JSON.
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.findAndRegisterModules(); 
            
            String jsonMessage = objectMapper.writeValueAsString(comentarioDTO);
            
            // se envia el mensaje al topic 'comentarios-eventos'
            kafkaTemplate.send(TOPIC_NAME, jsonMessage);
            System.out.println("PRODUCER: Mensaje de comentario enviado a Kafka: " + jsonMessage);
            
        } catch (Exception e) {
            System.err.println("ERROR PRODUCER: Fallo al serializar o enviar mensaje a Kafka: " + e.getMessage());
            e.printStackTrace();

            throw new RuntimeException("Error en el servicio productor de Kafka", e);
        }
    }
}
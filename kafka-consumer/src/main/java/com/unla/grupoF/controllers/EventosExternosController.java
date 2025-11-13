package com.unla.grupoF.controllers;

import com.unla.grupoF.dto.ComentarioEventoDTO;
import com.unla.grupoF.service.EventosExternosProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comentarios")
public class EventosExternosController {

    @Autowired
    private EventosExternosProducerService producerService;

    // Endpoint al que el cliente Python hará POST
    @PostMapping("/enviar")
    public ResponseEntity<String> enviarComentario(@RequestBody ComentarioEventoDTO comentarioDTO) {
        try {
            // se valida que el DTO no sea nulo
            if (comentarioDTO == null) {
                return ResponseEntity.badRequest().body("El cuerpo de la solicitud no puede estar vacío.");
            }

            // se llama al servicio que enviará el JSON a Kafka
            producerService.sendComentario(comentarioDTO); 
            
            // Respuesta inmediata al cliente (la persistencia es ASÍNCRONA)
            return ResponseEntity.ok("Comentario recibido y enviado a Kafka para procesamiento asíncrono.");
            
        } catch (Exception e) {
            // Captura cualquier RuntimeException lanzada por el servicio productor
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                               .body("Error interno al procesar la solicitud: " + e.getMessage());
        }
    }
}
package com.unla.grupoF.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unla.grupoF.KafkaProducer;
import com.unla.grupoF.dto.EventoDTO;
import com.unla.grupoF.service.EventoExternoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventos")
public class EventosController {
    private final EventoExternoService eventoExternoService;

    public EventosController(EventoExternoService eventoExternoService) {
        this.eventoExternoService = eventoExternoService;
    }

    @GetMapping("/listarEventos")
    public ResponseEntity<Object> obtenerEventos(){
        try {
            List<EventoDTO> lista = eventoExternoService.obtenerEventos();
            return ResponseEntity.ok().body(lista);
        }
        catch(Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}

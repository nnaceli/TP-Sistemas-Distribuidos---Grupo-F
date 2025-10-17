package com.unla.grupoF.controllers;

import com.unla.grupoF.dto.EventoDTO;
import com.unla.grupoF.service.EventoExternoService;
import com.unla.grupoF.service.EventoExternoServiceProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/eventos")
public class EventosController {
    private final EventoExternoService eventoExternoService;
    public final EventoExternoServiceProducer eventoExternoServiceProducer;

    public EventosController(EventoExternoService eventoExternoService, EventoExternoServiceProducer eventoExternoServiceProducer) {
        this.eventoExternoService = eventoExternoService;
        this.eventoExternoServiceProducer = eventoExternoServiceProducer;
    }

    @GetMapping("/listarEventos")
    public ResponseEntity<Object> obtenerEventos() {
        try {
            List<EventoDTO> lista = eventoExternoService.obtenerEventos();
            return ResponseEntity.ok().body(lista);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @PostMapping("/publicarEvento")
    public ResponseEntity<Object> publicarEvento(@RequestBody EventoDTO dto) {
        try {
            eventoExternoServiceProducer.publicarEvento(dto);
            return ResponseEntity.ok()
                    .body(Collections.singletonMap("message", "Se publicó la creación del evento"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @DeleteMapping("/publicarBajaEvento")
    public ResponseEntity<Object> publicarBajaEvento(@RequestBody EventoDTO dto) {
        try {
            eventoExternoServiceProducer.bajaEvento(dto);
            return ResponseEntity.ok()
                    .body(Collections.singletonMap("message", "Se publicó la baja del evento"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
    }
}
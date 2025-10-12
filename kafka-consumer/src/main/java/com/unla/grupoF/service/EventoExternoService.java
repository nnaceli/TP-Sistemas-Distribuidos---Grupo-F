package com.unla.grupoF.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.unla.grupoF.dto.EventoDTO;
import com.unla.grupoF.entities.EventoExterno;
import com.unla.grupoF.mappers.EventoMapper;
import com.unla.grupoF.repositories.EventoExternoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventoExternoService {

    private final EventoExternoRepository eventoRepository;
    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    // Guarda un evento externo nuevo
    public void guardarEventoExterno(EventoDTO dto) {
        EventoExterno evento = EventoExterno.builder()
                .organizacionId(dto.getOrganizacionId())
                .eventoId(dto.getEventoId())
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .fechaHora(dto.getFechaHora())
                .darDeBaja(false)
                .build();

        eventoRepository.save(evento);
        log.info("Evento externo guardado: {}", dto.getEventoId());
    }

    // verifica si ya existe un evento con ese ID
    public boolean existeEvento(String eventoId) {
        return eventoRepository.findByEventoId(eventoId).isPresent();
    }

    // marca un evento como dado de baja
    public boolean darDeBaja(String eventoId) {
        return eventoRepository.findByEventoId(eventoId).map(evento -> {
            if (!evento.isDarDeBaja()) {
                evento.setDarDeBaja(true);
                eventoRepository.save(evento); 
                log.info("Evento externo marcado como dado de baja: {}", eventoId);
            } else {
                log.info("Evento externo ya estaba dado de baja: {}", eventoId);
            }
            return true;
        }).orElseGet(() -> {
            log.warn("Evento externo no encontrado para dar de baja: {}", eventoId);
            return false;
        });
    }

    public List<EventoDTO> obtenerEventos() throws Exception{
        List<EventoDTO> dtoList = new ArrayList<>();
        List<EventoExterno> entityList = eventoRepository.findByDarDeBajaFalse();

        if (entityList.isEmpty()){
            throw new Exception("No existen eventos externos");
        }
        for (EventoExterno aux : entityList) {
            dtoList.add(EventoMapper.toDto(aux));
        }
        return dtoList;
    }
}


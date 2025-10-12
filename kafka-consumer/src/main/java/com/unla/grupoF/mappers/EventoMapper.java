package com.unla.grupoF.mappers;
import com.unla.grupoF.dto.*;
import com.unla.grupoF.entities.EventoExterno;

public class EventoMapper {

    public static EventoDTO toDto(EventoExterno evento) {
        if (evento == null) return null;

        return EventoDTO.builder()
                .organizacionId(evento.getOrganizacionId())
                .eventoId(evento.getEventoId())
                .nombre(evento.getNombre())
                .descripcion(evento.getDescripcion())
                .fechaHora(evento.getFechaHora())
                .build();
    }

    public static EventoExterno toEntity(EventoDTO dto) {
        if (dto == null) return null;

        return EventoExterno.builder()
                .organizacionId(dto.getOrganizacionId())
                .eventoId(dto.getEventoId())
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .fechaHora(dto.getFechaHora())
                .darDeBaja(false) // Valor por defecto
                .build();
    }
}
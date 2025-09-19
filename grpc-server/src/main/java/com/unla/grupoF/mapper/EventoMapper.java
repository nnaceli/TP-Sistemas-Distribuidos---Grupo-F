package com.unla.grupoF.mapper;

import com.google.protobuf.Timestamp;
import com.unla.grupoF.entities.Evento;
import com.unla.grupoF.entities.Usuario;
import com.unla.grupoF.service.EventoSolidarioOuterClass;
import com.unla.grupoF.service.EventoSolidarioServiceGrpc;
import com.unla.grupoF.mapper.UsuarioMapper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Set;
import java.util.stream.Collectors;

public class EventoMapper {

    private final UsuarioMapper usuarioMapper;

    public EventoMapper() {
        this.usuarioMapper = new UsuarioMapper();
    }

    public EventoSolidarioOuterClass.EventoSolidarioDTO toDTO(Evento evento) {
        return EventoSolidarioOuterClass.EventoSolidarioDTO.newBuilder()
            .setNombre(evento.getNombreEvento())
            .setDescripcion(evento.getDescripcion())
            .setFecha(toTimestamp(evento.getFechaHora()))
             .addAllMiembros(evento.getMiembros().stream().map(usuarioMapper::fromEntity).collect(Collectors.toList()))
            .build();
    }

    public Evento toEntity(EventoSolidarioOuterClass.EventoSolidarioDTO dto) {
        return Evento.builder()
                .nombreEvento(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .fechaHora(toLocalDateTime(dto.getFecha()))
                .miembros(dto.getMiembrosList().stream().map(usuarioMapper::fromProtobuf).collect(Collectors.toSet()))
                .build();
    }

    public EventoSolidarioOuterClass.EventoSolidario fromEntity(Evento evento) {
        return EventoSolidarioOuterClass.EventoSolidario.newBuilder()
            .setId(evento.getId())
            .setNombre(evento.getNombreEvento())
            .setDescripcion(evento.getDescripcion())
            .setFecha(toTimestamp(evento.getFechaHora()))
            .addAllMiembros(evento.getMiembros().stream().map(usuarioMapper::fromEntity).collect(Collectors.toList()))
            .build();
    }

    public Evento fromProtobuf(EventoSolidarioOuterClass.EventoSolidario eventoProto) {
        return Evento.builder()
                .id(eventoProto.getId())
                .nombreEvento(eventoProto.getNombre())
                .descripcion(eventoProto.getDescripcion())
                .fechaHora(toLocalDateTime(eventoProto.getFecha()))
                .miembros(eventoProto.getMiembrosList().stream().map(usuarioMapper::fromProtobuf).collect(Collectors.toSet()))
                .build();
    }

    private Timestamp toTimestamp(LocalDateTime dateTime) {
        Instant instant = dateTime.toInstant(ZoneOffset.UTC);
        return Timestamp.newBuilder()
            .setSeconds(instant.getEpochSecond())
            .setNanos(instant.getNano())
            .build();
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        return LocalDateTime.ofEpochSecond(timestamp.getSeconds(), timestamp.getNanos(), ZoneOffset.UTC);
    }
}

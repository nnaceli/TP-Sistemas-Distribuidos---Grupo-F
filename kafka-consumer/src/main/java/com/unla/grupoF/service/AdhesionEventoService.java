package com.unla.grupoF.service;

import com.unla.grupoF.dto.AdhesionEventoDTO;
import com.unla.grupoF.entities.AdhesionEvento;
import com.unla.grupoF.repositories.AdhesionEventoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdhesionEventoService {

    private final AdhesionEventoRepository repository;

    public void guardarAdhesion(AdhesionEventoDTO dto) {
         if (repository.existsByEventoIdAndVoluntarioId(dto.getEventoId(), dto.getVoluntarioId())) {
            throw new RuntimeException("El voluntario ya está adherido a este evento.");
        }
        AdhesionEvento adhesion = AdhesionEvento.builder()
                .eventoId(dto.getEventoId())
                .organizacionId(dto.getOrganizacionId())
                .voluntarioId(dto.getVoluntarioId())
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .telefono(dto.getTelefono())
                .email(dto.getEmail())
                .build();

        repository.save(adhesion);
    }
}

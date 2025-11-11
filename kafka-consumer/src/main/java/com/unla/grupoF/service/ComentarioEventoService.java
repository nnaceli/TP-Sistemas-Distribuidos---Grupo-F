package com.unla.grupoF.service;

import com.unla.grupoF.dto.ComentarioEventoDTO;
import com.unla.grupoF.entities.ComentarioEventoExterno;
import com.unla.grupoF.repositories.ComentarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;
import com.unla.grupoF.entities.ComentarioEventoExterno;

@Service
public class ComentarioEventoService {

    private final ComentarioRepository comentarioRepository;
    private final ModelMapper modelMapper;

    public ComentarioEventoService(ComentarioRepository comentarioRepository, ModelMapper modelMapper) {
        this.comentarioRepository = comentarioRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public void guardarComentario(ComentarioEventoDTO comentarioEventoDTO) {
        try {
            ComentarioEventoExterno entity = new ComentarioEventoExterno();
            

            entity.setIdEventoExterno(comentarioEventoDTO.getIdEventoExterno());
            entity.setIdOrganizacion(comentarioEventoDTO.getIdOrganizacion());
            entity.setIdVoluntario(comentarioEventoDTO.getIdVoluntario());

            entity.setNombreVoluntario(comentarioEventoDTO.getNombreVoluntario());
            entity.setApellidoVoluntario(comentarioEventoDTO.getApellidoVoluntario());
            entity.setComentario(comentarioEventoDTO.getComentario());
            entity.setFechaHora(comentarioEventoDTO.getFechaHora());

            comentarioRepository.save(entity);
            System.out.println("Comentario de evento externo guardado con éxito: ID Voluntario " + entity.getIdVoluntario());
        } catch (Exception e) {
            System.err.println("Error al guardar comentario (Causa raíz): " + e.getMessage()); 
            throw new RuntimeException("Error en la persistencia después de Kafka", e);
        }
    }
}
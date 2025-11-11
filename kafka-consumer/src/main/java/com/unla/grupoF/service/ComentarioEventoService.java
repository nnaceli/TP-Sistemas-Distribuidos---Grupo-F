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
            // Reemplazar modelMapper.map(...) por el mapeo manual:
            ComentarioEventoExterno entity = new ComentarioEventoExterno();
            
            // Mapeo de IDs recibidos
            entity.setIdEventoExterno(comentarioEventoDTO.getIdEventoExterno());
            entity.setIdOrganizacion(comentarioEventoDTO.getIdOrganizacion());
            entity.setIdVoluntario(comentarioEventoDTO.getIdVoluntario());

            // Mapeo de datos del comentario
            entity.setNombreVoluntario(comentarioEventoDTO.getNombreVoluntario());
            entity.setApellidoVoluntario(comentarioEventoDTO.getApellidoVoluntario());
            entity.setComentario(comentarioEventoDTO.getComentario());
            entity.setFechaHora(comentarioEventoDTO.getFechaHora()); // ¡Asegúrate que fechaHora está en el DTO!

            comentarioRepository.save(entity);
            System.out.println("Comentario de evento externo guardado con éxito: ID Voluntario " + entity.getIdVoluntario());
        } catch (Exception e) {
            // Este catch evita que el error 'Seek to current after exception' aparezca en la consola,
            // pero lo más importante es que la lógica de arriba ya no usa ModelMapper.
            System.err.println("Error al guardar comentario (Causa raíz): " + e.getMessage()); 
            // Podrías registrar un error con más detalle aquí: e.printStackTrace();
            throw new RuntimeException("Error en la persistencia después de Kafka", e);
        }
    }
}
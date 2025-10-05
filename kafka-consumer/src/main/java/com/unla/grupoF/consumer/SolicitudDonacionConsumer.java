package com.unla.grupoF.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unla.grupoF.dto.SolicitudDonacionDTO;
import com.unla.grupoF.entities.Categoria;
import com.unla.grupoF.entities.DonacionRequerida;
import com.unla.grupoF.entities.SolicitudDonacion;
import com.unla.grupoF.repositories.SolicitudDonacionRepository;
import lombok.*;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SolicitudDonacionConsumer {

    private final SolicitudDonacionRepository solicitudRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "solicitud-donaciones", groupId = "grupoF")
    public void consume(String message) {
        try {
            // convertir el JSON a dto
            SolicitudDonacionDTO dto = objectMapper.readValue(message, SolicitudDonacionDTO.class);

            // mapear a entidad
            SolicitudDonacion solicitud = SolicitudDonacion.builder()
                    .organizacionId(dto.getOrganizacionId())
                    .solicitudId(dto.getSolicitudId())
                    .donaciones(dto.getDonaciones().stream()
                                    .map(d -> DonacionRequerida.builder()
                                            .categoria(Categoria.valueOf(d.getCategoria()))
                                            .descripcion(d.getDescripcion())
                                            .build()
                                    ).toList()
                    )
                    .build();

            // setear relación bidireccional
            solicitud.getDonaciones().forEach(d -> d.setSolicitudDonacion(solicitud));

            // guardar en BD
            solicitudRepository.save(solicitud);

            System.out.println("Solicitud guardada: " + dto.getSolicitudId());

        } catch (Exception e) {
            System.err.println("Error procesando mensaje: " + e.getMessage());
        }
    }
}

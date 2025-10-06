package com.unla.grupoF;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unla.grupoF.dto.SolicitudDonacionDTO;
import com.unla.grupoF.dto.BajaSolicitudDonacionDTO;
import com.unla.grupoF.entities.Categoria;
import com.unla.grupoF.entities.DonacionRequerida;
import com.unla.grupoF.entities.SolicitudDonacion;
import com.unla.grupoF.service.SolicitudDonacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    private final SolicitudDonacionService solicitudService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    // Escucha todos los tópicos definidos
    @KafkaListener(topics = {
            "solicitud-donaciones",
            "baja-solicitud-donaciones",
            "transferencia-donaciones",
            "eventos-solidarios",
            "baja-evento-solidario",
            "adhesion-evento"
    }, groupId = "grupoF")
    public void listen(String message, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        System.out.println("📩 Mensaje recibido del tópico: " + topic);

        try {
            switch (topic) {

                // 🔹 NUEVA SOLICITUD DE DONACIÓN
                case "solicitud-donaciones" -> {
                    System.out.println("Procesando solicitud de donación...");
                    SolicitudDonacionDTO dto = objectMapper.readValue(message, SolicitudDonacionDTO.class);

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

                    // Relación bidireccional
                    solicitud.getDonaciones().forEach(d -> d.setSolicitudDonacion(solicitud));

                    solicitudService.guardarSolicitud(solicitud);
                    System.out.println("Solicitud guardada correctamente: " + dto.getSolicitudId());
                }

                // 🔹 BAJA DE SOLICITUD DE DONACIÓN
                case "baja-solicitud-donaciones" -> {
                    System.out.println("Procesando baja de solicitud de donación...");

                    // Mensaje esperado: {"organizacionId":1,"solicitudId":"SOL-001"}
                    BajaSolicitudDonacionDTO bajaDTO =
                            objectMapper.readValue(message, BajaSolicitudDonacionDTO.class);

                    boolean exito = solicitudService.darDeBaja(bajaDTO.getOrganizacionId(), bajaDTO.getSolicitudId());

                    if (exito) {
                        System.out.println("Solicitud dada de baja: " + bajaDTO.getSolicitudId());
                    } else {
                        System.out.println("No se encontró la solicitud para dar de baja: " + bajaDTO.getSolicitudId());
                    }
                }

                // 🔹 TRANSFERENCIAS
                case "transferencia-donaciones" -> {
                    System.out.println("Procesando transferencia de donaciones...");
                    // TODO: Implementar luego
                }

                // 🔹 EVENTOS SOLIDARIOS
                case "eventos-solidarios" -> {
                    System.out.println("Procesando evento solidario...");
                    // TODO: Implementar luego
                }

                // 🔹 BAJA DE EVENTOS
                case "baja-evento-solidario" -> {
                    System.out.println("Procesando baja de evento solidario...");
                    // TODO: Implementar luego
                }

                // 🔹 ADHESIÓN A EVENTOS
                case "adhesion-evento" -> {
                    System.out.println("Procesando adhesión a evento...");
                    // TODO: Implementar luego
                }

                default -> System.out.println("Tópico no reconocido: " + topic);
            }
        } catch (Exception e) {
            System.err.println("Error al procesar mensaje del tópico [" + topic + "]: " + e.getMessage());
        }
    }
}


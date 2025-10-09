package com.unla.grupoF;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unla.grupoF.dto.SolicitudDonacionDTO;
import com.unla.grupoF.dto.BajaSolicitudDonacionDTO;
import com.unla.grupoF.dto.TransferenciaDonacionDTO;
import com.unla.grupoF.service.SolicitudDonacionService;
import com.unla.grupoF.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    private final SolicitudDonacionService solicitudService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    // Escucha todos los tópicos definidos
    @KafkaListener(topics = {
            "solicitud-donaciones",
            "baja-solicitud-donaciones",
            "transferencia-donaciones${organization.id}", //nuestra org
            "eventos-solidarios",
            "baja-evento-solidario",
            "adhesion-evento"
    }, groupId = "grupoF")
    public void listen(String message, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        log.info("\uD83D\uDCE9 Mensaje recibido del tópico: {}", topic);
        try {
            switch (topic) {

                // 🔹 NUEVA SOLICITUD DE DONACIÓN
                case "solicitud-donaciones" -> {
                    log.info("Procesando solicitud de donación...");
                    SolicitudDonacionDTO dto = objectMapper.readValue(message, SolicitudDonacionDTO.class);
                    try {
                        solicitudService.guardarSolicitud(dto);
                        log.info("Solicitud guardada correctamente: {}", dto.getSolicitudId());
                    } catch (Exception e) {
                        log.error("Error procesando la solicitud: {}", e.getMessage());
                    }
                }

                // 🔹 BAJA DE SOLICITUD DE DONACIÓN
                case "baja-solicitud-donaciones" -> {
                    log.info("Procesando baja de solicitud de donación...");

                    // Mensaje esperado: {"organizacionId":1,"solicitudId":"SOL-001"}
                    BajaSolicitudDonacionDTO bajaDTO =
                            objectMapper.readValue(message, BajaSolicitudDonacionDTO.class);

                    boolean exito = solicitudService.darDeBaja(bajaDTO);

                    if (exito) {
                        log.info("Solicitud dada de baja: {}", bajaDTO.getSolicitudId());
                    } else {
                        log.warn("No se encontró la solicitud para dar de baja: {}", bajaDTO.getSolicitudId());
                    }
                }

                // 🔹 TRANSFERENCIAS
                case "transferencia-donaciones101" -> {
                    log.info("Procesando transferencia de donaciones...");
                    TransferenciaDonacionDTO transfDTO =
                            objectMapper.readValue(message, TransferenciaDonacionDTO.class);

                    if(transfDTO.getOrganizacionId().longValue() != Constants.ORG_ID.longValue()){
                        solicitudService.recibirDonacionTransferida(transfDTO);
                    }
                }

                // 🔹 EVENTOS SOLIDARIOS
                case "eventos-solidarios" -> {
                    log.info("Procesando evento solidario...");
                    // TODO: Implementar luego
                }

                // 🔹 BAJA DE EVENTOS
                case "baja-evento-solidario" -> {
                    log.info("Procesando baja de evento solidario...");
                    // TODO: Implementar luego
                }

                // 🔹 ADHESIÓN A EVENTOS
                case "adhesion-evento" -> {
                    log.info("Procesando adhesión a evento...");
                    // TODO: Implementar luego
                }

                default -> log.info("Tópico no reconocido: {}", topic);
            }
        } catch (Exception e) {
            log.error("Error al procesar mensaje del tópico [{}]: {}", topic, e.getMessage());
        }
    }
}


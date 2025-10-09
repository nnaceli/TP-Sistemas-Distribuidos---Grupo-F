package com.unla.grupoF;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unla.grupoF.dto.*;
import com.unla.grupoF.service.EventoExternoService;
import com.unla.grupoF.service.SolicitudDonacionService;
import com.unla.grupoF.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    private final SolicitudDonacionService solicitudService;
    private final EventoExternoService eventoService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = {
            "solicitud-donaciones",
            "baja-solicitud-donaciones",
            "transferencia-donaciones${organization.id}",
            "eventos-solidarios",
            "baja-evento-solidario",
            "adhesion-evento"
    }, groupId = "grupoF")
    public void listen(String message, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        log.info(" Mensaje recibido del tópico: {}", topic);
        try {
            switch (topic) {

                // 🔹 NUEVA SOLICITUD DE DONACIÓN
                case "solicitud-donaciones" -> {
                    log.info("Procesando solicitud de donación...");
                    SolicitudDonacionDTO dto = objectMapper.readValue(message, SolicitudDonacionDTO.class);
                    solicitudService.guardarSolicitud(dto);
                    log.info("Solicitud guardada correctamente: {}", dto.getSolicitudId());
                }

                // 🔹 BAJA DE SOLICITUD DE DONACIÓN
                case "baja-solicitud-donaciones" -> {
                    log.info("Procesando baja de solicitud de donación...");
                    BajaSolicitudDonacionDTO bajaDTO = objectMapper.readValue(message, BajaSolicitudDonacionDTO.class);
                    boolean exito = solicitudService.darDeBaja(bajaDTO);
                    if (exito) log.info("Solicitud dada de baja: {}", bajaDTO.getSolicitudId());
                    else log.warn("No se encontró la solicitud: {}", bajaDTO.getSolicitudId());
                }

                // 🔹 TRANSFERENCIAS
                case "transferencia-donaciones101" -> {
                    log.info("Procesando transferencia de donaciones...");
                    TransferenciaDonacionDTO transfDTO = objectMapper.readValue(message, TransferenciaDonacionDTO.class);
                    if (!transfDTO.getOrganizacionId().equals(Constants.ORG_ID)) {
                        solicitudService.recibirDonacionTransferida(transfDTO);
                        log.info("Donación transferida registrada");
                    } else {
                        log.info("Evento propio de transferencia ignorado");
                    }
                }

                // 🔹 EVENTOS SOLIDARIOS
                case "eventos-solidarios" -> {
                    log.info("Procesando evento solidario...");
                    EventoDTO eventoDTO = objectMapper.readValue(message, EventoDTO.class);

                    // ignorar eventos propios
                    if (eventoDTO.getOrganizacionId() != null && eventoDTO.getOrganizacionId().equals(Constants.ORG_ID)) {
                        log.info("Evento propio ignorado: {}", eventoDTO.getEventoId());
                        // no guardamos
                    }
                    // chequea fecha obligatoria y futura
                    else if (eventoDTO.getFechaHora() == null || eventoDTO.getFechaHora().isBefore(LocalDateTime.now())) {
                        log.info("Evento vencido o fecha inválida: {}", eventoDTO.getEventoId());
                    }
                    // evitar duplicados (ya registrado)
                    else if (eventoService.existeEvento(eventoDTO.getEventoId())) {
                        log.info("Evento ya registrado (se omite): {}", eventoDTO.getEventoId());
                    }
                    // guardar evento externo
                    else {
                        eventoService.guardarEventoExterno(eventoDTO);
                        log.info("Evento externo guardado correctamente: {}", eventoDTO.getEventoId());
                    }
                }

                // 🔹 BAJA DE EVENTOS 
                case "baja-evento-solidario" -> {
                    log.info("Procesando baja de evento solidario...");
                    EventoDTO bajaDTO = objectMapper.readValue(message, EventoDTO.class);

                    boolean exito = eventoService.darDeBaja(bajaDTO.getEventoId());

                    if (exito) {
                        log.info("Evento dado de baja correctamente: {}", bajaDTO.getEventoId());
                    } else {
                        log.warn("No se encontró el evento para dar de baja: {}", bajaDTO.getEventoId());
                    }
                }

                // 🔹 ADHESIÓN A EVENTOS
                case "adhesion-evento" -> {
                    log.info("Procesando adhesión a evento... (pendiente)");
                    // TODO: implementar adhesión
                }

                default -> log.info("Tópico no reconocido: {}", topic);
            }
        } catch (Exception e) {
            log.error("Error al procesar mensaje del tópico [{}]: {}", topic, e.getMessage());
        }
    }
}

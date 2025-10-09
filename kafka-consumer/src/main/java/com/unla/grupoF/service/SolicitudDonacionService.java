package com.unla.grupoF.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unla.grupoF.dto.BajaSolicitudDonacionDTO;
import com.unla.grupoF.dto.SolicitudDonacionDTO;
import com.unla.grupoF.dto.TransferenciaDonacionDTO;
import com.unla.grupoF.entities.Categoria;
import com.unla.grupoF.entities.DonacionRequerida;
import com.unla.grupoF.entities.SolicitudDonacion;
import com.unla.grupoF.repositories.SolicitudDonacionRepository;
import com.unla.grupoF.util.Constants;
import com.unla.grupoF.util.SecuredPostClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SolicitudDonacionService {

    private final SolicitudDonacionRepository solicitudRepository;
    @Autowired
    private SecuredPostClient client;

    private final ObjectMapper objectMapper = new ObjectMapper();

    // guardar solicitud nueva
    public void guardarSolicitud(SolicitudDonacionDTO dto) throws Exception {
        if (buscarPorSolicitudId(dto.getSolicitudId()).isPresent()) {
            throw new Exception("Ya existe esa solicitud de donación");
        }
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
        solicitudRepository.save(solicitud);
    }

    // buscar x id de donacion
    public Optional<SolicitudDonacion> buscarPorSolicitudId(String solicitudId) {
        return solicitudRepository.findBySolicitudId(solicitudId);
    }

    // baja logica
    public boolean darDeBaja(BajaSolicitudDonacionDTO dto) {
        return buscarPorSolicitudId(dto.getSolicitudId())
                .filter(s ->
                        s.getOrganizacionId().equals(dto.getOrganizacionId())
                                && !s.isEliminada())
                .map(solicitud -> {
                    solicitud.setEliminada(true);
                    solicitudRepository.save(solicitud);
                    return true;
                })
                .orElse(false);
    }

    public boolean recibirDonacionTransferida(TransferenciaDonacionDTO dto) {
        try {
            String token = client.login();
            for (TransferenciaDonacionDTO.DonacionTransferidaDTO donacion : dto.getDonaciones()) {
                String donacionJSON = convertirDonacionAJson(donacion);
                client.postSecuredData(token, donacionJSON, Constants.DONACION_ENDPOINT + "/crear");
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (RuntimeException e) {
            log.error("No se pudo enviar la transferencia de donaciones a nuestro sistema: {}", e.getMessage());
            return false;
        }
        return true;
    }

    public String convertirDonacionAJson(TransferenciaDonacionDTO.DonacionTransferidaDTO donacionTransferida) throws JsonProcessingException {
        return objectMapper.writeValueAsString(donacionTransferida);
    }
}

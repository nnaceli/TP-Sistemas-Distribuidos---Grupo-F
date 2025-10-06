package com.unla.grupoF.service;

import com.unla.grupoF.entities.SolicitudDonacion;
import com.unla.grupoF.repositories.SolicitudDonacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SolicitudDonacionService {

    private final SolicitudDonacionRepository solicitudRepository;

    // guardar solicitud nueva
    public void guardarSolicitud(SolicitudDonacion solicitud) {
        solicitudRepository.save(solicitud);
    }

    // buscar x id de donacion
    public Optional<SolicitudDonacion> buscarPorSolicitudId(String solicitudId) {
        return solicitudRepository.findBySolicitudId(solicitudId);
    }

    // baja logica
    public boolean darDeBaja(Long organizacionId, String solicitudId) {
        return solicitudRepository.findBySolicitudId(solicitudId)
                .filter(s -> s.getOrganizacionId().equals(organizacionId))
                .map(solicitud -> {
                    solicitud.setEliminada(true);
                    solicitudRepository.save(solicitud);
                    return true;
                })
                .orElse(false);
    }
}

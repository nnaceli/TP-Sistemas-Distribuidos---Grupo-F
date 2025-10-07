package com.unla.grupoF.service;

import com.unla.grupoF.dto.BajaSolicitudDonacionDTO;
import com.unla.grupoF.dto.SolicitudDonacionDTO;
import com.unla.grupoF.entities.Categoria;
import com.unla.grupoF.entities.DonacionRequerida;
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
    public void guardarSolicitud(SolicitudDonacionDTO dto) throws Exception {
        if (buscarPorSolicitudId(dto.getSolicitudId()).isPresent()){
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
                && !s.isEliminada() )
                .map(solicitud -> {
                    solicitud.setEliminada(true);
                    solicitudRepository.save(solicitud);
                    return true;
                })
                .orElse(false);
    }
}

package com.unla.grupoF.controllers;

import com.unla.grupoF.dto.BajaSolicitudDonacionDTO;
import com.unla.grupoF.dto.SolicitudDonacionDTO;
import com.unla.grupoF.dto.TransferenciaDonacionDTO;
import com.unla.grupoF.service.DonacionesServiceProducer;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/donaciones")
public class DonacionesController {
    private final DonacionesServiceProducer donacionesService;

    public DonacionesController(DonacionesServiceProducer donacionesService) {
        this.donacionesService = donacionesService;
    }

    @PostMapping(value = "/solicitud", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> solicitudDonaciones(@RequestBody SolicitudDonacionDTO dto) {
        try {
            donacionesService.solicitudDonaciones(dto);
            return ResponseEntity.ok(Map.of("message", "Se envió el mensaje"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping(value = "/baja", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> bajaDonacion(@RequestBody BajaSolicitudDonacionDTO dto) {
        try {
            donacionesService.bajaDonacion(dto);
            return ResponseEntity.ok(Map.of("message", "Se envió el mensaje"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping(value = "/transferir", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> transferenciaDonacion(@RequestBody TransferenciaDonacionDTO dto) {
        try {
            donacionesService.transferenciaDonacion(dto);
            return ResponseEntity.ok(Map.of("message", "Se envió el mensaje"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }

    //TODO: listar las solicitudes de donacion

}
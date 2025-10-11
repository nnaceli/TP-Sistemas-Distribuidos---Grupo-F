package com.unla.grupoF.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unla.grupoF.KafkaProducer;
import com.unla.grupoF.dto.BajaSolicitudDonacionDTO;
import com.unla.grupoF.dto.SolicitudDonacionDTO;
import com.unla.grupoF.dto.TransferenciaDonacionDTO;
import com.unla.grupoF.service.DonacionesService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/donaciones")
public class DonacionesController {
    private final DonacionesService donacionesService;

    public DonacionesController(DonacionesService donacionesService) {
        this.donacionesService = donacionesService;
    }


    @PostMapping("/solicitud")
    public ResponseEntity<String> solicitudDonaciones(@RequestBody SolicitudDonacionDTO dto) {
        try {
            donacionesService.solicitudDonaciones(dto);
            return ResponseEntity.ok("Se envio el mensaje");
        }
        catch(Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/baja")
    public ResponseEntity<String> bajaDonacion(@RequestBody BajaSolicitudDonacionDTO dto) {
        try {
            donacionesService.bajaDonacion(dto);
            return ResponseEntity.ok("Se envio el mensaje");
        }
        catch(Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping("/transferir")
    public ResponseEntity<String> transferenciaDonacion(@RequestBody TransferenciaDonacionDTO dto) {
        try {
            donacionesService.transferenciaDonacion(dto);
            return ResponseEntity.ok("Se envio el mensaje");
        }
        catch(Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}

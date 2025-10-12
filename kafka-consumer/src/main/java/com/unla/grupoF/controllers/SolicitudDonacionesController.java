package com.unla.grupoF.controllers;


import com.unla.grupoF.dto.SolicitudDonacionDTO;
import com.unla.grupoF.service.SolicitudDonacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/solicitudDonaciones")
public class SolicitudDonacionesController {
    private final SolicitudDonacionService service;

    public SolicitudDonacionesController(SolicitudDonacionService service) {
        this.service = service;
    }

    @GetMapping("/listar")
    public ResponseEntity<Object> obtenerDonaciones(){
        try {
            List<SolicitudDonacionDTO> lista = service.obtenerSolicitudes();
            return ResponseEntity.ok().body(lista);
        }
        catch(Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}

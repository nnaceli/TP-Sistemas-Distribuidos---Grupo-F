package com.unla.grupof.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unla.grupof.entities.Donacion;
import com.unla.grupof.services.IDonacionService;

@RestController
@RequestMapping("/donaciones") // Esta es la ruta que Python intenta consumir.
public class DonacionController {

    // Inyectamos el servicio para acceder a la lógica de negocio.
    @Autowired
    private IDonacionService donacionService; 

    // Endpoint: GET http://localhost:8081/donaciones
    @GetMapping("/traer")
    public ResponseEntity<Object> getAll() {
        // Llama al servicio para obtener todas las donaciones.
        List<Donacion> donaciones = donacionService.getAll();
        
        // Retorna la lista con un estado HTTP 200 OK.
        return new ResponseEntity<>(donaciones, HttpStatus.OK);
    }
}
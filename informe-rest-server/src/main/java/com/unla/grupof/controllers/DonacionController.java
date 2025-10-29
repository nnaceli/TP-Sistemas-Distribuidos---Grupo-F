package com.unla.grupof.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.unla.grupof.entities.Donacion;
import com.unla.grupof.services.IDonacionService;

@RestController
@RequestMapping("/donaciones") // Esta es la ruta que Python intenta consumir.
@Tag(name = "Donaciones", description = "Operaciones relacionadas con donaciones")
public class DonacionController {

    // Inyectamos el servicio para acceder a la lógica de negocio.
    @Autowired
    private IDonacionService donacionService;

    @Operation(summary = "Obtener todas las donaciones", description = "Retorna una lista de todas las donaciones registradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Donaciones obtenidas exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno en la búsqueda de donaciones")
    })
    // Endpoint: GET http://localhost:8081/donaciones
    @GetMapping("/traer")
    public ResponseEntity<Object> getAll() {
        // Llama al servicio para obtener todas las donaciones.
        List<Donacion> donaciones = donacionService.getAll();
        
        // Retorna la lista con un estado HTTP 200 OK.
        return new ResponseEntity<>(donaciones, HttpStatus.OK);
    }
}
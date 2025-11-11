package com.unla.grupoF.controllers;

import com.unla.grupoF.entities.ComentarioEventoExterno;
import com.unla.grupoF.repositories.ComentarioRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/comentarios-eventos")
public class ComentarioController {

    private final ComentarioRepository comentarioRepository;

    public ComentarioController(ComentarioRepository comentarioRepository) {
        this.comentarioRepository = comentarioRepository;
    }

    // Endpoint para satisfacer el requerimiento de la vista:
    // Devuelve todos los comentarios asociados a un ID de evento externo
    @GetMapping("/{idEvento}")
    public List<ComentarioEventoExterno> getComentariosByEvento(@PathVariable Long idEvento) {
        // Usamos el método que definimos en el Repositorio
        return comentarioRepository.findAllByIdEventoExterno(idEvento);
    }
}
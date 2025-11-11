package com.unla.grupoF.repositories;

import com.unla.grupoF.entities.ComentarioEventoExterno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<ComentarioEventoExterno, Long> {
    
    // Método necesario para la pantalla de visualización:
    List<ComentarioEventoExterno> findAllByIdEventoExterno(Long idEventoExterno);
}
package com.unla.grupoF.repositories;

import com.unla.grupoF.entities.EventoExterno;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EventoExternoRepository extends JpaRepository<EventoExterno, Long> {
    Optional<EventoExterno> findByEventoId(String eventoId);
}

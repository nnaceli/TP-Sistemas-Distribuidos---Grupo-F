package com.unla.grupoF.repositories;

import com.unla.grupoF.entities.EventoExterno;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface EventoExternoRepository extends JpaRepository<EventoExterno, Long> {
    Optional<EventoExterno> findByEventoId(String eventoId);

    List<EventoExterno> findByDarDeBajaFalse();
}

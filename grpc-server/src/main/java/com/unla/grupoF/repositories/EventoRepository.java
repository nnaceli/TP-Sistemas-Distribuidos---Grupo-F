package com.unla.grupoF.repositories;

import com.unla.grupoF.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {
}

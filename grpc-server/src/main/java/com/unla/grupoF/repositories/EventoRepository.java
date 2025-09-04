package com.unla.grupoF.grpcserver.repositories;

import com.unla.grupoF.grpcserver.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {
}

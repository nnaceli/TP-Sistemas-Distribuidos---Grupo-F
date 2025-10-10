package com.unla.grupoF.repositories;

import com.unla.grupoF.entities.AdhesionEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdhesionEventoRepository extends JpaRepository<AdhesionEvento, Long> {
     boolean existsByEventoIdAndVoluntarioId(String eventoId, Long voluntarioId);
}

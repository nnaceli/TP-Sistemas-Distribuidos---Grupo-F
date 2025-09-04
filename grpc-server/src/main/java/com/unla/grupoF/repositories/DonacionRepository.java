package com.unla.grupoF.grpcserver.repositories;

import com.unla.grupoF.grpcserver.entities.Donacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonacionRepository extends JpaRepository<Donacion, Long> {
}

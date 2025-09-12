package com.unla.grupoF.repositories;

import com.unla.grupoF.entities.Donacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonacionRepository extends JpaRepository<Donacion, Long> {
}

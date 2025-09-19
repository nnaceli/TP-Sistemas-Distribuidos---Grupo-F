package com.unla.grupoF.repositories;

import com.unla.grupoF.entities.Donacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DonacionRepository extends JpaRepository<Donacion, Long> {

    Optional<Donacion> findByDescripcion(String descripcion);

}


package com.unla.grupof.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.grupof.entities.Donacion;

// Usamos Long porque es el tipo de dato del ID en Donacion.java
@Repository 
public interface IDonacionRepository extends JpaRepository<Donacion, Long> {
    // Spring Data JPA ya nos da todos los métodos que necesitamos (findAll(), save(), etc.)
}

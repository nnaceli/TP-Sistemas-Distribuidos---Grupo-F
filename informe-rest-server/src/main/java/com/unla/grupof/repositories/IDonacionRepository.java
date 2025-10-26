package com.unla.grupof.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unla.grupof.entities.Donacion;
import java.util.List;


@Repository 
public interface IDonacionRepository extends JpaRepository<Donacion, Long> {

        // Método explícito para obtener todas las donaciones.
        // Usamos JPQL (Java Persistence Query Language) para indicar que 
        // queremos seleccionar todos los objetos Donacion.
        // En JPA, "Donacion" se refiere al nombre de la clase Entity, no al nombre de la tabla.
    @Query("SELECT d FROM Donacion d")
    public List<Donacion> getAll();
}

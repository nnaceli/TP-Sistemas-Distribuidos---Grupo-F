package com.unla.grupoF.repositories;

import com.unla.grupoF.entities.Evento;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEventoRepository extends JpaRepository<Evento, Long> {

    Optional<Evento> findByNombreEvento(String nombre);

    @EntityGraph(attributePaths = {"miembros"})
    List<Evento> findAll();
}

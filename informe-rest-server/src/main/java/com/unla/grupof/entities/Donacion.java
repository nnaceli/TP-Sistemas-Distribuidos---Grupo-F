package com.unla.grupof.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Asegúrate de que esta enumeración esté definida en el mismo paquete o importada
// Si tu Categoría es un Enum de Java, debe verse así:
enum Categoria {
    ROPA,
    ALIMENTOS,
    JUGUETES,
    UTILES_ESCOLARES
}

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Donacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    // Usamos EnumType.STRING para que en la BD se guarde el nombre del Enum (ej: "ROPA")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Categoria categoria; 

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private int cantidad;

    // Auditoría
    @Column(nullable = false)
    private boolean eliminado = false;
    
    // Campo de auditoría necesario para el informe
    @Column(name = "fecha_creacion", nullable = true)
    private LocalDateTime fechaCreacion;

    // Campos de auditoría necesarios para el informe
    private String usernameCreacion;
    private LocalDateTime fechaModificacion;
    private String usernameModificacion;
}

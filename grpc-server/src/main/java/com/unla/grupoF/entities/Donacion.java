package com.unla.grupoF.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Donacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Categoria categoria;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private int cantidad;

    private boolean eliminado = false;

    // Auditoría
    private LocalDateTime fechaAlta;
    private String usuarioAlta;
    private LocalDateTime fechaModificacion;
    private String usuarioModificacion;
}

package com.unla.grupoF.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    private String telefono;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String clave; // encriptada

    @Enumerated(EnumType.STRING)
    private Roles rol;

    private boolean activo = true;
}

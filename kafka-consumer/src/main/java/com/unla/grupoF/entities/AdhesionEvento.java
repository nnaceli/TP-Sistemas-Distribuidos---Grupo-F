package com.unla.grupoF.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdhesionEvento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventoId;
    private Long organizacionId;
    private Long voluntarioId;

    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
}

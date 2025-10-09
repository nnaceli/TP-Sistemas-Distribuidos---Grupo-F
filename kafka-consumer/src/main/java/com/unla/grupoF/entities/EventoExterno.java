package com.unla.grupoF.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventoExterno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long organizacionId;

    @Column(nullable = false, unique = true)
    private String eventoId;

    private String nombre;

    private String descripcion;

    private LocalDateTime fechaHora;

    private boolean darDeBaja = false;
}

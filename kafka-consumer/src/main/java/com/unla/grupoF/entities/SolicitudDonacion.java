package com.unla.grupoF.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SolicitudDonacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long organizacionId;

    @Column(nullable = false, unique = true)
    private String solicitudId;

    @OneToMany(mappedBy = "solicitudDonacion", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference //Resolvemos problema de recursividad infinita con DonacionRequerida
    private List<DonacionRequerida> donaciones;

    @Column(nullable = false)
    private boolean eliminada = false;
}
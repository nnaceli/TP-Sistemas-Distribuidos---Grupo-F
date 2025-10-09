package com.unla.grupoF.entities;

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
    private List<DonacionRequerida> donaciones;

    @Column(nullable = false)
    private boolean eliminada = false;
}
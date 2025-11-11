package com.unla.grupoF.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comentarios_eventos") // Nombre de la tabla en PostgreSQL
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ComentarioEventoExterno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_evento_externo", nullable = false)
    private Long idEventoExterno; 

    @Column(name = "id_organizacion", nullable = false)
    private Long idOrganizacion;

    @Column(name = "id_voluntario", nullable = false)
    private Long idVoluntario;

    // Datos del voluntario y comentario
    @Column(name = "nombre_voluntario")
    private String nombreVoluntario;

    @Column(name = "apellido_voluntario")
    private String apellidoVoluntario;

    @Column(name = "comentario", columnDefinition = "TEXT")
    private String comentario;

    // Fecha y hora del comentario
    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    // --- GETTERS (Explícitos para robustez) ---

    public Long getId() {
        return id;
    }

    public Long getIdEventoExterno() {
        return idEventoExterno;
    }

    public Long getIdOrganizacion() {
        return idOrganizacion;
    }

    public Long getIdVoluntario() {
        return idVoluntario;
    }

    public String getNombreVoluntario() {
        return nombreVoluntario;
    }

    public String getApellidoVoluntario() {
        return apellidoVoluntario;
    }

    public String getComentario() {
        return comentario;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }


    // --- SETTERS (La parte que no estaba reconociendo) ---

    // El setter de ID autogenerado no se necesita, pero lo incluyo por si acaso.
    public void setId(Long id) {
        this.id = id;
    }

    public void setIdEventoExterno(Long idEventoExterno) {
        this.idEventoExterno = idEventoExterno;
    }

    public void setIdOrganizacion(Long idOrganizacion) {
        this.idOrganizacion = idOrganizacion;
    }

    public void setIdVoluntario(Long idVoluntario) {
        this.idVoluntario = idVoluntario;
    }

    public void setNombreVoluntario(String nombreVoluntario) {
        this.nombreVoluntario = nombreVoluntario;
    }

    public void setApellidoVoluntario(String apellidoVoluntario) {
        this.apellidoVoluntario = apellidoVoluntario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

}
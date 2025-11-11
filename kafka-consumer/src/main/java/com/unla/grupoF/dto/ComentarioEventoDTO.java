package com.unla.grupoF.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter 
@Setter
@NoArgsConstructor 
@AllArgsConstructor
@Builder
public class ComentarioEventoDTO {

    private Long idEventoExterno;
    private Long idOrganizacion;
    private Long idVoluntario;
    private String nombreVoluntario;
    private String apellidoVoluntario;
    private String comentario;
    private LocalDateTime fechaHora; 

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

    // --- Setters ---

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
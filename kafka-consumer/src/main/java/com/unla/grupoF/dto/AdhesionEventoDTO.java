package com.unla.grupoF.dto;

import lombok.Data;

@Data
public class AdhesionEventoDTO {
    private String eventoId;
    private Long organizacionId;
    private Long voluntarioId;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
}

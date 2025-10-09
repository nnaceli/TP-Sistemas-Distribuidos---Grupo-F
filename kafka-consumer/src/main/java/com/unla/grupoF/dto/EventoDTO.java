package com.unla.grupoF.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventoDTO {
    private Long organizacionId;
    private String eventoId;
    private String nombre;
    private String descripcion;
    private LocalDateTime fechaHora;
}

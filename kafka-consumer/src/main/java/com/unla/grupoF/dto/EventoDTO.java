package com.unla.grupoF.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class EventoDTO {
    private Long organizacionId;
    private String eventoId;
    private String nombre;
    private String descripcion;
    @JsonProperty("fechaHora")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    //TODO: El publicar evento no esta aceptando la fecha (el mensaje se publica en null)
    private LocalDateTime fechaHora;
}

package com.unla.grupoF.dto;

import com.unla.grupoF.entities.Categoria;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DonacionRequeridaDTO {
    private String categoria;
    private String descripcion;

}

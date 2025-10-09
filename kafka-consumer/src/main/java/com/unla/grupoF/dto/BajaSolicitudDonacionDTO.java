package com.unla.grupoF.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BajaSolicitudDonacionDTO {
    private Long organizacionId;
    private String solicitudId;
}

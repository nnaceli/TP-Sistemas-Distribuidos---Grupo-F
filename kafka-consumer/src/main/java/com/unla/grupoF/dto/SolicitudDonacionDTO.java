package com.unla.grupoF.dto;

import java.util.List;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SolicitudDonacionDTO {
    private Long organizacionId;
    private String solicitudId;
    private List<DonacionRequeridaDTO> donaciones;
}

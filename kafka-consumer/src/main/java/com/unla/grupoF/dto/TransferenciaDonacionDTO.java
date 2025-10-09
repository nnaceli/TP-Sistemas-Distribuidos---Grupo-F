package com.unla.grupoF.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferenciaDonacionDTO {
    private String solicitudId;
    private Long organizacionId;
    private List<DonacionTransferidaDTO> donaciones;

    public record DonacionTransferidaDTO(String categoria, String descripcion, int cantidad) {
    }
}

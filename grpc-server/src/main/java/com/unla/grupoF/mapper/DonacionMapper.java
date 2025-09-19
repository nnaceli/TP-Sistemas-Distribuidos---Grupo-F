package com.unla.grupoF.mapper;


import com.unla.grupoF.entities.Categoria;
import com.unla.grupoF.entities.Donacion;
import com.unla.grupoF.service.DonacionOuterClass;

public class DonacionMapper {

    public DonacionMapper() {
    }

    // Convierte de Protobuf DTO a Entidad
    public Donacion toEntity(DonacionOuterClass.DonacionDTO dto) {
        return Donacion.builder()
                .categoria(Categoria.valueOf(dto.getCategoria().name())) 
                .descripcion(dto.getDescripcion())
                .cantidad((int) dto.getCantidad())
                .build();
    }

    // Convierte de Entidad a Protobuf Donacion
    public DonacionOuterClass.Donacion fromEntity(Donacion entity) {
        return DonacionOuterClass.Donacion.newBuilder()
                .setId(entity.getId())
                .setCategoria(DonacionOuterClass.DonacionCategoria.valueOf(entity.getCategoria().name())) // Enum Java → Enum proto
                .setDescripcion(entity.getDescripcion())
                .setCantidad(entity.getCantidad())
                .setEliminado(entity.isEliminado())
                .build();
    }

   // Convierte de Entidad a Protobuf DonacionDTO
    public DonacionOuterClass.DonacionDTO toDTO(Donacion entity) {
        return DonacionOuterClass.DonacionDTO.newBuilder()
            .setCategoria(DonacionOuterClass.DonacionCategoria.valueOf(entity.getCategoria().name()))
            .setDescripcion(entity.getDescripcion())
            .setCantidad(entity.getCantidad())
            .build();
}
}

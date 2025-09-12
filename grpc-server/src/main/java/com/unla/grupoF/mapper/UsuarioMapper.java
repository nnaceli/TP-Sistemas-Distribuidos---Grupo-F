package com.unla.grupoF.mapper;

import com.unla.grupoF.entities.Roles;
import com.unla.grupoF.entities.Usuario;
import com.unla.grupoF.service.UsuarioOuterClass;

public class UsuarioMapper {

    public UsuarioMapper() {
    }

    public Roles toRoleEnum( UsuarioOuterClass.Rol rol) {
        try {
            return Roles.valueOf(rol.getNombre());
        } catch (IllegalArgumentException e) {
            return Roles.VOLUNTARIO; // Por defecto el menor rol
        }
    }

    public UsuarioOuterClass.Rol toRolProtobuf(Roles rol) {
        String nombreRol;
        switch (rol) {
            case PRESIDENTE:
               nombreRol= "PRESIDENTE";
                break;
            case VOCAL:
                nombreRol = "VOCAL";
                break;
            case COORDINADOR:
                nombreRol = "COORDINADOR";
                break;
            case VOLUNTARIO:
            default:
                nombreRol = "VOLUNTARIO";
        }
        return UsuarioOuterClass.Rol.newBuilder()
                .setNombre( nombreRol)
                .build();
    }

    public UsuarioOuterClass.UsuarioDTO toDTO(Usuario usuario) {

        return UsuarioOuterClass.UsuarioDTO.newBuilder()
                .setUsername(usuario.getUsername())
                .setNombre(usuario.getNombre())
                .setApellido(usuario.getApellido())
                .setApellido( usuario.getApellido())
                .setTelefono(usuario.getTelefono())
                .setEmail(usuario.getEmail())
                .setRol(toRolProtobuf(usuario.getRol()))
                .build();
    }

    public Usuario toEntity( UsuarioOuterClass.UsuarioDTO usuarioDTO) {

        return Usuario.builder()
                .username(usuarioDTO.getUsername())
                .nombre(usuarioDTO.getNombre())
                .apellido(usuarioDTO.getApellido())
                .telefono(usuarioDTO.getTelefono())
                .email(usuarioDTO.getEmail())
                .rol(toRoleEnum(usuarioDTO.getRol()))
                .build();
    }
}

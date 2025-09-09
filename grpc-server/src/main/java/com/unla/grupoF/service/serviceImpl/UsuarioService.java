package com.unla.grupoF.service.serviceImpl;

import com.unla.grupoF.entities.Usuario;
import com.unla.grupoF.mapper.UsuarioMapper;
import com.unla.grupoF.repositories.UsuarioRepository;
import com.unla.grupoF.service.UsuarioOuterClass;
import com.unla.grupoF.service.UsuarioServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GRpcService
public class UsuarioService extends UsuarioServiceGrpc.UsuarioServiceImplBase {

    @Autowired
    private UsuarioRepository repository;

    private UsuarioMapper usuarioMapper = new UsuarioMapper();

    //ALTA (recibe DTO de la vista, genera clave, guarda Usuario en la bdd y devuelve DTO))
    @Override
    public void createUsuario(UsuarioOuterClass.UsuarioDTO request, StreamObserver<UsuarioOuterClass.UsuarioDTO> responseObserver) {
        //TODO: chequear el rol PRESIDENTE del responseObserver (el admin)

        //Chequear que no exista un usuario con el mismo username o email
        if (repository.findByUsername(request.getEmail()).isPresent() ||
                repository.findByEmail(request.getEmail()).isPresent()) {
            responseObserver.onError(new Throwable("El username o email ya existe"));
        }
        Usuario usuario = usuarioMapper.toEntity(request);
        //TODO: generacion de clave encriptada con un password encoder
        usuario.setClave("clave-encriptada"); // modificar luego
        Usuario savedUsuario = repository.save(usuario);
        UsuarioOuterClass.UsuarioDTO response = usuarioMapper.toDTO(savedUsuario);
        //TODO: triggear mandar contraseña por mail
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    //MODIFICACION (todos los campos excepto la clave, devuelve DTO)
    @Override
    public void updateUsuario(UsuarioOuterClass.UsuarioDTO request, StreamObserver<UsuarioOuterClass.UsuarioDTO> responseObserver) {
        //TODO: chequear el rol PRESIDENTE del responseObserver (el admin)
        if (repository.findByUsername(request.getUsername()).isEmpty()) {
            responseObserver.onError(new Throwable("El usuario no existe"));
        } else if (request.getEmail().isEmpty() || request.getNombre().isEmpty() || request.getApellido().isEmpty()
                || request.getTelefono().isEmpty() || request.getRol() == null
        ) {
            responseObserver.onError(new Throwable("Faltan campos a modificar"));
        } else {
            Usuario usuario = usuarioMapper.toEntity(request);
            Usuario updatedUsuario = repository.save(usuario);
            UsuarioOuterClass.UsuarioDTO response = usuarioMapper.toDTO(updatedUsuario);
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        //BAJA (solo baja lógica)

        //TRAER USUARIOS (todos los que esten activos)
    }
}

package com.unla.grupoF.service.serviceImpl;

import com.unla.grupoF.entities.Usuario;
import com.unla.grupoF.mapper.UsuarioMapper;
import com.unla.grupoF.repositories.IUsuarioRepository;
import com.unla.grupoF.service.UsuarioOuterClass;
import com.unla.grupoF.service.UsuarioServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GRpcService
public class UsuarioServiceImpl extends UsuarioServiceGrpc.UsuarioServiceImplBase {

    @Autowired
    private IUsuarioRepository repository;

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
        usuario.setActivo(true); // por defecto activo
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
        if (request.getUsername().isEmpty()) {
            responseObserver.onError(new Throwable("Falta el username del usuario a modificar"));
        } else if (repository.findByUsername(request.getUsername()).isEmpty()) {
            responseObserver.onError(new Throwable("El usuario no existe"));
        } else if (request.getEmail().isEmpty() || request.getNombre().isEmpty() || request.getApellido().isEmpty()
                || request.getTelefono().isEmpty() || request.getRol().getNombre().isEmpty()
        ) {
            responseObserver.onError(new Throwable("Faltan campos a modificar"));
        } else {
            Usuario usuario = usuarioMapper.toEntity(request);
            usuario.setClave(repository.findByUsername(request.getUsername()).get().getClave()); // mantener la clave actual
            Usuario updatedUsuario = repository.save(usuario);
            UsuarioOuterClass.UsuarioDTO response = usuarioMapper.toDTO(updatedUsuario);
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }

    //BAJA (solo baja lógica)
    @Override
    public void deleteUsuario(UsuarioOuterClass.Username request, StreamObserver<UsuarioOuterClass.Empty> responseObserver) {

        try {
            Usuario usuario = repository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new RuntimeException("Usuario con ese username no encontrado"));
            usuario.setActivo(false);
            repository.save(usuario);
            responseObserver.onNext(UsuarioOuterClass.Empty.newBuilder().build());
            responseObserver.onCompleted();
        } catch (RuntimeException e) {
            responseObserver.onError(new Throwable("Error al buscar el usuario: " + e.getMessage()));
        } catch (Exception ex) {
            responseObserver.onError(new Throwable("Error al eliminar el usuario: " + ex.getMessage()));
        }
    }

    //TRAER USUARIOS (todos los que esten activos)
    @Override
    public void listUsuarios(UsuarioOuterClass.Empty request, StreamObserver<UsuarioOuterClass.UsuarioListResponse> responseObserver) {
        try {
            UsuarioOuterClass.UsuarioListResponse.Builder listUsuarios = UsuarioOuterClass.UsuarioListResponse.newBuilder();
            repository.findAll().stream()
                    .filter(Usuario::isActivo)
                    .forEach(usuario -> {
                        listUsuarios.addUsuarios(
                                usuarioMapper.fromEntity(usuario)
                        );
                    });
            responseObserver.onNext(listUsuarios.build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(new Throwable("Error al listar los usuarios: " + e.getMessage()));
        }
    }


    @Override
    public void getUsuario(UsuarioOuterClass.Username request, StreamObserver<UsuarioOuterClass.Usuario> responseObserver) {
        try {
            repository.findByUsername(request.getUsername());
            Usuario usuario = repository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new Exception("Usuario con ese username no encontrado"));

            responseObserver.onNext(usuarioMapper.fromEntity(usuario));
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(new Throwable("Error al buscar el usuario: " + e.getMessage()));
        }

    }

}

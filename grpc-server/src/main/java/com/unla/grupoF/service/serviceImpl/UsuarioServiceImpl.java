package com.unla.grupoF.service.serviceImpl;

import com.unla.grupoF.entities.Usuario;
import com.unla.grupoF.mapper.UsuarioMapper;
import com.unla.grupoF.repositories.IUsuarioRepository;
import com.unla.grupoF.service.UsuarioOuterClass;
import com.unla.grupoF.service.UsuarioServiceGrpc;
import com.unla.grupoF.utils.EmailService;
import com.unla.grupoF.utils.LoginInterceptor;
import com.unla.grupoF.utils.SecurityUtil;
import io.grpc.Context;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Set;

@GRpcService
public class UsuarioServiceImpl extends UsuarioServiceGrpc.UsuarioServiceImplBase {

    @Autowired
    private IUsuarioRepository repository;

   @Autowired
   private EmailService emailService;

   private SecurityUtil securityUtil = new SecurityUtil();

    private UsuarioMapper usuarioMapper = new UsuarioMapper();

    public UsuarioServiceImpl() throws IOException {
    }

    //ALTA (recibe DTO de la vista, genera clave, guarda Usuario en la bdd y devuelve DTO))
    @Override
    public void createUsuario(UsuarioOuterClass.UsuarioDTO request, StreamObserver<UsuarioOuterClass.UsuarioDTO> responseObserver) {
        securityUtil.hasRole(Context.key(LoginInterceptor.ROL_HEADER_KEY.name()).toString(), Set.of("PRESIDENTE"));
        //Chequear que no exista un usuario con el mismo username o email
        if (repository.findByUsername(request.getEmail()).isPresent() ||
                repository.findByEmail(request.getEmail()).isPresent()) {
            StatusRuntimeException statusException = Status.ALREADY_EXISTS
                    .withDescription("El username o email ya existe")
                    .asRuntimeException();
            responseObserver.onError(statusException);
            return;
        }
        Usuario usuario = usuarioMapper.toEntity(request);

        // Generar una clave aleatoria
        String claveGenerada = securityUtil.randomPassword(8); // longitud de 8 caracteres
        usuario.setClave( securityUtil.encodePassword( claveGenerada ) );

        usuario.setActivo(true); // por defecto activo
        Usuario savedUsuario = repository.save(usuario);
        UsuarioOuterClass.UsuarioDTO response = usuarioMapper.toDTO(savedUsuario);

        //Enviarla por mail al usuario
        try {
            emailService.sendNewPassword(usuario.getEmail(), claveGenerada);
        } catch (Exception e) {
            // si no se pudo enviar el mail, eliminar el usuario creado
            repository.delete( usuario);
            StatusRuntimeException statusException = Status.ABORTED
                    .withDescription(e.getMessage())
                    .asRuntimeException();
            responseObserver.onError(statusException);
            return;
        }
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    //MODIFICACION (todos los campos excepto la clave, devuelve DTO)
    @Override
    public void updateUsuario(UsuarioOuterClass.UsuarioDTO request, StreamObserver<UsuarioOuterClass.UsuarioDTO> responseObserver) {
        securityUtil.hasRole(Context.key(LoginInterceptor.ROL_HEADER_KEY.name()).toString(), Set.of("PRESIDENTE"));
        if (request.getUsername().isEmpty()) {
            StatusRuntimeException statusException = Status.NOT_FOUND
                    .withDescription("Falta el username del usuario a modificar")
                    .asRuntimeException();
            responseObserver.onError(statusException);
        } else if (repository.findByUsername(request.getUsername()).isEmpty()) {
            StatusRuntimeException statusException = Status.NOT_FOUND
                    .withDescription("El usuario no existe")
                    .asRuntimeException();
            responseObserver.onError(statusException);
        } else if (request.getEmail().isEmpty() || request.getNombre().isEmpty() || request.getApellido().isEmpty()
                || request.getTelefono().isEmpty() || request.getRol().getNombre().isEmpty()
        ) {
            StatusRuntimeException statusException = Status.NOT_FOUND
                    .withDescription("Faltan campos a modificar")
                    .asRuntimeException();
            responseObserver.onError(statusException);
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
        securityUtil.hasRole(Context.key(LoginInterceptor.ROL_HEADER_KEY.name()).toString(), Set.of("PRESIDENTE"));
        try {
            Usuario usuario = repository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new RuntimeException("Usuario con ese username no encontrado"));
            usuario.setActivo(false);
            repository.save(usuario);
            responseObserver.onNext(UsuarioOuterClass.Empty.newBuilder().build());
            responseObserver.onCompleted();
        } catch (RuntimeException e) {
            StatusRuntimeException statusException = Status.NOT_FOUND
                    .withDescription("Error al buscar el usuario: " + e.getMessage())
                    .asRuntimeException();
            responseObserver.onError(statusException);
        } catch (Exception ex) {
            StatusRuntimeException statusException = Status.NOT_FOUND
                    .withDescription("Error al eliminar el usuario: " + ex.getMessage())
                    .asRuntimeException();
            responseObserver.onError(statusException);
        }
    }

    //TRAER USUARIOS (todos los que esten activos)
    @Override
    public void listUsuarios(UsuarioOuterClass.Empty request, StreamObserver<UsuarioOuterClass.UsuarioListResponse> responseObserver) {
        securityUtil.hasRole(Context.key(LoginInterceptor.ROL_HEADER_KEY.name()).toString(), Set.of("PRESIDENTE"));
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
            StatusRuntimeException statusException = Status.NOT_FOUND
                    .withDescription("Error al listar los usuarios: " + e.getMessage())
                    .asRuntimeException();
            responseObserver.onError(statusException);
        }
    }


    @Override
    public void getUsuario(UsuarioOuterClass.Username request, StreamObserver<UsuarioOuterClass.Usuario> responseObserver) {
        securityUtil.hasRole(Context.key(LoginInterceptor.ROL_HEADER_KEY.name()).toString(), Set.of("PRESIDENTE"));
        try {
            repository.findByUsername(request.getUsername());
            Usuario usuario = repository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new Exception("Usuario con username: "+ request.getUsername() + " no encontrado"));

            responseObserver.onNext(usuarioMapper.fromEntity(usuario));
            responseObserver.onCompleted();
        } catch (Exception e) {
            StatusRuntimeException statusException = Status.NOT_FOUND
                    .withDescription("Error al buscar el usuario: " + e.getMessage())
                    .asRuntimeException();
            responseObserver.onError(statusException);
        }

    }

}

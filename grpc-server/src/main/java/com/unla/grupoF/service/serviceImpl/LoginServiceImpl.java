package com.unla.grupoF.service.serviceImpl;

import com.unla.grupoF.entities.Usuario;
import com.unla.grupoF.mapper.UsuarioMapper;
import com.unla.grupoF.repositories.IUsuarioRepository;
import com.unla.grupoF.service.Login;
import com.unla.grupoF.service.LoginServiceGrpc;
import com.unla.grupoF.service.UsuarioOuterClass;
import com.unla.grupoF.utils.SecurityUtil;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Optional;

@GRpcService
public class LoginServiceImpl extends LoginServiceGrpc.LoginServiceImplBase {
    @Autowired
    private IUsuarioRepository usuarioRepository;

    private final UsuarioMapper mapper = new UsuarioMapper();

    private final SecurityUtil securityUtil = new SecurityUtil();

    public LoginServiceImpl() throws IOException {
    }

    @Override
    public void login(Login.LoginRequest request,
                      io.grpc.stub.StreamObserver<Login.LoginResponse> responseObserver) {
        String username = request.getUsername();
        String password = request.getPassword();

        try {
            // verificar que exista usuario
            Optional<Usuario> usuario = usuarioRepository.findByUsername(username);
            if (usuario.isEmpty()) {
                throw new RuntimeException("Usuario no encontrado: " + username);
            }

            // verificar que la contraseña sea correcta
            if (!securityUtil.checkPassword(password,
                    usuario.get().getClave())) {
                throw new RuntimeException("Contraseña incorrecta");
            }
            // generar token para autorizacion de usuario
            String token = securityUtil.generateToken(username, usuario.get().getRol().name());

            Login.LoginResponse response = Login.LoginResponse.newBuilder()
                    .setToken(token)
                    .setRol(
                            mapper.toRolProtobuf(usuario.get().getRol()))
                    .setUsername(
                            UsuarioOuterClass.Username.newBuilder().
                                    setUsername( usuario.get().getUsername() ).build() )
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (RuntimeException e) {
            StatusRuntimeException statusException = Status.UNAUTHENTICATED
                    .withDescription("Error al iniciar sesión: " + e.getMessage())
                    .asRuntimeException();
            responseObserver.onError(statusException);
        }
    }
}

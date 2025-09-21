package com.unla.grupoF.utils;

import io.grpc.*;
import org.lognet.springboot.grpc.GRpcGlobalInterceptor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@GRpcGlobalInterceptor
public class LoginInterceptor implements ServerInterceptor {

    @Autowired
    private SecurityUtil securityUtil;

    public static final Context.Key<String> ROLE_CONTENT_KEY = Context.key("rol");
    public static final Context.Key<String> USER_CONTENT_KEY = Context.key("usuario");

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
            ServerCall<ReqT, RespT> call,
            Metadata headers,
            ServerCallHandler<ReqT, RespT> next) {
        //Extraer el servicio llamado
        String metodo = call.getMethodDescriptor().getFullMethodName();

        List<String> metodosPermitidos = List.of(
                "LoginService/Login"
        );

        if (metodosPermitidos.contains(metodo)){
            return Contexts.interceptCall(Context.current(),call,headers,next);
        }
        System.out.println("Interceptando llamada al metodo: " + metodo);
        //Extraer JWT token que el cliente envio en los metadatos
        String token = headers.get(Metadata.Key.of("Authorization",Metadata.ASCII_STRING_MARSHALLER));

        if(token != null && token.startsWith("Bearer ")){
            token = token.substring(7); //despues del Bearer

            try {
                System.out.println("Verificando token: ");
               String authenticatedUser= securityUtil.getUsernameFromToken(token);
               String rol= securityUtil.getRolFromToken(token);

               Context contextWithRol = Context.current()
                       .withValue(USER_CONTENT_KEY, authenticatedUser)
                       .withValue(ROLE_CONTENT_KEY, rol);

                return Contexts.interceptCall(contextWithRol, call, headers, next);
            }catch (Exception e){
                call.close(Status.UNAUTHENTICATED.withDescription("Token invalido"),headers);
                return new ServerCall.Listener<ReqT>() {};
            }
        }
        else {
            call.close(Status.UNAUTHENTICATED.withDescription("No se ha encontrado token"),headers);
            return new ServerCall.Listener<ReqT>() {};
        }
    }
}

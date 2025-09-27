package com.unla.grupoF.service.serviceImpl;

import com.unla.grupoF.entities.Evento;
import com.unla.grupoF.entities.Usuario;
import com.unla.grupoF.mapper.EventoMapper;
import com.unla.grupoF.repositories.IEventoRepository;
import com.unla.grupoF.repositories.IUsuarioRepository;
import com.unla.grupoF.service.EventoSolidarioOuterClass;
import com.unla.grupoF.service.EventoSolidarioServiceGrpc;
import com.unla.grupoF.service.UsuarioOuterClass;
import com.unla.grupoF.utils.LoginInterceptor;
import com.unla.grupoF.utils.SecurityUtil;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@GRpcService
public class EventoServiceImpl extends EventoSolidarioServiceGrpc.EventoSolidarioServiceImplBase {

    @Autowired
    private IEventoRepository eventoRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private SecurityUtil securityUtil;

    private final EventoMapper eventoMapper = new EventoMapper();

    // CREAR EVENTO
    @Override
    public void createEventoSolidario(EventoSolidarioOuterClass.EventoSolidarioDTO request, StreamObserver<EventoSolidarioOuterClass.EventoSolidario> responseObserver) {
        try {
            if (!securityUtil.hasRole(
                    LoginInterceptor.ROLE_CONTENT_KEY.get(),
                    Set.of("PRESIDENTE", "COORDINADOR"))) {
                throw new RuntimeException("No tiene permisos para realizar esta acción");
            }

            LocalDateTime fechaEvento = LocalDateTime.ofEpochSecond(
                    request.getFecha().getSeconds(),
                    request.getFecha().getNanos(),
                    ZoneOffset.UTC
            );

            if (fechaEvento.isBefore(LocalDateTime.now())) {
                throw new RuntimeException("La fecha del evento debe ser futura");
            }

            // Verificar que los usuarios existan
            if (!request.getMiembrosList().isEmpty()) {
                for (UsuarioOuterClass.Usuario usuarioProto : request.getMiembrosList()) {
                    if (usuarioRepository.findByUsername(usuarioProto.getUsername()).isEmpty()) {
                        throw new RuntimeException("Usuario no encontrado: " + usuarioProto.getUsername());
                    }
                }
            }

            Evento evento = eventoMapper.toEntity(request);
            Evento saved = eventoRepository.save(evento);

            responseObserver.onNext(eventoMapper.fromEntity(saved));
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(
                    Status.INVALID_ARGUMENT.withDescription("Error al crear evento: " + e.getMessage()).asRuntimeException()
            );
        }
    }

    // OBTENER EVENTO POR ID
    @Override
    public void getEventoSolidario(EventoSolidarioOuterClass.EventoIdRequest request, StreamObserver<EventoSolidarioOuterClass.EventoSolidario> responseObserver) {
        try {
            Evento evento = eventoRepository.findById(request.getId())
                    .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

            responseObserver.onNext(eventoMapper.fromEntity(evento));
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(
                    Status.NOT_FOUND.withDescription("Error al obtener evento: " + e.getMessage()).asRuntimeException()
            );
        }
    }

    // MODIFICAR EVENTO
    @Override
    public void updateEventoSolidario(EventoSolidarioOuterClass.EventoSolidarioDTO request, StreamObserver<EventoSolidarioOuterClass.EventoSolidario> responseObserver) {
        try {
            if (!securityUtil.hasRole(
                    LoginInterceptor.ROLE_CONTENT_KEY.get(),
                    Set.of("PRESIDENTE", "COORDINADOR"))) {
                throw new RuntimeException("No tiene permisos para realizar esta acción");
            }

            Evento evento = eventoRepository.findByNombreEvento(request.getNombre())
                    .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

            LocalDateTime fechaEvento = LocalDateTime.ofEpochSecond(
                    request.getFecha().getSeconds(),
                    request.getFecha().getNanos(),
                    ZoneOffset.UTC
            );

            if (fechaEvento.isBefore(LocalDateTime.now())) {
                throw new RuntimeException("No se puede modificar un evento pasado");
            }

            evento.setDescripcion(request.getDescripcion());
            evento.setFechaHora(fechaEvento);

            if (!request.getMiembrosList().isEmpty()) {
                Set<Usuario> miembros = request.getMiembrosList().stream()
                        .map(usuarioProto -> usuarioRepository.findByUsername(usuarioProto.getUsername())
                                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + usuarioProto.getUsername())))
                        .collect(Collectors.toSet());
                evento.setMiembros(miembros);
            }

            Evento updated = eventoRepository.save(evento);

            responseObserver.onNext(eventoMapper.fromEntity(updated));
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(
                    Status.INVALID_ARGUMENT.withDescription("Error al modificar evento: " + e.getMessage()).asRuntimeException()
            );
        }
    }

    // ELIMINAR EVENTO
    
    @Override
    public void deleteEventoSolidario(EventoSolidarioOuterClass.EventoIdRequest request,  StreamObserver<EventoSolidarioOuterClass.EventoSolidario> responseObserver) {
        try {
            if (!securityUtil.hasRole(
                    LoginInterceptor.ROLE_CONTENT_KEY.get(),
                    Set.of("PRESIDENTE", "COORDINADOR"))) {
                throw new RuntimeException("No tiene permisos para realizar esta acción");
            }

            Evento evento = eventoRepository.findById(request.getId())
                    .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

            if (evento.getFechaHora().isBefore(LocalDateTime.now())) {
                throw new Throwable("No se pueden eliminar eventos pasados");
            }

            eventoRepository.delete(evento);

            responseObserver.onNext(eventoMapper.fromEntity(evento));
            responseObserver.onCompleted();
        } catch (Throwable e) {
            StatusRuntimeException statusException = Status.NOT_FOUND
                    .withDescription("Error al eliminar evento: " + e.getMessage())
                    .asRuntimeException();
            responseObserver.onError(statusException);
        }
    }

    // LISTAR EVENTOS
    @Override
    public void listEventoSolidarios(UsuarioOuterClass.Empty request,  StreamObserver<EventoSolidarioOuterClass.EventoSolidarioListResponse> responseObserver) {
        try {
            List<Evento> eventos = eventoRepository.findAll();

            EventoSolidarioOuterClass.EventoSolidarioListResponse.Builder response =
                    EventoSolidarioOuterClass.EventoSolidarioListResponse.newBuilder();

            eventos.forEach(evento -> response.addEventos(eventoMapper.fromEntity(evento)));

            responseObserver.onNext(response.build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(
                    Status.INTERNAL.withDescription("Error al listar eventos: " + e.getMessage()).asRuntimeException()
            );
        }
    }
}
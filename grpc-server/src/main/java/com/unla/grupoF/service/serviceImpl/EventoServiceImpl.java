package com.unla.grupoF.service.serviceImpl;

import com.unla.grupoF.entities.Evento;
import com.unla.grupoF.entities.Usuario;
import com.unla.grupoF.mapper.EventoMapper;
import com.unla.grupoF.repositories.EventoRepository;
import com.unla.grupoF.repositories.IUsuarioRepository;
import com.unla.grupoF.service.EventoSolidarioOuterClass;
import com.unla.grupoF.service.EventoSolidarioServiceGrpc;
import com.unla.grupoF.service.UsuarioOuterClass;
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
    private EventoRepository eventoRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    private final EventoMapper eventoMapper = new EventoMapper();

    // CREAR EVENTO
    @Override
    public void createEventoSolidario(EventoSolidarioOuterClass.EventoSolidarioDTO request,
                                      StreamObserver<EventoSolidarioOuterClass.EventoSolidario> responseObserver) {
        try {
            LocalDateTime fechaEvento = LocalDateTime.ofEpochSecond(
                    request.getFecha().getSeconds(),
                    request.getFecha().getNanos(),
                    ZoneOffset.UTC
            );

            if (fechaEvento.isBefore(LocalDateTime.now())) {
                responseObserver.onError(new Throwable("La fecha del evento debe ser futura"));
                return;
            }

            Evento evento = eventoMapper.toEntity(request);

            Evento saved = eventoRepository.save(evento);

            EventoSolidarioOuterClass.EventoSolidario response = eventoMapper.fromEntity(saved);
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(new Throwable("Error al crear evento: " + e.getMessage()));
        }
    }

    @Override
    public void getEventoSolidario(EventoSolidarioOuterClass.EventoSolidario request,
                                   StreamObserver<EventoSolidarioOuterClass.EventoSolidario> responseObserver) {
        try {
            Evento evento = eventoRepository.findById(request.getId())
                    .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

            responseObserver.onNext(eventoMapper.fromEntity(evento));
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(new Throwable("Error al obtener evento: " + e.getMessage()));
        }
    }

    // MODIFICAR EVENTO
    @Override
    public void updateEventoSolidario(EventoSolidarioOuterClass.EventoSolidarioDTO request, StreamObserver<EventoSolidarioOuterClass.EventoSolidario> responseObserver) {
        try {
            // Buscamos por nombre como identificador (porque DTO no trae id)
            Evento evento = eventoRepository.findByNombreEvento(request.getNombre())
                    .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

            LocalDateTime fechaEvento = LocalDateTime.ofEpochSecond(
                    request.getFecha().getSeconds(),
                    request.getFecha().getNanos(),
                    ZoneOffset.UTC
            );

            if (fechaEvento.isBefore(LocalDateTime.now())) {
                // TODO: Registrar donaciones repartidas
                responseObserver.onError(new Throwable("El evento ya pasó, registrar donaciones"));
                return;
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

            EventoSolidarioOuterClass.EventoSolidario response = eventoMapper.fromEntity(updated);
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(new Throwable("Error al actualizar evento: " + e.getMessage()));
        }
    }

    // ELIMINAR EVENTO 
    @Override
    public void deleteEventoSolidario(EventoSolidarioOuterClass.EventoSolidario request, StreamObserver<EventoSolidarioOuterClass.EventoSolidario> responseObserver) {
        try {
            Evento evento = eventoRepository.findById(request.getId())
                    .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

            if (evento.getFechaHora().isBefore(LocalDateTime.now())) {
                responseObserver.onError(new Throwable("No se pueden eliminar eventos pasados"));
                return;
            }

            eventoRepository.delete(evento);

            responseObserver.onNext(eventoMapper.fromEntity(evento));
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(new Throwable("Error al eliminar evento: " + e.getMessage()));
        }
    }

    // LISTAR EVENTOS
   @Override
    public void listEventoSolidarios(UsuarioOuterClass.Empty request, StreamObserver<EventoSolidarioOuterClass.EventoSolidarioListResponse> responseObserver) {
    try {
        List<Evento> eventos = eventoRepository.findAll();

        EventoSolidarioOuterClass.EventoSolidarioListResponse.Builder response =
                EventoSolidarioOuterClass.EventoSolidarioListResponse.newBuilder();

        eventos.forEach(evento -> response.addEventos(eventoMapper.fromEntity(evento)));

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    } catch (Exception e) {
        responseObserver.onError(new Throwable("Error al listar eventos: " + e.getMessage()));
    }
}

}

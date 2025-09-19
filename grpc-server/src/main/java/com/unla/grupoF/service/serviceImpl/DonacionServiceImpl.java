package com.unla.grupoF.service.serviceImpl;

import com.unla.grupoF.entities.Donacion;
import com.unla.grupoF.mapper.DonacionMapper;
import com.unla.grupoF.repositories.DonacionRepository;
import com.unla.grupoF.service.DonacionOuterClass;
import com.unla.grupoF.service.DonacionServiceGrpc;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@GRpcService
public class DonacionServiceImpl extends DonacionServiceGrpc.DonacionServiceImplBase {

    @Autowired
    private DonacionRepository repository;

    private DonacionMapper donacionMapper = new DonacionMapper();

    // alta
    @Override
    public void createDonacion(DonacionOuterClass.DonacionDTO request, StreamObserver<DonacionOuterClass.DonacionDTO> responseObserver) {
        try {
            if (request.getCantidad() < 0) {
                responseObserver.onError(new Throwable("La cantidad no puede ser negativa"));
                return;
            }

            // Chequear que no exista otra donación con la misma descripción
            if (repository.findByDescripcion(request.getDescripcion()).isPresent()) {
                responseObserver.onError(new Throwable("Ya existe una donación con esa descripción"));
                return;
            }

            Donacion donacion = donacionMapper.toEntity(request);
            donacion.setEliminado(false);
            donacion.setFechaAlta(LocalDateTime.now());
            donacion.setUsuarioAlta("admin"); // TODO: reemplazar por usuario logueado

            Donacion saved = repository.save(donacion);
            DonacionOuterClass.DonacionDTO response = donacionMapper.toDTO(saved);

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(new Throwable("Error al crear donación: " + e.getMessage()));
        }
    }

    // modificar (buscando por descripcion)
    @Override
    public void updateDonacion(DonacionOuterClass.DonacionDTO request, StreamObserver<DonacionOuterClass.DonacionDTO> responseObserver) {
        try {
            Donacion donacion = repository.findByDescripcion(request.getDescripcion())
                    .orElseThrow(() -> new RuntimeException("Donación no encontrada"));

            if (request.getCantidad() < 0) {
                responseObserver.onError(new Throwable("La cantidad no puede ser negativa"));
                return;
            }

            donacion.setCantidad((int) request.getCantidad());
            donacion.setDescripcion(request.getDescripcion());
            donacion.setFechaModificacion(LocalDateTime.now());
            donacion.setUsuarioModificacion("admin"); // TODO: reemplazar por usuario logueado

            Donacion updated = repository.save(donacion);
            DonacionOuterClass.DonacionDTO response = donacionMapper.toDTO(updated);

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(new Throwable("Error al modificar donación: " + e.getMessage()));
        }
    }

    //borrando buscando por id, en caso de que sea por descripcion hay que modificar el proto
@Override
public void deleteDonacion(DonacionOuterClass.DonacionIdRequest request, StreamObserver<Empty> responseObserver) {

    try {
        Donacion donacion = repository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Donación no encontrada"));

        donacion.setEliminado(true);
        donacion.setFechaModificacion(LocalDateTime.now());
        donacion.setUsuarioModificacion("admin"); // TODO: reemplazar por el usuario real
        repository.save(donacion);

        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();

    } catch (RuntimeException e) {
        responseObserver.onError(new Throwable("Error al buscar la donación: " + e.getMessage()));
    } catch (Exception ex) {
        responseObserver.onError(new Throwable("Error al eliminar la donación: " + ex.getMessage()));
    }
}

    // LISTAR (solo las que no estan eliminadas)
    @Override
    public void listDonaciones(Empty request,
                               StreamObserver<DonacionOuterClass.DonacionListResponse> responseObserver) {
        try {
            DonacionOuterClass.DonacionListResponse.Builder lista = DonacionOuterClass.DonacionListResponse.newBuilder();
            repository.findAll().stream()
                    .filter(d -> !d.isEliminado())
                    .forEach(donacion -> lista.addDonaciones(
                            donacionMapper.fromEntity(donacion)
                    ));

            responseObserver.onNext(lista.build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(new Throwable("Error al listar donaciones: " + e.getMessage()));
        }
    }
}

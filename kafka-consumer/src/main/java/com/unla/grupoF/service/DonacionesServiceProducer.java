package com.unla.grupoF.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unla.grupoF.KafkaProducer;
import com.unla.grupoF.dto.BajaSolicitudDonacionDTO;
import com.unla.grupoF.dto.SolicitudDonacionDTO;
import com.unla.grupoF.dto.TransferenciaDonacionDTO;
import org.springframework.stereotype.Service;


@Service
public class DonacionesServiceProducer {
    private final KafkaProducer kafkaProducer;
    private final ObjectMapper objectMapper;

    public DonacionesServiceProducer(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
        this.objectMapper = new ObjectMapper();
    }

    public void solicitudDonaciones(SolicitudDonacionDTO dto) throws Exception {

        if (dto == null) {
            throw new Exception("El cuerpo del request esta incompleto o es nulo");
        }
        /*for (DonacionRequeridaDTO aux : dto.getDonaciones()) {
            com.unla.grupoF.entities.Categoria cat = Categoria.valueOf(aux.getCategoria());
           if (cat == null){
               throw new Exception("Categoria inexistente");
           }
        }*/
        String topic = "solicitud-donaciones";
        String mensaje = objectMapper.writeValueAsString(dto);
        kafkaProducer.enviarMensaje(topic, mensaje);

    }


    public void bajaDonacion(BajaSolicitudDonacionDTO dto) throws Exception{

        if (dto == null) {
            throw new Exception("El cuerpo del request esta incompleto o es nulo");
        }
        String topic = "baja-solicitud-donaciones";
        String mensaje = objectMapper.writeValueAsString(dto);
        kafkaProducer.enviarMensaje(topic, mensaje);

    }


    public void transferenciaDonacion(TransferenciaDonacionDTO dto) throws Exception{

        if (dto == null) {
            throw new Exception("El cuerpo del request esta incompleto o es nulo");
        }

        String topic = "transferencia-donaciones" + dto.getOrganizacionId();
        String mensaje = objectMapper.writeValueAsString(dto);
        kafkaProducer.enviarMensaje(topic, mensaje);
    }
}

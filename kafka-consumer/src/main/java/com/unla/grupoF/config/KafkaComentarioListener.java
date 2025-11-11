package com.unla.grupoF.config;

import com.unla.grupoF.dto.ComentarioEventoDTO;
import com.unla.grupoF.service.ComentarioEventoService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaComentarioListener {

    private final ComentarioEventoService comentarioService;

    public KafkaComentarioListener(ComentarioEventoService comentarioService) {
        this.comentarioService = comentarioService;
    }

    // Método que escucha el topic /comentarios-eventos
    @KafkaListener(
        topics = "comentarios-eventos", 
        groupId = "grupo-f-consumer",
        // ESTAS PROPIEDADES ANULAN LA CONFIGURACIÓN GLOBAL PARA PODER UTILIZAR JSON(String)
        properties = {
            "spring.json.value.default.type=com.unla.grupoF.dto.ComentarioEventoDTO",
            "value.deserializer=org.springframework.kafka.support.serializer.JsonDeserializer"
        }
    )
    public void consumirComentario(ComentarioEventoDTO comentarioDTO) {
        
        // El error de getIdEventoExterno debería desaparecer luego de 'mvn install'
        System.out.println("Mensaje recibido de Kafka. Procesando: ID Evento " + comentarioDTO.getIdEventoExterno()); 

        // Delegar la lógica de guardado al servicio
        comentarioService.guardarComentario(comentarioDTO);
    }
}
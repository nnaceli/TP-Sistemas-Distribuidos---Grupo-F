package com.unla.grupoF;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    // escucha a los topicos especificados
    // el groupId debe ser unico para cada consumidor
    // el header indica de que topico proviene el mensaje (y procesarlo como corresponda)
    @KafkaListener(topics = {
            "solicitud-donaciones",
            "transferencia-donaciones",
            "baja-solicitud-donaciones",
            "eventos-solidarios",
            "baja-evento-solidario",
            "adhesion-evento"
    }, groupId = "grupo-F")
    public void listen(String message, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        System.out.println("Received message: " + message);
        // TODO: Agregar la lógica para procesar el mensaje según el tópico
        //TODO: distinguir por el id de la org en el msj si es para nuestra ong
        switch (topic) {
            case "solicitud-donaciones":
                // Procesar mensaje de solicitud-donaciones
                System.out.println("Procesando solicitud de donación: " + message);
                break;
            case "transferencia-donaciones":
                // Procesar mensaje de transferencia-donaciones
                // TODO: consultar las donacione sy agregar las nuevas a la BD
                System.out.println("Procesando transferencia de donación: " + message);
                break;
            case "baja-solicitud-donaciones":
                // Procesar mensaje de baja-solicitud-donaciones
                // TODO: consultar las solicitudes y si existen, darlas de baja de la bd
                System.out.println("Procesando baja de solicitud de donación: " + message);
                break;
            case "eventos-solidarios":
                // Procesar mensaje de eventos-solidarios
                //TODO: descartar los de nuestra org y guardar los de otras orgs
                System.out.println("Procesando evento solidario: " + message);
                break;
            case "baja-evento-solidario":
                // Procesar mensaje de baja-evento-solidario
                //TODO: consultar los eventos y si existen, darlos de baja de la bd
                System.out.println("Procesando baja de evento solidario: " + message);
                break;
            case "adhesion-evento":
                // Procesar mensaje de adhesion-evento
                //TODO: agregar la logica para registrar al usuario en el evento
            default:
                System.out.println("Tópico no reconocido: " + topic);
        }
    }
}

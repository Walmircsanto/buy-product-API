package org.compra;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.compra.dto.BuyProductRequest;
import org.compra.service.BuyProductUser;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;


@RequestScoped
public class ProducerMessage {

    @Channel("my-outgoing-channel") //my-outgoing-channel deve bater com o nome la do properties, e como se fosse um apelido.
    Emitter<String> emitter;


    @Inject
    ObjectMapper objectMapper;

    public void sendMessage(BuyProductRequest buyProductRequest) {
        try {
            // converte o objeto em um valor json
            String buyProductJson = objectMapper.writeValueAsString(buyProductRequest);
            emitter.send(buyProductJson);
            Log.info("Message sent to RabbitMQ and exchange");
        } catch (JsonProcessingException e) {
            Log.error("Error serializing message", e);
        }
    }
}

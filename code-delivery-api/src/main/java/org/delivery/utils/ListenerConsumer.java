package org.delivery.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.delivery.dto.BuyProductRequest;
import org.delivery.service.BuyProductService;
import org.eclipse.microprofile.reactive.messaging.Incoming;


public class ListenerConsumer {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Inject
    BuyProductService buyProductService;

    @Incoming("my-incoming-channel")
    @Transactional
    public void consumerBuyProduct(String json) {
        try {
            BuyProductRequest request = objectMapper.readValue(json, BuyProductRequest.class);
            this.buyProductService.save(request);

            Log.info("Received message: User " + request.getIdUser() + " comprou o produto:  " + request.getIdProduct()
                    + " do id " + request.getIdCompra() + "Endereço "+ request.getAddress());
        } catch (Exception e) {
            Log.error("Failed to deserialize message", e);
        }
    }
}

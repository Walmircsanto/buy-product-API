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
            Log.info("Received json: " + json);

        } catch (Exception e) {
            Log.error("Failed to deserialize message", e);
        }
    }
}

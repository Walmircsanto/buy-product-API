package org.compra.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.compra.dto.VerifyPaymentResponse;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import java.io.DataInput;

public class PaymentConsumer {

    ObjectMapper objectMapper = new ObjectMapper();

    @Incoming("payment-response")
    public void handlePaymentResponse(String response) {


        try {
            VerifyPaymentResponse request = objectMapper.readValue(response,
                    VerifyPaymentResponse.class);

            if (request.isApproved()) {
                // agora processar a compra

                // em ambos adicionar um serviço de notificação


            } else {
                return; //metodo de notificação
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void processPurchase(Long idUser, String productId) {

    }

    private void notifyFailure(Long idUser) {

    }
}

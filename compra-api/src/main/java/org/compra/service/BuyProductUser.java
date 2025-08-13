package org.compra.service;


import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.compra.dto.VerifyPaymentRequest;
import org.compra.model.Product;
import org.compra.rabbitmq.PaymentProducerMessage;
import org.compra.model.User;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RequestScoped
public class BuyProductUser {

    @Inject
    UserService userService;

    @Inject
    PaymentProducerMessage paymentProducerMessage;

    @Inject
    ProductService productService;


    public void buyProduct(Long idUser, Long idProduct) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        User user = this.userService.getUser(idUser);
        Product product = this.productService.findById(idProduct);

        if (user != null && product != null) {
            this.verifyPayment(user.getNumberCard(), user.getHalfPayment(), user.getId(),
                    product.getPrice(), product.getId());
        }
    }

    private void verifyPayment(String numberCard, String halfPayment, Long idUser,
                               Double productPrice, Long idProduct) throws UnsupportedEncodingException,
            NoSuchAlgorithmException {

        String paymentId = this.encryptPayment(numberCard,halfPayment, idUser,productPrice,idProduct);
        VerifyPaymentRequest verifyPaymentRequest = new VerifyPaymentRequest(numberCard,
                halfPayment, paymentId, idUser, productPrice, idProduct);

        this.paymentProducerMessage.producerMessageToPayment(verifyPaymentRequest);
    }


    private String encryptPayment(String numberCard, String halfPayment, Long idUser,
                                  Double productPrice, Long idProduct)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {

        String hashPaymentId = numberCard + halfPayment + idUser + productPrice + idProduct;
        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte messageDigest[] = algorithm.digest(hashPaymentId.getBytes("UTF-8"));

        return new String(messageDigest, "UTF-8");
    }
}

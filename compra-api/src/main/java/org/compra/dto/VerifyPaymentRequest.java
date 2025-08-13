package org.compra.dto;


public class VerifyPaymentRequest {
    private String numberCard;
    private String halfPayment;
    private String paymentId;
    private Long idUser;
    private Double amount;
    private  Long idProduct;


    public VerifyPaymentRequest() {
    }

    public VerifyPaymentRequest(String numberCard, String halfPayment,
                                String paymentId, Long idUser, Double amount, Long idProduct) {
        this.numberCard = numberCard;
        this.halfPayment = halfPayment;
        this.paymentId = paymentId;
        this.idUser = idUser;
        this.amount = amount;
        this.idProduct = idProduct;
    }

    public String getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(String numberCard) {
        this.numberCard = numberCard;
    }

    public String getHalfPayment() {
        return halfPayment;
    }

    public void setHalfPayment(String halfPayment) {
        this.halfPayment = halfPayment;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "VerifyPaymentRequest{" +
                "numberCard='" + numberCard + '\'' +
                ", halfPayment='" + halfPayment + '\'' +
                ", paymentId='" + paymentId + '\'' +
                ", idUser=" + idUser +
                ", amount=" + amount +
                ", idProduct=" + idProduct +
                '}';
    }
}

package org.compra.dto;

public class VerifyPaymentResponse {
    private boolean isApproved;
    private String paymentId;
    private Long idUser;
    private Double valueBuy; //valor da compra emitido pelo microserviço de pagamento


    public VerifyPaymentResponse(boolean isApproved, String paymentId,
                                 Long idUser, Double valueBuy) {
        this.isApproved = isApproved;
        this.paymentId = paymentId;
        this.idUser = idUser;
        this.valueBuy =valueBuy;
    }


    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
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

    @Override
    public String toString() {
        return "{" +
                "isApproved=" + isApproved +
                "paymentId='" + paymentId + '\'' +
                "idUser='" + idUser + '\'' +
                '}';
    }
}

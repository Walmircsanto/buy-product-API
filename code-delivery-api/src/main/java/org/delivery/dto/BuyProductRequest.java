package org.delivery.dto;

public class BuyProductRequest {
    private Long idProduct;
    private Long idUser;
    private String idCompra;
    private String address;


    public BuyProductRequest() {
    }

    public BuyProductRequest(Long idProduct, Long idUser, String idCompra, String address) {
        this.idProduct = idProduct;
        this.idUser = idUser;
        this.idCompra = idCompra;
        this.address = address;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(String idCompra) {
        this.idCompra = idCompra;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

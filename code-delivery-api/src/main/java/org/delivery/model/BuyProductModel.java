package org.delivery.model;

import jakarta.persistence.*;

@Entity
@Table(name = "buyProducts")
public class BuyProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idProduct;
    private Long idUser;
    private String idCompra;
    private String address;


    public BuyProductModel() {
    }


    public BuyProductModel(Long id, Long idProduct, Long idUser, String idCompra) {
        this.id = id;
        this.idProduct = idProduct;
        this.idUser = idUser;
        this.idCompra = idCompra;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

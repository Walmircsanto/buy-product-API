package org.delivery.dto.mapper;

import org.delivery.dto.BuyProductRequest;
import org.delivery.model.BuyProductModel;

public class Mapper {

    public BuyProductModel mapBuyProductRequestToBuyProductModel(BuyProductRequest buyProductRequest) {
        BuyProductModel buyProductModel = new BuyProductModel();
        buyProductModel.setIdUser(buyProductRequest.getIdUser());
        buyProductModel.setIdProduct(buyProductRequest.getIdProduct());
        buyProductModel.setIdCompra(buyProductRequest.getIdCompra());
        buyProductModel.setAddress(buyProductRequest.getAddress());

        return buyProductModel;
    }
}

package org.delivery.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.delivery.dto.BuyProductRequest;
import org.delivery.dto.mapper.Mapper;
import org.delivery.model.BuyProductModel;
import org.delivery.repositories.BuyProductRepository;

import java.util.List;

@ApplicationScoped
public class BuyProductService {

    @Inject
    BuyProductRepository buyProductRepository;


    Mapper mapper = new Mapper();


    public BuyProductModel save(BuyProductRequest buyProductRequest) {

        BuyProductModel buyProductModel =(mapper.mapBuyProductRequestToBuyProductModel
                (buyProductRequest));
        this.buyProductRepository.persist(buyProductModel);

        this.buyProductRepository.flush();

        return buyProductModel;
    }


    public List<BuyProductModel> findAll() {
        return this.buyProductRepository.listAll();
    }

}

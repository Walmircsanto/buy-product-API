package org.delivery.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import org.delivery.model.BuyProductModel;

@ApplicationScoped
public class BuyProductRepository implements PanacheRepository<BuyProductModel> {
}

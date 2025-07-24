package org.compra.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.RequestScoped;
import org.compra.model.Product;

@RequestScoped
public class ProductRepository implements PanacheRepository<Product> {
}

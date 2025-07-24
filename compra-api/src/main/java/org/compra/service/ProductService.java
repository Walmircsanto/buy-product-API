package org.compra.service;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.compra.model.Product;
import org.compra.repository.ProductRepository;

@RequestScoped
public class ProductService {

    @Inject
    ProductRepository productRepository;


    public Product createdProduct(Product product){
        this.productRepository.persist(product);
        productRepository.flush();
        return product;
    }


    public Product findById(Long id){
        return this.productRepository.findById(id);
    }

    public boolean existsProduct(Long id){
        return this.productRepository.findById(id) != null;
    }
}

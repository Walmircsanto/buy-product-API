package org.compra.service;


import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.compra.ProducerMessage;
import org.compra.dto.BuyProductRequest;
import org.compra.model.Product;
import org.compra.model.User;
import org.compra.repository.ProductRepository;
import org.compra.repository.UserRepository;

import java.util.UUID;

@RequestScoped
public class BuyProductUser {

    @Inject
    private UserService userService;

    @Inject
    private ProductService productService;

    @Inject
    private ProducerMessage producerMessage;


    @Inject
    ProductRepository productRepository;

    @Inject
    UserRepository userRepository;


    public void buyProduct(Long idUser, Long idProduct) {
        if (this.productService.existsProduct(idProduct) && this.userService.existsUser(idUser)) {
            Product product = this.productService.findById(idProduct);
            User user = this.userService.getUser(idUser);

            if (product.getStock() > 0 && user.getBalance() >= product.getPrice()) {
                user.setBalance(user.getBalance() - product.getPrice());
                product.setStock(product.getStock() - 1);
                user.getProducts().add(product);
                product.getUsers().add(user);
                this.userRepository.persist(user);
                this.productRepository.persist(product);

                this.productRepository.flush();
                this.userRepository.flush();

                UUID uuid = UUID.randomUUID();


                this.producerMessage.sendMessage(new BuyProductRequest(product.getId(),
                        user.getId(),uuid.toString(), user.getAddress()));


            }else{
                throw new RuntimeException("Product in stock < 1 or user balancer: " + user.getBalance() + " < " + product.getPrice() );
            }
        }
    }
}

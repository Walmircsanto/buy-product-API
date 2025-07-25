package org.compra.service;

import org.compra.ProducerMessage;
import org.compra.dto.BuyProductRequest;
import org.compra.model.Product;
import org.compra.model.User;
import org.compra.repository.ProductRepository;
import org.compra.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class BuyProductUserTest {

    @InjectMocks
    BuyProductUser buyProductUser;

    @Mock
    UserService userService;

    @Mock
    ProductService productService;

    @Mock
    ProducerMessage producerMessage;

    @Mock
    UserRepository userRepository;

    @Mock
    ProductRepository productRepository;

    User user;
    Product product;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setId(1L);
        user.setBalance(100.0);
        user.setAddress("Rua teste");
        user.getProducts().add(new Product());

        product = new Product();
        product.setId(10L);
        product.setPrice(50.0);
        product.setStock(5);
        product.getUsers().add(user);
    }

    @Test
    void testBuyProduct_success() {
        when(userService.getUser(1L)).thenReturn(user);
        when(productService.findById(10L)).thenReturn(product);

        buyProductUser.buyProduct(1L, 10L);

        // Validações de interação
        verify(userRepository).persist(user);
        verify(productRepository).persist(product);
        verify(userRepository).flush();
        verify(productRepository).flush();
        verify(producerMessage).sendMessage(any(BuyProductRequest.class));
    }

    @Test
    void testBuyProduct_userNotFound() {
        when(userService.getUser(1L)).thenReturn(null);
        when(productService.findById(10L)).thenReturn(product);

        buyProductUser.buyProduct(1L, 10L);

        verify(userRepository, never()).persist((User) any());
        verify(productRepository, never()).persist((Product) any());
    }

    @Test
    void testBuyProduct_productNotFound() {
        when(userService.getUser(1L)).thenReturn(user);
        when(productService.findById(10L)).thenReturn(null);

        buyProductUser.buyProduct(1L, 10L);

        verify(userRepository, never()).persist((User) any());
        verify(productRepository, never()).persist((Product) any());
    }

    @Test
    void testBuyProduct_insufficientBalance() {
        user.setBalance(10.0);
        when(userService.getUser(1L)).thenReturn(user);
        when(productService.findById(10L)).thenReturn(product);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            buyProductUser.buyProduct(1L, 10L);
        });

        verify(userRepository, never()).persist((User) any());
        verify(producerMessage, never()).sendMessage(any());
    }

    @Test
    void testBuyProduct_outOfStock() {
        product.setStock(0);
        when(userService.getUser(1L)).thenReturn(user);
        when(productService.findById(10L)).thenReturn(product);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            buyProductUser.buyProduct(1L, 10L);
        });

        verify(userRepository, never()).persist((User) any());
        verify(producerMessage, never()).sendMessage(any());
    }
}

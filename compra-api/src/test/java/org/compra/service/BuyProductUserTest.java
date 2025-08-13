package org.compra.service;

import org.compra.rabbitmq.PaymentProducerMessage;
import org.compra.dto.VerifyPaymentRequest;
import org.compra.model.Product;
import org.compra.model.User;
import org.compra.repository.ProductRepository;
import org.compra.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

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
    PaymentProducerMessage paymentProducerMessage;

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
    void testBuyProduct_success() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        when(userService.getUser(1L)).thenReturn(user);
        when(productService.findById(10L)).thenReturn(product);

        buyProductUser.buyProduct(1L, 10L);

        // Validações de interação
        verify(userRepository).persist(user);
        verify(productRepository).persist(product);
        verify(userRepository).flush();
        verify(productRepository).flush();
        verify(paymentProducerMessage).producerMessageToPayment(any(VerifyPaymentRequest.class));
    }

    @Test
    void testBuyProduct_userNotFound() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        when(userService.getUser(1L)).thenReturn(null);
        when(productService.findById(10L)).thenReturn(product);

        buyProductUser.buyProduct(1L, 10L);

        verify(userRepository, never()).persist((User) any());
        verify(productRepository, never()).persist((Product) any());
    }

    @Test
    void testBuyProduct_productNotFound() throws UnsupportedEncodingException, NoSuchAlgorithmException {
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
        verify(paymentProducerMessage, never()).producerMessageToPayment(any());
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
        verify(paymentProducerMessage, never()).producerMessageToPayment(any());
    }
}

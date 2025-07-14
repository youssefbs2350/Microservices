package com.example.product.service;

import com.example.product.dto.ProductDTO;
import com.example.product.product.client.OrderClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderIntegrationService {

    @Autowired
    private OrderClient orderClient;

    @CircuitBreaker(name = "orderService", fallbackMethod = "fallbackGetOrder")
    public ProductDTO getOrder(Long id) {
        return orderClient.getOrderById(id);
    }

    public ProductDTO fallbackGetOrder(Long id, Throwable throwable) {
        System.err.println("⚠️ Fallback triggered in product-service for orderId: " + id);

        ProductDTO fallback = new ProductDTO();
        fallback.setId(String.valueOf(id));
        fallback.setName("Unavailable Order");
        fallback.setDescription("Order service is down");
        fallback.setPrice(0.0);
        return fallback;
    }
}

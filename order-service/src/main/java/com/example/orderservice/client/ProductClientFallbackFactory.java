package com.example.orderservice.client;

import com.example.orderservice.dto.OrderDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductClientFallbackFactory implements ProductClient {

    @Override
    public OrderDTO getProductById(String id) {
        OrderDTO fallback = new OrderDTO();
        fallback.setId(id);
        fallback.setName("Unavailable Product");
        fallback.setDescription("This product is currently unavailable.");
        fallback.setPrice(0.0);
        return fallback;
    }
}

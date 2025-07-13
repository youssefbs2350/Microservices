package com.example.product.product.client;

import com.example.product.dto.OrderDTO;
import org.springframework.stereotype.Component;

@Component
public class OrderClientFallback implements OrderClient {

    @Override
    public OrderDTO getOrderById(Long id) {
        OrderDTO fallback = new OrderDTO();
        fallback.setId(String.valueOf(id));
        fallback.setName("Unavailable Order");
        fallback.setDescription("Order service is currently unavailable.");
        fallback.setPrice(0.0);
        return fallback;
    }
}

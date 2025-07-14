package com.example.product.product.client;

import com.example.product.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "order-service",
        //url = "http://order-service:8081",
        fallback = OrderClientFallback.class
)
public interface OrderClient {
    @GetMapping("/api/orders/{id}")
    ProductDTO getOrderById(@PathVariable("id") Long id);
}

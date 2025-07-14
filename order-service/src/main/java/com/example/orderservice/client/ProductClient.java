package com.example.orderservice.client;

import com.example.orderservice.dto.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(
        name = "product-service",
        path = "/api/products",
        //url = "http://product-service:8082",
        fallback = ProductClientFallbackFactory.class

)
public interface ProductClient {
    @GetMapping("/{id}")
    OrderDTO getProductById(@PathVariable("id") String id);
}

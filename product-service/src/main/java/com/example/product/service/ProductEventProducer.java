package com.example.product.service;

import com.example.product.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductEventProducer {

    @Value("${kafka.topic.product.created:product-created-topic}")
    private String productCreatedTopic;

    @Value("${kafka.topic.product.updated:product-updated-topic}")
    private String productUpdatedTopic;

    @Value("${kafka.topic.product.deleted:product-deleted-topic}")
    private String productDeletedTopic;

    @Autowired
    private KafkaTemplate<String, ProductDTO> kafkaTemplate;

    public void sendProductCreatedEvent(ProductDTO product) {
        kafkaTemplate.send(productCreatedTopic, product);
        System.out.println("✅ Sent product created event for product ID: " + product.getId());
    }

    public void sendProductUpdatedEvent(ProductDTO product) {
        kafkaTemplate.send(productUpdatedTopic, product);
        System.out.println("✅ Sent product updated event for product ID: " + product.getId());
    }

    public void sendProductDeletedEvent(String productId) {
        ProductDTO product = new ProductDTO();
        product.setId(productId);
        kafkaTemplate.send(productDeletedTopic, productId, product);
        System.out.println("✅ Sent product deleted event for product ID: " + productId);
    }
}


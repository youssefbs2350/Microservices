package com.example.orderservice.dto;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ProductEventConsumer {

    @KafkaListener(topics = "product-created-topic", groupId = "order-group", containerFactory = "productKafkaListenerFactory")
    public void consume(ProductDTO product) {
        System.out.println("📩 Produit reçu depuis Kafka :");
        System.out.println("🆔 ID : " + product.getId());
        System.out.println("🛒 Nom : " + product.getName());
        System.out.println("💵 Prix : " + product.getPrice());
    }
    @KafkaListener(topics = "product.updated-topic", groupId = "order-group", containerFactory = "productKafkaListenerFactory")
    public void consumeUpdate(ProductDTO product) {
        System.out.println("📩 Produit mis à jour reçu depuis Kafka :");
        System.out.println("🆔 ID : " + product.getId());
        System.out.println("🛒 Nom : " + product.getName());
        System.out.println("💵 Prix : " + product.getPrice());
    }
    @KafkaListener(topics = "product.deleted-topic", groupId = "order-group", containerFactory = "productKafkaListenerFactory")
    public void consumeDelete(String productId) {
        System.out.println("📩 Produit supprimé reçu depuis Kafka :");
        System.out.println("🆔 ID : " + productId);
    }
    @KafkaListener(topics = "product.error", groupId = "order-group", containerFactory = "productKafkaListenerFactory")
    public void consumeError(String errorMessage) {
        System.out.println("📩 Erreur reçue depuis Kafka :");
        System.out.println("❗ Message d'erreur : " + errorMessage);
    }
}
// This class listens to Kafka topics related to product events and prints the details of the received messages.
package com.example.orderservice;

import com.example.orderservice.dto.OrderResponseDTO;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@EnableFeignClients
@SpringBootApplication
public class OrderServiceApplication {

	// Injection de l'URL Kafka depuis la config Spring
	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	// Configuration Kafka JSON Producer avec l'URL bootstrap dynamique
	@Bean
	public ProducerFactory<String, OrderResponseDTO> producerFactory() {
		Map<String, Object> config = new HashMap<>();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return new DefaultKafkaProducerFactory<>(config);
	}

	@Bean
	public KafkaTemplate<String, OrderResponseDTO> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}
}

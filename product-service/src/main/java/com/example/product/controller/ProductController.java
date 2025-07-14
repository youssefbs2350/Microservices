package com.example.product.controller;

import com.example.product.dto.ProductDTO;
import com.example.product.model.Product;
import com.example.product.service.OrderIntegrationService;
import com.example.product.service.ProductEventProducer;
import com.example.product.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private OrderIntegrationService orderIntegrationService;

    @GetMapping("/test-order/{id}")
    public Object testOrderService(@PathVariable Long id) {
        return orderIntegrationService.getOrder(id);
    }
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductEventProducer productEventProducer; // Inject the producer

    @PostMapping
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO) {
        productDTO.setId(null);  // forcer null pour création
        Product product = modelMapper.map(productDTO, Product.class);
        Product savedProduct = productService.save(product);
        ProductDTO savedProductDTO = modelMapper.map(savedProduct, ProductDTO.class);
        productEventProducer.sendProductCreatedEvent(savedProductDTO);
        return savedProductDTO;
    }


    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.findAll().stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable String id) {
        return productService.findById(id)
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        productService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable String id, @RequestBody ProductDTO productDTO) {
        Product existingProduct = productService.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        // Map incoming DTO to existing product entity to update fields
        modelMapper.map(productDTO, existingProduct);
        existingProduct.setId(id); // ensure id stays the same

        Product updatedProduct = productService.save(existingProduct);
        ProductDTO updatedProductDTO = modelMapper.map(updatedProduct, ProductDTO.class);
        productEventProducer.sendProductUpdatedEvent(updatedProductDTO); // Send Kafka event
        return updatedProductDTO;
    }
}

package com.globazaar.productservice.controller;

import java.util.List;
import com.globazaar.productservice.dto.CreateProductDto;
import com.globazaar.productservice.dto.ProductDto;
import com.globazaar.productservice.service.ProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/{userId}/products")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    // Endpoint: POST /api/users/{userId}/products
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@PathVariable Long userId, @Valid @RequestBody CreateProductDto createProductDto) {
        ProductDto savedProductDto = productService.createProduct(userId, createProductDto);
        return new ResponseEntity<>(savedProductDto, HttpStatus.CREATED);
    }

    // Endpoint: GET /api/users/{userId}/products/{productId}
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProductByIdForUser(@PathVariable Long userId, @PathVariable Long productId) {
        ProductDto productDto = productService.getProductByIdForUser(userId, productId);
        return ResponseEntity.ok(productDto);
    }

    // Endpoint: GET /api/users/{userId}/products
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProductsForUser(@PathVariable Long userId) {
        List<ProductDto> products = productService.getAllProductsForUser(userId);
        return ResponseEntity.ok(products);
    }


}
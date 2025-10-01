package com.globazaar.productservice.service;

import com.globazaar.productservice.client.UserClient;
import com.globazaar.productservice.dto.CreateProductDto;
import com.globazaar.productservice.dto.ProductDto;
import com.globazaar.productservice.entity.Product;
import com.globazaar.productservice.exception.ResourceNotFoundException;
import com.globazaar.productservice.repository.ProductRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class ProductServiceImpl {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserClient userClient; // Inject the Feign Client



    public ProductDto createProduct(Long userId, CreateProductDto createProductDto) {
        // --- VALIDATION STEP ---
        // This will make a REST API call to the User Service.
        // If the user doesn't exist, Feign will throw an exception and the method will stop here.
        userClient.getUserById(userId);

        Product product = convertToEntity(createProductDto);
        product.setUserId(userId); // Set the userId from the URL path

        Product savedProduct = productRepository.save(product);

        return convertToDto(savedProduct);
    }

    public ProductDto getProductByIdForUser(Long userId, Long productId) {
        try{
            userClient.getUserById(userId);
        }catch(FeignException.NotFound e){
            throw new ResourceNotFoundException("User not found with id " + userId);
        }


        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + productId ));

        if(!product.getUserId().equals(userId)){
            throw new ResourceNotFoundException("User not authorized to access this product");
        }

        return convertToDto(product);
    }

    public List<ProductDto> getAllProductsForUser(Long userId) {
        List<Product> products = productRepository.findByUserId(userId);
        return products.stream()
                .map(this::convertToDto) // Reuse our existing conversion method
                .collect(Collectors.toList());
    }

    // Helper method to convert DTO to Entity
    private Product convertToEntity(CreateProductDto dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setCategory(dto.getCategory());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        return product;
    }

    // Helper method to convert Entity to DTO
    private ProductDto convertToDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setCategory(product.getCategory());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        dto.setUserId(product.getUserId());
        return dto;
    }
}
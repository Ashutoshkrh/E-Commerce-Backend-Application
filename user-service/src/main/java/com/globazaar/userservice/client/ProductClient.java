package com.globazaar.userservice.client;

import com.globazaar.userservice.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;


@FeignClient(name = "product-service")
public interface ProductClient {


    @GetMapping("/api/users/{userId}/products")
    List<ProductDto> getProductsForUser(@PathVariable("userId") Long userId);
}
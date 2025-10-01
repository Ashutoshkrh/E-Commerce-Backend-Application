package com.globazaar.userservice.dto;

import lombok.Data;
import java.util.List;

@Data
public class UserResponseDto {
    private Long id;
    private String username;
    private String email;
    private List<ProductDto> products; // This will hold the products
}
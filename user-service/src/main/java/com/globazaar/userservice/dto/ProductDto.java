package com.globazaar.userservice.dto;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String category;
    private Double price;
    private String description;
}
package com.globazaar.productservice.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateProductDto {

    @NotEmpty(message = "Product name cannot be empty")
    private String name;

    @NotEmpty(message = "Product category cannot be empty")
    private String category;

    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    @NotNull(message = "Price cannot be null")
    private Double price;

    private String description;
}
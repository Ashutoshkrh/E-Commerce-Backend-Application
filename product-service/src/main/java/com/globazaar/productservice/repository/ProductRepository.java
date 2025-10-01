package com.globazaar.productservice.repository;

import com.globazaar.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List; // Import this

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    Optional<Product> findByIdAndUserId(Long productId, Long userId);
    List<Product> findByUserId(Long userId);

}
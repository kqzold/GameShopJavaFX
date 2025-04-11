package com.example.GameShopJavaFX.repository;

import com.example.GameShopJavaFX.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

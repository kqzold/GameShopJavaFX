package com.example.GameShopJavaFX.interfaces;

import com.example.GameShopJavaFX.model.Product;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public interface ProductService extends AppService<Product> {

    ObservableList<Product> getListProducts();

    Optional<Product> updateProduct(Long id, Product updatedProduct);

    boolean deleteProduct(Long id);

    ObservableList<Product> findByTitle(String title);

    ObservableList<Product> filterByGenre(String genre);

    ObservableList<Product> filterByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);

    ObservableList<Product> filterByReleaseDateRange(LocalDate startDate, LocalDate endDate);
}
package com.example.GameShopJavaFX.interfaces;

import com.example.GameShopJavaFX.model.Product;
import javafx.collections.ObservableList;

import java.util.Optional;

public interface ProductService {
    ObservableList<Product> getListProducts();
    Optional<Product> add(Product product);
}

package com.example.GameShopJavaFX.service;

import com.example.GameShopJavaFX.interfaces.ProductService;
import com.example.GameShopJavaFX.model.Product;
import com.example.GameShopJavaFX.repository.ProductRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> add(Product product) {
        return Optional.of(productRepository.save(product));
    }

    @Override
    public ObservableList<Product> getListProducts() {
        List<Product> products = productRepository.findAll();
        return FXCollections.observableArrayList(products);
    }

}

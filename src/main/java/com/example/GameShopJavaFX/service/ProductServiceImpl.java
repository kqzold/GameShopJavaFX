package com.example.GameShopJavaFX.service;

import com.example.GameShopJavaFX.interfaces.ProductService;
import com.example.GameShopJavaFX.model.Product;
import com.example.GameShopJavaFX.repository.ProductRepository;
import javafx.collections.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> add(Product product) {
        validateProduct(product);
        return Optional.of(productRepository.save(product));
    }

    @Override
    public ObservableList<Product> getListProducts() {
        List<Product> products = productRepository.findAll();
        return FXCollections.observableArrayList(products);
    }

    public Optional<Product> updateProduct(Long id, Product updatedProduct) {
        return productRepository.findById(id).map(existingProduct -> {
            existingProduct.setTitle(updatedProduct.getTitle());
            existingProduct.setGenre(updatedProduct.getGenre());
            existingProduct.setPlatform(updatedProduct.getPlatform());
            existingProduct.setQuantity(updatedProduct.getQuantity());
            existingProduct.setPublisher(updatedProduct.getPublisher());
            existingProduct.setDeveloper(updatedProduct.getDeveloper());
            existingProduct.setReleaseDate(updatedProduct.getReleaseDate());
            existingProduct.setPrice(updatedProduct.getPrice());
            validateProduct(existingProduct);
            return productRepository.save(existingProduct);
        });
    }

    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public ObservableList<Product> findByTitle(String title) {
        List<Product> products = productRepository.findAll()
                .stream()
                .filter(product -> product.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
        return FXCollections.observableArrayList(products);
    }

    public ObservableList<Product> filterByGenre(String genre) {
        List<Product> products = productRepository.findAll()
                .stream()
                .filter(product -> product.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
        return FXCollections.observableArrayList(products);
    }

    public ObservableList<Product> filterByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        List<Product> products = productRepository.findAll()
                .stream()
                .filter(product -> product.getPrice().compareTo(minPrice) >= 0 && product.getPrice().compareTo(maxPrice) <= 0)
                .collect(Collectors.toList());
        return FXCollections.observableArrayList(products);
    }

    public ObservableList<Product> filterByReleaseDateRange(LocalDate startDate, LocalDate endDate) {
        List<Product> products = productRepository.findAll()
                .stream()
                .filter(product -> !product.getReleaseDate().isBefore(startDate) && !product.getReleaseDate().isAfter(endDate))
                .collect(Collectors.toList());
        return FXCollections.observableArrayList(products);
    }

    private void validateProduct(Product product) {
        if (product.getTitle() == null || product.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (product.getGenre() == null || product.getGenre().isEmpty()) {
            throw new IllegalArgumentException("Genre cannot be null or empty");
        }
        if (product.getQuantity() != null && product.getQuantity() < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        if (product.getPrice() != null && product.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
    }
}
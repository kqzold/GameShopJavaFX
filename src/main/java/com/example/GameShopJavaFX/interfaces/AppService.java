package com.example.GameShopJavaFX.interfaces;

import com.example.GameShopJavaFX.model.Product;

import java.util.Optional;

public interface AppService<T> {
    Optional<T> add(T t);

}

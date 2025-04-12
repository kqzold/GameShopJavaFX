package com.example.GameShopJavaFX.interfaces;

import com.example.GameShopJavaFX.model.Product;
import javafx.collections.ObservableList;


public interface ProductService extends AppService<Product> {
    ObservableList<Product> getListProducts();
}

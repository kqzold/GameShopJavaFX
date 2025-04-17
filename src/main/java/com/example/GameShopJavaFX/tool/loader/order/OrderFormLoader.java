package com.example.GameShopJavaFX.tool.loader.order;

import com.example.GameShopJavaFX.tool.SpringFXMLLoader;
import org.springframework.stereotype.Component;

@Component
public class OrderFormLoader {

    private final SpringFXMLLoader springFXMLLoader;

    public OrderFormLoader(SpringFXMLLoader springFXMLLoader) {
        this.springFXMLLoader = springFXMLLoader;
    }
}

package com.example.GameShopJavaFX.tool.loader.card;

import com.example.GameShopJavaFX.GameShopJavaFxApplication;
import com.example.GameShopJavaFX.controller.order.UserOrderFormController;
import com.example.GameShopJavaFX.model.Product;
import com.example.GameShopJavaFX.tool.SpringFXMLLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CardProductFormLoader {

    private final SpringFXMLLoader springFXMLLoader;

    public CardProductFormLoader(SpringFXMLLoader springFXMLLoader) {
        this.springFXMLLoader = springFXMLLoader;
    }

    private Stage getPrimaryStage() {
        return GameShopJavaFxApplication.primaryStage;
    }

    public void loadUserOrderForm(Product product) {
        if (product == null || product.getQuantity() == 0) {
            // Проверяем наличие продукта или его доступность
            System.out.println("Невозможно загрузить форму заказа: товар отсутствует.");
            return;
        }

        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/card/CardProduct.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить форму заказа", e);
        }

        UserOrderFormController controller = fxmlLoader.getController();
        controller.setSelectedProduct(product);

        Scene scene = new Scene(root);
        Stage primaryStage = getPrimaryStage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Game Shop - Заказ товара");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
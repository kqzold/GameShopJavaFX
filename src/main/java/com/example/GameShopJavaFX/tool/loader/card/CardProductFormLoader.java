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
        FXMLLoader fxmlLoader = springFXMLLoader.load("card/card_product_form.fxml");
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

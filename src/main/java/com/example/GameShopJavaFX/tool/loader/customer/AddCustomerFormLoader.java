package com.example.GameShopJavaFX.tool.loader.customer;

import com.example.GameShopJavaFX.GameShopJavaFxApplication;
import com.example.GameShopJavaFX.tool.SpringFXMLLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AddCustomerFormLoader {
    private final SpringFXMLLoader springFXMLLoader;

    public AddCustomerFormLoader(SpringFXMLLoader springFXMLLoader) {
        this.springFXMLLoader = springFXMLLoader;
    }

    private Stage getPrimaryStage() {
        return GameShopJavaFxApplication.primaryStage;
    }

    public void loadLoginForm() {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/customer/loginForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        getPrimaryStage().setScene(scene);
        getPrimaryStage().setTitle("GameShop вход пользователя");
        getPrimaryStage().centerOnScreen();
        getPrimaryStage().show();
    }

    public void loadMainForm() {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/main/mainForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        getPrimaryStage().setScene(scene);
        getPrimaryStage().setTitle("GameShop главная форма");
        getPrimaryStage().centerOnScreen();
        getPrimaryStage().show();
    }
}

package com.example.GameShopJavaFX.tool.loader.auth;

import com.example.GameShopJavaFX.GameShopJavaFxApplication;
import com.example.GameShopJavaFX.tool.SpringFXMLLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginFormLoader {

    private final SpringFXMLLoader springFXMLLoader;

    public LoginFormLoader(SpringFXMLLoader springFXMLLoader) {
        this.springFXMLLoader = springFXMLLoader;
    }

    private Stage getPrimaryStage() {
        return GameShopJavaFxApplication.primaryStage;
    }

    public void loadMainForm() {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/main/mainForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить форму входа", e);
        }

        Scene scene = new Scene(root);
        Stage primaryStage = getPrimaryStage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Game Shop - Вход");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void loadCatalogForm() {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/catalog/CatalogForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить форму каталога", e);
        }

        Scene scene = new Scene(root);
        Stage primaryStage = getPrimaryStage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Game Shop - Каталог");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void loadRegistrationForm() {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/customer/registrationForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить форму регистрации", e);
        }

        Scene scene = new Scene(root);
        getPrimaryStage().setScene(scene);
        getPrimaryStage().setTitle("Game Shop - Регистрация");
        getPrimaryStage().centerOnScreen();
        getPrimaryStage().show();
    }
}

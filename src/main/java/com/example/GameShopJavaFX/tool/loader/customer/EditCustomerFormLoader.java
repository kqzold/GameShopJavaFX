package com.example.GameShopJavaFX.tool.loader.customer;

import com.example.GameShopJavaFX.GameShopJavaFxApplication;
import com.example.GameShopJavaFX.controller.customer.EditCustomerFormController;
import com.example.GameShopJavaFX.tool.SpringFXMLLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class EditCustomerFormLoader {

    private final SpringFXMLLoader springFXMLLoader;

    public EditCustomerFormLoader(SpringFXMLLoader springFXMLLoader) {
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
        } catch (Exception e) {
            throw new RuntimeException("Не удалось загрузить /main/mainForm.fxml", e);
        }
        Scene scene = new Scene(root);
        Stage primaryStage = getPrimaryStage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("GameShop главная форма");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void loadEditForm() {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/customer/editCustomerForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(root);
        getPrimaryStage().setScene(scene);
        getPrimaryStage().setTitle("Редактирование пользователя");
        getPrimaryStage().centerOnScreen();
        getPrimaryStage().show();
    }
}

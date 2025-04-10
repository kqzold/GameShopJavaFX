package com.example.GameShopJavaFX.tool.loader.customer;

import com.example.GameShopJavaFX.GameShopJavaFxApplication;
import com.example.GameShopJavaFX.interfaces.AppCustomerService;
import com.example.GameShopJavaFX.model.Customer;
import com.example.GameShopJavaFX.tool.SpringFXMLLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ProfileFormLoader {

    private final SpringFXMLLoader springFXMLLoader;
    private final AppCustomerService appCustomerService;

    public ProfileFormLoader(SpringFXMLLoader springFXMLLoader, AppCustomerService appCustomerService) {
        this.springFXMLLoader = springFXMLLoader;
        this.appCustomerService = appCustomerService;
    }

    private Stage getPrimaryStage() {
        return GameShopJavaFxApplication.primaryStage;
    }

    public void loadChangePasswordForm(Customer customer) {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/customer/ChangePasswordForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить /view/customer/ChangePasswordForm.fxml", e);
        }
        // Получаем контроллер формы изменения пароля
        ChangePasswordFormController controller = fxmlLoader.getController();
        // Передаём текущего пользователя, если он установлен
        Customer currentCustomer = appCustomerService.getCurrentCustomer();
        if (currentCustomer != null) {
            controller.setCustomer(currentCustomer);
        }
        Scene scene = new Scene(root);
        Stage primaryStage = getPrimaryStage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Изменение пароля");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void loadMainForm() {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/main/mainForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить /main/mainForm.fxml", e);
        }
        Scene scene = new Scene(root);
        Stage primaryStage = getPrimaryStage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Clothing_StoreJavaFX магазин верхней одежды");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}

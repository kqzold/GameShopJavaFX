package com.example.GameShopJavaFX.tool.loader.main;

import com.example.GameShopJavaFX.GameShopJavaFxApplication;
import com.example.GameShopJavaFX.controller.customer.ProfileFormController;
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
public class MenuFormLoader {

    private final SpringFXMLLoader springFXMLLoader;
    private final AppCustomerService appCustomerService;

    public MenuFormLoader(SpringFXMLLoader springFXMLLoader, AppCustomerService appCustomerService) {
        this.springFXMLLoader = springFXMLLoader;
        this.appCustomerService = appCustomerService;
    }

    private Stage getPrimaryStage() {
        return GameShopJavaFxApplication.primaryStage;
    }

    public Parent loadMenuForm() {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/main/menuForm.fxml");
        try {
            return fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить форму меню", e);
        }
    }

    public void showAddProductForm() {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/product/AddProductForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить форму добавления продукта", e);
        }

        Scene scene = new Scene(root);
        Stage primaryStage = getPrimaryStage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Добавление продукта");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void showAddCustomerForm() {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/customer/AddCustomerForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить форму добавления пользователя", e);
        }

        Scene scene = new Scene(root);
        Stage primaryStage = getPrimaryStage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Добавление пользователя");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void loadLoginForm() {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/customer/loginForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить форму входа", e);
        }

        Scene scene = new Scene(root);
        Stage primaryStage = getPrimaryStage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("GameShop вход пользователя");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void showCustomersForm() {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/customer/CustomerForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить форму пользователя", e);
        }

        Scene scene = new Scene(root);
        Stage primaryStage = getPrimaryStage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("GameShop форма пользователя");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void loadProfileForm() {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/customer/profileForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить форму профиля", e);
        }

        ProfileFormController controller = fxmlLoader.getController();
        controller.setCustomer(appCustomerService.getCurrentCustomer());

        Customer currentCustomer = appCustomerService.getCurrentCustomer();
        if (currentCustomer != null) {
            controller.setCustomer(currentCustomer);
        }

        Scene scene = new Scene(root);
        Stage primaryStage = getPrimaryStage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("GameShop профиль пользователя");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void loadOrderForm() {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/order/OrderForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить форму заказа", e);
        }

        Scene scene = new Scene(root);
        Stage primaryStage = getPrimaryStage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("GameShop форма заказа");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void loadCatalogForm() {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/catalog/CatalogForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить CatalogForm", e);
        }
        Scene scene = new Scene(root);
        Stage primaryStage = getPrimaryStage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Каталог одежды");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void loadRevenueForm() {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/order/RevenueForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить RevenueForm.fxml", e);
        }
        Scene scene = new Scene(root);
        Stage primaryStage = getPrimaryStage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Доход магазина");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void loadRatingChartForm() {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/rating/ratingForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить RatingForm.fxml", e);
        }
        Scene scene = new Scene(root);
        Stage primaryStage = getPrimaryStage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Рейтинг продаж");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}

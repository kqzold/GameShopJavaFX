package com.example.GameShopJavaFX.tool;

import com.example.GameShopJavaFX.GameShopJavaFxApplication;
import com.example.GameShopJavaFX.model.Customer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FormLoader {

    private final SpringFXMLLoader springFXMLLoader;

    @Autowired
    public FormLoader(SpringFXMLLoader springFXMLLoader) {
        this.springFXMLLoader = springFXMLLoader;
    }


    // Загрузка формы входа
    public void loadLoginForm() {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/customer/loginForm.fxml");
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        getPrimaryStage().setScene(scene);
        getPrimaryStage().setTitle("Game Shop вход пользователя");
        getPrimaryStage().centerOnScreen();
        getPrimaryStage().show();
    }

    private Stage getPrimaryStage() {
        return GameShopJavaFxApplication.primaryStage;
    }

    // Загрузка главной страницы
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

    // Загрузка формы редактирования клиента
    public void loadEditForm() {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/customer/showCustomerList.fxml");
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        getPrimaryStage().setScene(scene);
        getPrimaryStage().setTitle("Редактирование пользователя");
    }

    // Загрузка формы изменения пароля
    public void loadChangePasswordForm(Customer customer) {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/customer/ChangePasswordForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить /view/customer/ChangePasswordForm.fxml", e);
        }
        // Получаем контроллер формы изменения пароля

        //ChangePasswordFormController controller = fxmlLoader.getController();
        // Передаём текущего пользователя, если он установлен
        //if (AppCustomerServiceImpl.currentCustomer != null) {
            //controller.setCustomer(AppCustomerServiceImpl.currentCustomer);
        }
        //Scene scene = new Scene(root);
        //Stage primaryStage = getPrimaryStage();
       // primaryStage.setScene(scene);
       // primaryStage.setTitle("Изменение пароля");
        //primaryStage.centerOnScreen();
       // primaryStage.show();
}

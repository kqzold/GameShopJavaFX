package com.example.GameShopJavaFX.controller.profile;

import com.example.GameShopJavaFX.interfaces.AppCustomerService;
import com.example.GameShopJavaFX.model.Customer;
import com.example.GameShopJavaFX.tool.loader.catalog.CatalogFormLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ChangePasswordFormController implements Initializable {

    private final AppCustomerService appCustomerService;
    private final CatalogFormLoader catalogFormLoader;
    private Customer currentCustomer;

    @FXML
    private PasswordField pfCurrentPassword;

    @FXML
    private PasswordField pfNewPassword;

    @FXML
    private PasswordField pfConfirmPassword;

    public ChangePasswordFormController(AppCustomerService appCustomerService, CatalogFormLoader catalogFormLoader) {
        this.appCustomerService = appCustomerService;
        this.catalogFormLoader = catalogFormLoader;
    }

    public void setCustomer(Customer customer) {
        this.currentCustomer = customer;
    }


    @FXML
    private void savePassword() {
        String currentPass = pfCurrentPassword.getText().trim();
        String newPass = pfNewPassword.getText().trim();
        String confirmPass = pfConfirmPassword.getText().trim();

        if (currentCustomer == null) {
            System.out.println("Пользователь не найден");
            return;
        }

        // Проверяем, что текущий пароль введён правильно
        if (!currentCustomer.getPassword().equals(currentPass)) {
            System.out.println("Неверный текущий пароль");
            return;
        }

        // Проверяем, что новый пароль введён и совпадает с подтверждением
        if (newPass.isEmpty() || !newPass.equals(confirmPass)) {
            System.out.println("Новый пароль не введён или подтверждение не совпадает");
            return;
        }

        // Обновляем пароль
        currentCustomer.setPassword(newPass);
        appCustomerService.add(currentCustomer);
        catalogFormLoader.loadMainFormCatalog();

    }


    @FXML
    private void cancel() {
        catalogFormLoader.loadMainFormCatalog();

    }

    private void closeWindow() {
        Stage stage = (Stage) pfCurrentPassword.getScene().getWindow();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

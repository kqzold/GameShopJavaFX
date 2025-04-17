package com.example.GameShopJavaFX.controller.auth;

import com.example.GameShopJavaFX.service.AppCustomerServiceImpl;
import com.example.GameShopJavaFX.tool.loader.auth.LoginFormLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

@Component
public class LoginFormController {

    @FXML
    private TextField tfName;

    @FXML
    private PasswordField tfPassword;

    @FXML
    private Label infoLabel;

    private final AppCustomerServiceImpl appCustomerService;
    private final LoginFormLoader formLoader;

    public LoginFormController(AppCustomerServiceImpl appCustomerService, LoginFormLoader formLoader) {
        this.appCustomerService = appCustomerService;
        this.formLoader = formLoader;
    }

    @FXML
    private void login() {
        String name = tfName.getText().trim();
        String password = tfPassword.getText().trim();

        boolean success = appCustomerService.authentication(name, password);
        if (!success) {
            infoLabel.setText("Неверное имя пользователя или пароль");
            return;
        }

        if (appCustomerService.getCurrentCustomer().getRoles().contains("ADMIN") || appCustomerService.getCurrentCustomer().getRoles().contains("MANAGER")) {
            formLoader.loadMainForm();
        } else {
            formLoader.loadCatalogForm();
        }
    }

    @FXML
    private void showRegistrationForm() {
        formLoader.loadRegistrationForm();
    }
}

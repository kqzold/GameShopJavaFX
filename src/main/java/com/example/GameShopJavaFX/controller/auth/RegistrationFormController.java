package com.example.GameShopJavaFX.controller.auth;

import com.example.GameShopJavaFX.model.Customer;
import com.example.GameShopJavaFX.service.AppCustomerServiceImpl;
import com.example.GameShopJavaFX.tool.loader.auth.RegistrationFormLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

@Component
public class RegistrationFormController {

    private final AppCustomerServiceImpl appCustomerService;
    private final RegistrationFormLoader formLoader;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfPassword;

    @FXML
    private Label infoLabel;

    public RegistrationFormController(AppCustomerServiceImpl appCustomerService, RegistrationFormLoader formLoader) {
        this.appCustomerService = appCustomerService;
        this.formLoader = formLoader;
    }

    @FXML
    private void registration() {
        try {
            Customer customer = new Customer();
            customer.setName(tfName.getText().trim());
            customer.setEmail(tfEmail.getText().trim());
            customer.setPassword(tfPassword.getText().trim());
            customer.getRoles().add(AppCustomerServiceImpl.ROLES.USER.toString());

            appCustomerService.add(customer);

            formLoader.loadLoginForm();
        } catch (IllegalArgumentException e) {
            infoLabel.setText(e.getMessage());
        } catch (Exception e) {
            infoLabel.setText("Ошибка регистрации: " + e.getMessage());
        }
    }
}

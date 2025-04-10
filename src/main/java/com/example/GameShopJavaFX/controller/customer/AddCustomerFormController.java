package com.example.GameShopJavaFX.controller.customer;

import com.example.GameShopJavaFX.interfaces.AppCustomerService;
import com.example.GameShopJavaFX.model.Customer;
import com.example.GameShopJavaFX.tool.FormLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

@Component
public class AddCustomerFormController implements Initializable {

    private final AppCustomerService appCustomerService;
    private final FormLoader formLoader;

    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private TextField addressField;
    @FXML private TextField balanceField;
    @FXML private Label infoLabel;
    @FXML private Button saveButton;
    @FXML private Button cancelButton;

    @Autowired
    public AddCustomerFormController(AppCustomerService appCustomerService, FormLoader formLoader) {
        this.formLoader = formLoader;
        this.appCustomerService = appCustomerService;
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        saveButton.setOnAction(event -> addCustomer());
        cancelButton.setOnAction(event -> goToMainForm());
    }

    private void addCustomer() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();
        String address = addressField.getText().trim();
        String balanceText = balanceField.getText().trim();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || balanceText.isEmpty()) {
            infoLabel.setText("Пожалуйста, заполните обязательные поля (Имя, Почта, Пароль, Баланс)");
            return;
        }


        double balance;
        try {
            balance = Double.parseDouble(balanceText);
            if (balance < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            infoLabel.setText("Введите корректное значение баланса (например: 100.0)");
            return;
        }

        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPassword(password); // На практике: шифруем!
        customer.setAddress(address);
        customer.setBalance(balance);

        try {
            Optional<Customer> result = appCustomerService.add(customer);
            if (result.isPresent()) {
                infoLabel.setText("Пользователь успешно добавлен!");
                formLoader.loadLoginForm();
            } else {
                infoLabel.setText("Ошибка при добавлении пользователя");
            }
        } catch (Exception ex) {
            infoLabel.setText("Ошибка: " + ex.getMessage());
        }
    }

    private void goToMainForm() {
        formLoader.loadMainForm();
    }
}

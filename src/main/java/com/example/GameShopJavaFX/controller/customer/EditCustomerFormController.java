package com.example.GameShopJavaFX.controller.customer;

import com.example.GameShopJavaFX.interfaces.AppCustomerService;
import com.example.GameShopJavaFX.model.Customer;
import com.example.GameShopJavaFX.tool.loader.customer.EditCustomerFormLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class EditCustomerFormController implements Initializable {

    private final EditCustomerFormLoader formLoader;
    private final AppCustomerService appCustomerService;
    private Customer editCustomer;

    @FXML
    private TextField tfnameField;

    @FXML
    private TextField tfemailField;

    @FXML
    private TextField tfpasswordField;

    @FXML
    private TextField tfaddressField;

    @FXML
    private TextField tfbalanceField;

    @FXML
    private Label infoLabel;

    public EditCustomerFormController(AppCustomerService appCustomerService, EditCustomerFormLoader formLoader) {
        this.appCustomerService = appCustomerService;
        this.formLoader = formLoader;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        infoLabel.setText("");
    }

    public void setEditCustomer(Customer editCustomer) {
        this.editCustomer = editCustomer;
        tfnameField.setText(editCustomer.getName());
        tfemailField.setText(editCustomer.getEmail());
        tfpasswordField.setText(editCustomer.getPassword());
        tfaddressField.setText(editCustomer.getAddress());
        tfbalanceField.setText(String.valueOf(editCustomer.getBalance()));
    }

    @FXML
    private void goEdit() {
        String name = tfnameField.getText().trim();
        String email = tfemailField.getText().trim();
        String password = tfpasswordField.getText().trim();
        String address = tfaddressField.getText().trim();
        String balanceText = tfbalanceField.getText().trim();

        // Валидация полей
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || balanceText.isEmpty()) {
            infoLabel.setText("Пожалуйста, заполните все обязательные поля.");
            return;
        }

        double balance;
        try {
            balance = Double.parseDouble(balanceText);
        } catch (NumberFormatException e) {
            infoLabel.setText("Введите корректное значение баланса.");
            return;
        }

        // Обновление данных
        editCustomer.setName(name);
        editCustomer.setEmail(email);
        editCustomer.setPassword(password);
        editCustomer.setAddress(address);
        editCustomer.setBalance(balance);

        try {
            appCustomerService.add(editCustomer);
            showAlert("Успешно", "Клиент успешно обновлен!");
            formLoader.loadMainForm();
        } catch (Exception e) {
            infoLabel.setText("Ошибка при сохранении: " + e.getMessage());
        }
    }

    @FXML
    private void goToCustomerListForm() {
        formLoader.loadEditForm();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

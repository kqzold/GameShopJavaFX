package com.example.GameShopJavaFX.controller.customer;

import com.example.GameShopJavaFX.interfaces.AppCustomerService;
import com.example.GameShopJavaFX.model.Customer;
import com.example.GameShopJavaFX.tool.FormLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileFormController implements Initializable {

    private final AppCustomerService appCustomerService;
    private final FormLoader formLoader;
    private Customer currentCustomer;

    @FXML
    private Label lblName;

    @FXML
    private Label lblBalance;

    public ProfileFormController(AppCustomerService appCustomerService, FormLoader formLoader) {
        this.appCustomerService = appCustomerService;
        this.formLoader = formLoader;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Можно сделать какие-то дополнительные действия при инициализации, если нужно.
    }

    // Метод для обновления данных на UI
    private void updateCustomerInfo(Customer customer) {
        if (customer != null) {
            lblName.setText(customer.getName());
            lblBalance.setText(String.valueOf(customer.getBalance()));
        } else {
            // Обработать ситуацию, если customer == null, например, вывести сообщение об ошибке
            lblName.setText("Неизвестный пользователь");
            lblBalance.setText("Нет данных");
        }
    }

    // Устанавливаем клиента и обновляем UI
    public void setCustomer(Customer customer) {
        this.currentCustomer = customer;
        updateCustomerInfo(customer);
    }

    // Открытие формы изменения пароля
    @FXML
    private void openChangePasswordWindow() {
        if (currentCustomer != null) {
            formLoader.loadChangePasswordForm(currentCustomer);
        } else {
            // Можно добавить обработку ошибок, если текущий пользователь не был установлен
            // Например, вывести ошибку или перенаправить на страницу логина
        }
    }

    // Переход на главную страницу
    @FXML
    private void cancel() {
        formLoader.loadMainForm();
    }
}

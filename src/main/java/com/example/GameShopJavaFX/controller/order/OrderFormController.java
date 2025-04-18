package com.example.GameShopJavaFX.controller.order;

import com.example.GameShopJavaFX.interfaces.AppCustomerService;
import com.example.GameShopJavaFX.interfaces.OrderService;
import com.example.GameShopJavaFX.interfaces.ProductService;
import com.example.GameShopJavaFX.model.Customer;
import com.example.GameShopJavaFX.model.Order;
import com.example.GameShopJavaFX.model.Product;
import com.example.GameShopJavaFX.tool.loader.main.MainFormLoader;
import com.example.GameShopJavaFX.tool.loader.order.OrderFormLoader;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class OrderFormController implements Initializable {

    private final OrderService orderService;
    private final ProductService productService;
    private final AppCustomerService appCustomerService;
    private final OrderFormLoader orderFormLoader;
    private final MainFormLoader mainFormLoader;

    @FXML
    private ComboBox<Customer> customerComboBox;

    @FXML
    private ComboBox<Product> productComboBox;

    @FXML
    private TextField quantityTextField;

    @FXML
    private Label totalPriceLabel;

    @FXML
    private Label errorLabel;

    public OrderFormController(OrderService orderService,
                              ProductService productService,
                              AppCustomerService appCustomerService,
                              OrderFormLoader orderFormLoader,
                              MainFormLoader mainFormLoader) {
        this.orderService = orderService;
        this.productService = productService;
        this.appCustomerService = appCustomerService;
        this.orderFormLoader = orderFormLoader;
        this.mainFormLoader = mainFormLoader;
    }

    public void initialize(URL location, ResourceBundle resources) {
        customerComboBox.setItems(appCustomerService.getListCustomers());
        ObservableList<Product> products = productService.getListProducts();
        productComboBox.setItems(products);
        productComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> recalcTotal());
        quantityTextField.textProperty().addListener((observable, oldValue, newValue) -> recalcTotal());
    }

    private void recalcTotal() {
        Product selected = productComboBox.getSelectionModel().getSelectedItem();
        String quantityText = quantityTextField.getText().trim();

        if (selected == null) {
            totalPriceLabel.setText("0.00");
            errorLabel.setText("Выберите продукт");
            return;
        }

        if (!quantityText.matches("\\d+")) {
            totalPriceLabel.setText("0.00");
            errorLabel.setText("Введите корректное количество");
            return;
        }

        int quantity = Integer.parseInt(quantityText);
        double totalPrice = selected.getPrice().multiply(BigDecimal.valueOf(quantity)).doubleValue();
        totalPriceLabel.setText(String.format("%.2f", totalPrice));
    }

    @FXML
    private void placeOrder() {
        Customer selectedCustomer = customerComboBox.getSelectionModel().getSelectedItem();
        if(selectedCustomer == null) {
            errorLabel.setText("Выберите клиента");
            return;
        }
        Product selectedProduct = productComboBox.getSelectionModel().getSelectedItem();
        if(selectedProduct == null) {
            errorLabel.setText("Выберите продукт");
            return;
        }
        int orderQuantity;
        try {
            orderQuantity = Integer.parseInt(quantityTextField.getText().trim());
            if(orderQuantity <= 0) {
                errorLabel.setText("Количество должно быть больше 0");
                return;
            }
        } catch (NumberFormatException e) {
            errorLabel.setText("Введите корректное количество");
            return;
        }

        double totalCost = selectedProduct.getPrice().multiply(BigDecimal.valueOf(orderQuantity)).doubleValue();
        if(selectedCustomer.getBalance() < totalCost) {
            errorLabel.setText("Недостаточно средств на счете клиента");
            return;
        }

        selectedCustomer.setBalance(selectedCustomer.getBalance() - totalCost);
        selectedProduct.setQuantity(selectedProduct.getQuantity() - orderQuantity);

        Order order = new Order(selectedCustomer, selectedProduct, orderQuantity, totalCost);
        orderService.add(order);

        productService.add(selectedProduct);
        appCustomerService.add(selectedCustomer);

        errorLabel.setText("Заказ успешно оформлен" + totalCost);
        mainFormLoader.loadMainForm();

    }

    @FXML
    private void goToMainForm() {
        mainFormLoader.loadMainForm();
    }
}

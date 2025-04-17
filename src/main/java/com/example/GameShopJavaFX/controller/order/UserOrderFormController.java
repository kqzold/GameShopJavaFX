package com.example.GameShopJavaFX.controller.order;

import com.example.GameShopJavaFX.interfaces.AppCustomerService;
import com.example.GameShopJavaFX.interfaces.OrderService;
import com.example.GameShopJavaFX.interfaces.ProductService;
import com.example.GameShopJavaFX.model.Customer;
import com.example.GameShopJavaFX.model.Order;
import com.example.GameShopJavaFX.model.Product;
import com.example.GameShopJavaFX.tool.loader.catalog.CatalogFormLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class UserOrderFormController implements Initializable {

    @FXML
    private Label lblProductInfo;

    @FXML
    private Label lblTotal;

    @FXML
    private Label lblError;

    @FXML
    private TextField tfQuantity;

    private Product selectedProduct;

    private final CatalogFormLoader catalogFormLoader;
    private final ProductService productService;
    private final OrderService orderService;
    private final AppCustomerService appCustomerService;

    public UserOrderFormController(CatalogFormLoader catalogFormLoader,
                                 ProductService productService,
                                   OrderService orderService,
                                 AppCustomerService appCustomerService) {
        this.catalogFormLoader = catalogFormLoader;
        this.productService = productService;
        this.orderService = orderService;
        this.appCustomerService = appCustomerService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tfQuantity.textProperty().addListener((observable, oldValue, newValue) -> recalcTotal());
    }

    public void setSelectedProduct(Product product) {
        this.selectedProduct = product;
        if (product != null) {
            lblProductInfo.setText(
                    product.getName() + ", " +
                            "Название: " + product.getName() + ", " +
                            "Категория: " + product.getCategory() + ", " +
                            "Цена: " + product.getPrice() + ", " +
                            "Кол-во: " + product.getQuantity()
            );
        }
    }

    private void recalcTotal() {
        int quantity = 0;
        try {
            quantity = Integer.parseInt(tfQuantity.getText().trim());
        } catch (NumberFormatException e) {
            // Если ввод некорректный, оставляем количество равным 0
        }
        if (selectedProduct != null) {
            double total = selectedProduct.getPrice().multiply(BigDecimal.valueOf(quantity)).doubleValue();
            lblTotal.setText(String.format("%.2f", total));
        } else {
            lblTotal.setText("0.00");
        }
    }


    @FXML
    private void placeOrder() {
        if (selectedProduct == null) {
            lblError.setText("Please select a product");
            return;
        }
        int quantity;
        try {
            quantity = Integer.parseInt(tfQuantity.getText().trim());
            if (quantity <= 0) {
                lblError.setText("Quantity must be greater than 0");
                return;
            }
        } catch (NumberFormatException e) {
            lblError.setText("Please enter a valid quantity");
            return;
        }

        double totalCost = selectedProduct.getPrice().multiply(BigDecimal.valueOf(quantity)).doubleValue();
        Customer currentCustomer = appCustomerService.getCurrentCustomer();
        if (currentCustomer == null) {
            lblError.setText("Please log in to place an order");
            return;
        }
        if (currentCustomer.getBalance() < totalCost) {
            lblError.setText("Insufficient funds in customer's account");
            return;
        }
        if (selectedProduct.getQuantity() < quantity) {
            lblError.setText("Insufficient product quantity");
            return;
        }

        currentCustomer.setBalance(currentCustomer.getBalance() - totalCost);
        selectedProduct.setQuantity(selectedProduct.getQuantity() - quantity);

        Order order = new Order(currentCustomer, selectedProduct, quantity, totalCost);
        orderService.add(order);

        productService.add(selectedProduct);
        appCustomerService.add(currentCustomer);

        lblError.setText("Order placed successfully. Total cost: " + totalCost);
        catalogFormLoader.loadMainFormCatalog();
    }

    @FXML
    private void cancel() {
        catalogFormLoader.loadMainFormCatalog();
    }
}

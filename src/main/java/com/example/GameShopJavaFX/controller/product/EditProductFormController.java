package com.example.GameShopJavaFX.controller.product;

import com.example.GameShopJavaFX.interfaces.ProductService;
import com.example.GameShopJavaFX.model.Product;
import com.example.GameShopJavaFX.tool.loader.product.EditProductFormLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class EditProductFormController implements Initializable {

    private final ProductService productService;
    private final EditProductFormLoader formLoader;
    private Product editProduct;

    @FXML
    private TextField tfNameField;

    @FXML
    private TextField tfPriceField;

    @FXML
    private TextField tfCategoryField;

    @FXML
    private TextField tfQuantityField;

    public EditProductFormController(ProductService productService, EditProductFormLoader formLoader) {
        this.productService = productService;
        this.formLoader = formLoader;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Инициализация, если необходимо
    }

    public void setEditProduct(Product product) {
        this.editProduct = product;
        if (product != null) {
            tfNameField.setText(product.getName());
            tfCategoryField.setText(product.getCategory());
            tfQuantityField.setText(String.valueOf(product.getQuantity()));
            tfPriceField.setText(String.valueOf(product.getPrice()));
        }
    }

    @FXML
    public void goEdit() {
        if (editProduct == null) return;

        try {
            String name = tfNameField.getText().trim();
            String category = tfCategoryField.getText().trim();
            int quantity = Integer.parseInt(tfQuantityField.getText().trim());
            BigDecimal price = new BigDecimal(tfPriceField.getText().trim());

            editProduct.setName(name);
            editProduct.setCategory(category);
            editProduct.setQuantity(quantity);
            editProduct.setPrice(price);

            productService.add(editProduct);

        } catch (NumberFormatException e) {
            showAlert("Неверный формат числа", "Пожалуйста, введите корректные значения цены и количества.");
        }
    }

    @FXML
    private void goToMainForm() {
        formLoader.loadMainForm();

    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

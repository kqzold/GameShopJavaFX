package com.example.GameShopJavaFX.controller.product;

import com.example.GameShopJavaFX.interfaces.ProductService;
import com.example.GameShopJavaFX.model.Product;
import com.example.GameShopJavaFX.tool.loader.product.AddProductFormLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AddProductFormController implements Initializable {

    private final AddProductFormLoader formLoader;
    private final ProductService productService;

    @FXML
    private TextField tfNameField;

    @FXML
    private TextField tfPriceField;

    @FXML
    private TextField tfCategoryField;

    @FXML
    private TextField tfQuantityField;

    public AddProductFormController(AddProductFormLoader formLoader, ProductService productService) {
        this.formLoader = formLoader;
        this.productService = productService;
    }

    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        // Инициализация, если необходимо
    }

    @FXML
    public void addProduct() {
        String name = tfNameField.getText().trim();
        String category = tfCategoryField.getText().trim();
        String quantityStr = tfQuantityField.getText().trim();
        String priceStr = tfPriceField.getText().trim();

        if (name.isEmpty() || category.isEmpty() || quantityStr.isEmpty() || priceStr.isEmpty()) {
            // Можно показать сообщение об ошибке через Alert
            System.out.println("Пожалуйста, заполните все поля.");
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityStr);
            BigDecimal price = new BigDecimal(priceStr);

            Product product = new Product();
            product.setName(name);
            product.setCategory(category);
            product.setQuantity(quantity);
            product.setPrice(price);

            productService.add(product);
            formLoader.loadMainForm();
        } catch (NumberFormatException e) {
            System.out.println("Некорректный формат количества или цены.");
            // Можно заменить на Alert или логирование
        }
    }

    @FXML
    public void goToMainForm() {
        formLoader.loadMainForm();
    }
}

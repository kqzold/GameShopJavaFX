package com.example.GameShopJavaFX.controller.product;

import com.example.GameShopJavaFX.interfaces.ProductService;
import com.example.GameShopJavaFX.model.Product;
import com.example.GameShopJavaFX.tool.loader.product.AddProductFormLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

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

    @FXML
    private TextField tfPlatformField;

    @FXML
    private TextField tfPublisherField;

    @FXML
    private TextField tfDeveloperField;

    @FXML
    private DatePicker dpReleaseDate;

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
        String title = tfNameField.getText().trim();
        String genre = tfCategoryField.getText().trim();
        String platform = tfPlatformField.getText().trim();
        String publisher = tfPublisherField.getText().trim();
        String developer = tfDeveloperField.getText().trim();
        String quantityStr = tfQuantityField.getText().trim();
        String priceStr = tfPriceField.getText().trim();
        LocalDate releaseDate = dpReleaseDate.getValue();

        if (title.isEmpty() || genre.isEmpty() || quantityStr.isEmpty() || priceStr.isEmpty()) {
            showAlert("Ошибка", "Пожалуйста, заполните все обязательные поля.");
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityStr);
            BigDecimal price = new BigDecimal(priceStr);

            if (price.compareTo(BigDecimal.ZERO) <= 0) {
                showAlert("Ошибка", "Цена должна быть положительной.");
                return;
            }

            Product product = new Product();
            product.setTitle(title);
            product.setGenre(genre);
            product.setPlatform(platform);
            product.setPublisher(publisher);
            product.setDeveloper(developer);
            product.setQuantity(quantity);
            product.setPrice(price);
            product.setReleaseDate(releaseDate);

            productService.add(product);
            formLoader.loadMainForm();
        } catch (NumberFormatException e) {
            showAlert("Ошибка", "Некорректный формат количества или цены.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    public void goToMainForm() {
        formLoader.loadMainForm();
    }
}
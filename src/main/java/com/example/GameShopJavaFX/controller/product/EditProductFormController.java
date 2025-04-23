package com.example.GameShopJavaFX.controller.product;

import com.example.GameShopJavaFX.interfaces.ProductService;
import com.example.GameShopJavaFX.model.Product;
import com.example.GameShopJavaFX.tool.loader.product.EditProductFormLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class EditProductFormController implements Initializable {

    private final ProductService productService;
    private final EditProductFormLoader formLoader;
    private Product editProduct;

    @FXML
    private TextField tfTitleField;

    @FXML
    private TextField tfGenreField;

    @FXML
    private TextField tfPlatformField;

    @FXML
    private TextField tfPublisherField;

    @FXML
    private TextField tfDeveloperField;

    @FXML
    private TextField tfReleaseDateField; // Use a date picker instead if possible

    @FXML
    private TextField tfQuantityField;

    @FXML
    private TextField tfPriceField;

    public EditProductFormController(ProductService productService, EditProductFormLoader formLoader) {
        this.productService = productService;
        this.formLoader = formLoader;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize any necessary components or data here
    }

    // In setEditProduct, initialize new fields
    public void setEditProduct(Product product) {
        this.editProduct = product;
        if (product != null) {
            tfTitleField.setText(product.getTitle());
            tfGenreField.setText(product.getGenre());
            tfPlatformField.setText(product.getPlatform());
            tfPublisherField.setText(product.getPublisher());
            tfDeveloperField.setText(product.getDeveloper());
            tfReleaseDateField.setText(product.getReleaseDate() != null ? product.getReleaseDate().toString() : "");
            tfQuantityField.setText(String.valueOf(product.getQuantity()));
            tfPriceField.setText(String.valueOf(product.getPrice()));
        }
    }

    // Update goEdit to handle new fields
    @FXML
    public void goEdit() {
        if (editProduct == null) return;

        try {
            String title = tfTitleField.getText().trim();
            String genre = tfGenreField.getText().trim();
            String platform = tfPlatformField.getText().trim();
            String publisher = tfPublisherField.getText().trim();
            String developer = tfDeveloperField.getText().trim();
            LocalDate releaseDate = tfReleaseDateField.getText().isEmpty() ? null : LocalDate.parse(tfReleaseDateField.getText().trim());
            int quantity = Integer.parseInt(tfQuantityField.getText().trim());
            BigDecimal price = new BigDecimal(tfPriceField.getText().trim());

            // Update editProduct fields
            editProduct.setTitle(title);
            editProduct.setGenre(genre);
            editProduct.setPlatform(platform);
            editProduct.setPublisher(publisher);
            editProduct.setDeveloper(developer);
            editProduct.setReleaseDate(releaseDate);
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
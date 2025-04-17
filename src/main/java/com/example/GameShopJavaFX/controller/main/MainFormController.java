package com.example.GameShopJavaFX.controller.main;

import com.example.GameShopJavaFX.interfaces.ProductService;
import com.example.GameShopJavaFX.model.Product;
import com.example.GameShopJavaFX.tool.loader.main.MainFormLoader;
import com.example.GameShopJavaFX.tool.loader.product.AddProductFormLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MainFormController implements Initializable {

    private final MainFormLoader mainFormLoader;
    private final ProductService productService;
    private final AddProductFormLoader productFormLoader;

    @FXML
    private VBox mainVBox;

    @FXML
    private TableView<Product> productTableView;

    @FXML
    private TableColumn<Product, Long> tcId;

    @FXML
    private TableColumn<Product, String> tcName;

    @FXML
    private TableColumn<Product, Integer> tcQuantity;

    @FXML
    private TableColumn<Product, Double> tcPrice;

    @FXML
    private HBox EditProductHBox;

    public MainFormController(MainFormLoader mainFormLoader, ProductService productService, AddProductFormLoader productFormLoader) {
        this.mainFormLoader = mainFormLoader;
        this.productService = productService;
        this.productFormLoader = productFormLoader;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("MainFormController initialized");

        mainVBox.getChildren().add(0,mainFormLoader.loadMenuForm());

        tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tcPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        productTableView.setItems(productService.getListProducts());

        productTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            EditProductHBox.setVisible(newValue != null);
        });
    }

    @FXML
    private void showEditProductForm() {
        Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            productFormLoader.loadEditProductForm(selectedProduct);
        } else {
            System.out.println("No product selected");
        }
    }
}

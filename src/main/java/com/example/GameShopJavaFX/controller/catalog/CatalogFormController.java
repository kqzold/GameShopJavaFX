package com.example.GameShopJavaFX.controller.catalog;


import com.example.GameShopJavaFX.controller.card.CardProductController;
import com.example.GameShopJavaFX.interfaces.ProductService;
import com.example.GameShopJavaFX.model.Product;
import com.example.GameShopJavaFX.tool.SpringFXMLLoader;
import com.example.GameShopJavaFX.tool.loader.catalog.CatalogFormLoader;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.FlowPane;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;

@Component
public class CatalogFormController implements Initializable {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CatalogFormController.class);

    @FXML
    private MenuBar menuBar;

    @FXML
    private FlowPane flowPaneProduct;

    private final ProductService productService;
    private final CatalogFormLoader catalogFormLoader;
    private final SpringFXMLLoader springFXMLLoader;

    public CatalogFormController(ProductService productService, CatalogFormLoader catalogFormLoader, SpringFXMLLoader springFXMLLoader) {
        this.productService = productService;
        this.catalogFormLoader = catalogFormLoader;
        this.springFXMLLoader = springFXMLLoader;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadProductCards();
    }

    private void loadProductCards() {
        flowPaneProduct.getChildren().clear();
        CompletableFuture.runAsync(() -> {
            productService.getListProducts().forEach(product -> {
                Platform.runLater(() -> addProductCard(product));
            });
        });
    }

    private void addProductCard(Product product) {
        try {
            FXMLLoader fxmlLoader = springFXMLLoader.load("/view/cards/CardProduct.fxml");
            Node cardNode = fxmlLoader.load();

            CardProductController cardProductController = fxmlLoader.getController();
            cardProductController.setProduct(product);
            cardProductController.setFormLoader(catalogFormLoader);

            flowPaneProduct.getChildren().add(cardNode);
        } catch (IOException e) {
            logger.error("Error loading product card for product: {}", product, e);
        }
    }

    @FXML
    private void goHome() {
        System.out.println("Home button clicked");
        catalogFormLoader.loadMainFormCatalog();
    }

    @FXML
    private void reloadCatalog() {
        System.out.println("Reload button clicked");
        loadProductCards();
    }

    @FXML
    private void showProfile() {
        System.out.println("Profile button clicked");
        catalogFormLoader.loadProfileForm();
    }

    @FXML
    private void logout() {
        System.out.println("Logout button clicked");
        catalogFormLoader.loadLoginForm();
    }
}

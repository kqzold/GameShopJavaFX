package com.example.GameShopJavaFX.controller.card;

import com.example.GameShopJavaFX.model.Product;
import com.example.GameShopJavaFX.tool.loader.card.CardProductFormLoader;
import com.example.GameShopJavaFX.tool.loader.catalog.CatalogFormLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;


public class CardProductController {

    @FXML
    private ImageView imgProduct;

    @FXML
    private Label lblTitle; // Ранее lblName

    @FXML
    private Label lblGenre; // Ранее lblCategory

    @FXML
    private Label lblPlatform;

    @FXML
    private Label lblPublisher;

    @FXML
    private Label lblDeveloper;

    @FXML
    private Label lblReleaseDate;

    @FXML
    private Label lblPrice;

    @FXML
    private Label lblQuantity;

    @FXML
    private Button btnBuy;

    private Product product;
    private CardProductFormLoader cardProductFormLoader;

    public void setProduct(Product product) {
        this.product = product;
        lblTitle.setText(product.getTitle());
        lblGenre.setText(product.getGenre());
        lblPlatform.setText(product.getPlatform() != null ? product.getPlatform() : "N/A");
        lblPrice.setText(product.getPrice().toPlainString());
        lblQuantity.setText(String.valueOf(product.getQuantity()));
        lblPublisher.setText(product.getPublisher() != null ? product.getPublisher() : "N/A");
        lblDeveloper.setText(product.getDeveloper() != null ? product.getDeveloper() : "N/A");
        lblReleaseDate.setText(product.getReleaseDate() != null ? product.getReleaseDate().toString() : "N/A");
    }

    public void setFormLoader(CatalogFormLoader catalogFormLoader) {
        this.cardProductFormLoader = cardProductFormLoader;
    }

    @FXML
    private void buyProduct() {
        if (product.getQuantity() == 0) {
            System.out.println("Product is out of stock.");
            return;
        }

        if (cardProductFormLoader != null) {
            cardProductFormLoader.loadUserOrderForm(product);
        } else {
            System.out.println("CardProductFormLoader is not set.");
        }
    }
}
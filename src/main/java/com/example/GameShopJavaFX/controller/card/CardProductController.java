package com.example.GameShopJavaFX.controller.card;

import com.example.GameShopJavaFX.model.Product;
import com.example.GameShopJavaFX.tool.loader.card.CardProductFormLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javax.swing.text.html.ImageView;

public class CardProductController {

    @FXML
    private ImageView imgProduct;

    @FXML
    private Label lblName;

    @FXML
    private Label lblCategory;

    @FXML
    private Label lblPrice;

    @FXML
    private Label lblQuantity;

    @FXML
    private Button btnBuy;

    private Product product;
    private final CardProductFormLoader cardProductFormLoader;

    public void setProduct(Product product) {
        this.product = product;
        lblName.setText(product.getName());
        lblCategory.setText(product.getCategory());
        lblPrice.setText(String.valueOf(product.getPrice()));
        lblQuantity.setText(String.valueOf(product.getQuantity()));
    }

    public void setFormLoader(CatalogFormLoader catalogFormLoader) {
        this.cardProductFormLoader = catalogFormLoader;
    }

    @FXML
    private void buyProduct() {
        if (cardProductFormLoader != null) {
            cardProductFormLoader.loadProductForm(product);
        } else {
            System.out.println("CardProductFormLoader is not set.");
        }
    }
}

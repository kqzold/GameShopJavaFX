package com.example.GameShopJavaFX.controller.customer;

import com.example.GameShopJavaFX.interfaces.AppCustomerService;
import com.example.GameShopJavaFX.model.Customer;
import com.example.GameShopJavaFX.tool.FormLoader;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowCustomerListController implements Initializable {

    private final FormLoader formLoader;
    private final AppCustomerService appCustomerService;

    @FXML
    private TableView<Customer> customerTableView;

    @FXML
    private TableColumn<Customer, Long> idColumn;

    @FXML
    private TableColumn<Customer, String> nameColumn;

    @FXML
    private TableColumn<Customer, Double> balanceColumn;

    @FXML
    private HBox hbEditCustomer;

    public ShowCustomerListController(AppCustomerService appCustomerService, FormLoader formLoader) {
        this.appCustomerService = appCustomerService;
        this.formLoader = formLoader;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        balanceColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));

        ObservableList<Customer> customers = appCustomerService.getListCustomers();
        customerTableView.setItems(customers);

        customerTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            hbEditCustomer.setVisible(newValue != null);
        });
    }


}

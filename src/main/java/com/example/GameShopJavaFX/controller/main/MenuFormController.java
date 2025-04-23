package com.example.GameShopJavaFX.controller.main;

import com.example.GameShopJavaFX.interfaces.AppCustomerService;
import com.example.GameShopJavaFX.service.AppCustomerServiceImpl;
import com.example.GameShopJavaFX.tool.loader.main.MenuFormLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MenuFormController implements Initializable {

    private final MenuFormLoader menuFormLoader;
    private final AppCustomerService appCustomerService;

    @FXML
    private Menu mProduct;

    @FXML
    private Menu mAdmin;

    @FXML
    private Menu mUsers;

    @FXML
    private MenuItem miEnter;

    @FXML
    private MenuItem miProfile;

    @FXML
    private MenuItem miLogout;

    @FXML
    private MenuItem miAddCustomer;

    @FXML
    private MenuItem miShowCustomerList;

    @FXML
    private MenuItem miAddProduct;

    @FXML
    private MenuItem miListProducts;

    @FXML
    private MenuItem miPlaceOrder;

    public MenuFormController(MenuFormLoader menuFormLoader, AppCustomerService appCustomerService) {
        this.menuFormLoader = menuFormLoader;
        this.appCustomerService = appCustomerService;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initMenuVisible();
    }

    private void initMenuVisible() {
        if(appCustomerService.getCurrentCustomer() == null) {
            mAdmin.setVisible(false);
            mUsers.setVisible(false);
            mProduct.setVisible(false);


            miEnter.setVisible(true);
            miProfile.setVisible(false);
            miLogout.setVisible(false);
            miAddCustomer.setVisible(false);
            miShowCustomerList.setVisible(false);
            miAddProduct.setVisible(false);
            miListProducts.setVisible(false);
            miPlaceOrder.setVisible(false);
            return;

        }

        if (appCustomerService.getCurrentCustomer().getRoles().contains(AppCustomerServiceImpl.ROLES.ADMIN.toString())) {

            mAdmin.setVisible(true);
            mUsers.setVisible(true);
            mProduct.setVisible(true);

            miEnter.setVisible(false);
            miProfile.setVisible(true);
            miLogout.setVisible(true);
            miAddCustomer.setVisible(true);
            miShowCustomerList.setVisible(true);
            miAddProduct.setVisible(true);
            miListProducts.setVisible(true);
            miPlaceOrder.setVisible(true);

        } else if (appCustomerService.getCurrentCustomer().getRoles().contains(AppCustomerServiceImpl.ROLES.MANAGER.toString())) {
            mAdmin.setVisible(false);
            mUsers.setVisible(false);
            mProduct.setVisible(true);

            miEnter.setVisible(false);
            miProfile.setVisible(true);
            miLogout.setVisible(true);
            miAddCustomer.setVisible(false);
            miShowCustomerList.setVisible(false);
            miAddProduct.setVisible(true);
            miListProducts.setVisible(true);
            miPlaceOrder.setVisible(true);

        } else if (appCustomerService.getCurrentCustomer().getRoles().contains(AppCustomerServiceImpl.ROLES.USER.toString())) {

            mAdmin.setVisible(false);
            mUsers.setVisible(true);
            mProduct.setVisible(true);

            miEnter.setVisible(false);
            miProfile.setVisible(true);
            miLogout.setVisible(true);
            miAddCustomer.setVisible(false);
            miShowCustomerList.setVisible(false);
            miAddProduct.setVisible(false);
            miListProducts.setVisible(true);
            miPlaceOrder.setVisible(false);

        }
    }

    @FXML
    private void showAddProductForm() {
        menuFormLoader.showAddProductForm();
    }

    @FXML
    private void showAddCustomerForm() {
        menuFormLoader.showAddCustomerForm();
    }

    @FXML
    private void showLoginForm(){
        menuFormLoader.loadLoginForm();
    }

    @FXML
    private void logout(){

        appCustomerService.logout();
        menuFormLoader.loadLoginForm();
    }

    @FXML
    private void showCustomersForm(){
        menuFormLoader.showCustomersForm();
    }

    @FXML
    private void showProfileForm() {
        menuFormLoader.loadProfileForm();
    }

    @FXML
    private void showOrderForm() {
        menuFormLoader.loadOrderForm();
    }

    @FXML
    private void showEditCustomer(ActionEvent event) {
        System.out.println("showEditCustomer invoked.");
    }

    @FXML
    private void showCatalogForm() {
        menuFormLoader.loadCatalogForm();
    }

    @FXML
    private void showRevenueForm() {
        menuFormLoader.loadRevenueForm();
    }

    @FXML
    private void showRatingForm() {
        menuFormLoader.loadRatingChartForm();
    }
}

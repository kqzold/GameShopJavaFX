package com.example.GameShopJavaFX.interfaces;

import com.example.GameShopJavaFX.model.Customer;
import javafx.collections.ObservableList;


public interface AppCustomerService extends AppService<Customer> {
    void initSuperUser();
    boolean authentication(String name, String password);
    ObservableList<Customer> getListCustomers();
    Customer getCurrentCustomer();
    void logout();


}

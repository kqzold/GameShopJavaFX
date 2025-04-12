package com.example.GameShopJavaFX.interfaces;

import com.example.GameShopJavaFX.model.Customer;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Service;


@Service
public interface AppCustomerService extends AppService<Customer> {
    void initSuperUser();
    boolean authentication(String name, String password);
    ObservableList<Customer> getListCustomers();
    Customer getCurrentCustomer();
    void logout();


}

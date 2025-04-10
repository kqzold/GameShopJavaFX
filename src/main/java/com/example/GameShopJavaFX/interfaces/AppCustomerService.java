package com.example.GameShopJavaFX.interfaces;

import com.example.GameShopJavaFX.model.Customer;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AppCustomerService {
    void initSuperUser();
    boolean authenticate(String name, String password);
    ObservableList<Customer> getListCustomers();
    Customer getCurrentCustomer();
    Optional<Customer> add(Customer customer);

}

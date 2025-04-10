package com.example.GameShopJavaFX.context;

import com.example.GameShopJavaFX.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CurrentCustomerContext {

    private Customer currentCustomer;

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public void setCurrentCustomer(Customer currentCustomer) {
        this.currentCustomer = currentCustomer;
    }
}

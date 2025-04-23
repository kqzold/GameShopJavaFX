package com.example.GameShopJavaFX.service;

import com.example.GameShopJavaFX.context.CurrentCustomerContext;
import com.example.GameShopJavaFX.interfaces.AppCustomerService;
import com.example.GameShopJavaFX.model.Customer;
import com.example.GameShopJavaFX.repository.CustomerRepository;
import javafx.collections.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AppCustomerServiceImpl implements AppCustomerService {

    private final CustomerRepository customerRepository;
    private final CurrentCustomerContext currentCustomerContext;

    public enum ROLES {
        USER,
        ADMIN,
        MANAGER
    }

    public AppCustomerServiceImpl(CustomerRepository customerRepository, CurrentCustomerContext currentCustomerContext) {
        this.customerRepository = customerRepository;
        this.currentCustomerContext = currentCustomerContext;
        initSuperUser();
    }

    @Override
    public void initSuperUser() {
        if (customerRepository.count() > 0) {
            return;
        }

        Customer admin = new Customer();
        admin.setName("admin");
        admin.setPassword("12345678"); // Зашифрованный пароль
        admin.setEmail("admin@gmail.com");
        admin.setAddress("Default Admin Address");
        admin.setBalance(0.0);
        admin.getRoles().add(ROLES.ADMIN.toString());
        admin.getRoles().add(ROLES.MANAGER.toString());
        admin.getRoles().add(ROLES.USER.toString());

        customerRepository.save(admin);
    }

    @Override
    public Optional<Customer> add(Customer user) {
        if (user == null || user.getName() == null || user.getName().isBlank()) {
            throw new IllegalArgumentException("Имя пользователя не может быть пустым");
        }

        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email не может быть пустым");
        }

        if (user.getPassword() == null || user.getPassword().length() < 8) {
            throw new IllegalArgumentException("Пароль должен содержать не менее 8 символов");
        }

        if (user.getBalance() < 0) {
            throw new IllegalArgumentException("Баланс не может быть отрицательным");
        }

        if (user.getId() == null && customerRepository.existsByName(user.getName())) {
            throw new IllegalArgumentException("Пользователь с логином '" + user.getName() + "' уже существует");
        }

        // Устанавливаем начальное значение баланса, если не указано
        if (user.getBalance() == 0) {
            user.setBalance(0.0);
        }

        return Optional.of(customerRepository.save(user));
    }

    @Override
    public boolean authentication(String name, String password) {
        Optional<Customer> optionalAppUser = customerRepository.findByName(name);
        if (optionalAppUser.isEmpty()) {
            return false;
        }
        Customer loginUser = optionalAppUser.get();
        if (!loginUser.getPassword().equals(password)) {
            return false;
        }
        // Устанавливаем текущего пользователя через контекст вместо статического поля
        currentCustomerContext.setCurrentCustomer(loginUser);
        return true;

    }

        @Override
        public ObservableList<Customer> getListCustomers() {
            List<Customer> customers = customerRepository.findAll();
            return FXCollections.observableArrayList(customers);
        }

        @Override
        public Customer getCurrentCustomer () {
            return currentCustomerContext.getCurrentCustomer();
        }

        @Override
        public void logout () {
            currentCustomerContext.setCurrentCustomer(null);
        }
}

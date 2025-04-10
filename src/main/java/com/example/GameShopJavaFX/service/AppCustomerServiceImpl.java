package com.example.GameShopJavaFX.service;

import com.example.GameShopJavaFX.interfaces.AppCustomerService;
import com.example.GameShopJavaFX.model.Customer;
import com.example.GameShopJavaFX.repository.CustomerRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppCustomerServiceImpl implements AppCustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    private Customer currentCustomer;

    public enum Role {
        USER,
        ADMIN,
        MANAGER
    }

    public AppCustomerServiceImpl(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        initSuperUser();
    }

    @Override
    public void initSuperUser() {
        if (customerRepository.count() > 0) return;

        Customer admin = new Customer();
        admin.setName("admin");
        admin.setPassword(passwordEncoder.encode("123")); // Зашифрованный пароль
        admin.setEmail("admin@gmail.com");
        admin.getRoles().add(Role.ADMIN.name());
        admin.getRoles().add(Role.MANAGER.name());
        admin.getRoles().add(Role.USER.name());

        customerRepository.save(admin);
    }

    @Override
    public Optional<Customer> add(Customer customer) {
        if (customer == null || customer.getName() == null || customer.getName().isBlank()) {
            throw new IllegalArgumentException("Имя пользователя не может быть пустым");
        }

        if (customer.getId() == null && customerRepository.existsByName(customer.getName())) {
            throw new IllegalArgumentException("Пользователь с логином '" + customer.getName() + "' уже существует");
        }

        // Шифруем пароль
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));

        return Optional.of(customerRepository.save(customer));
    }

    @Override
    public boolean authenticate(String name, String password) {
        if (name == null || password == null) return false;

        return customerRepository.findByName(name)
                .filter(c -> passwordEncoder.matches(password, c.getPassword()))
                .map(c -> {
                    currentCustomer = c;
                    return true;
                })
                .orElse(false);
    }

    @Override
    public ObservableList<Customer> getListCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return FXCollections.observableArrayList(customers);
    }

    @Override
    public Customer getCurrentCustomer() {
        return currentCustomer;
    }
}

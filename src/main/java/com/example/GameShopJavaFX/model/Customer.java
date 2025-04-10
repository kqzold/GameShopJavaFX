package com.example.GameShopJavaFX.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String email;
    private String password;
    private String address;

    @Column(nullable = false, columnDefinition = "double default 0.0")
    private double balance;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "customer_roles", joinColumns = @JoinColumn(name = "customer_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Set<String> roles = new HashSet<>();

    public Customer() {}

    public Customer(String name, String email, String password, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.balance = 0.0;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword(){
        return password;
    }
    public String getAddress() {
        return address;
    }
    public double getBalance() {
        return balance;
    }
    public Set<String> getRoles() {
        return roles;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer customer)) return false;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + password + '\'' +
                ", address='" + address + '\'' +
                ", balance=" + balance +
                '}';
    }
}

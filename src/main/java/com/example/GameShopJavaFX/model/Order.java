package com.example.GameShopJavaFX.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "orders") // избегаем конфликта с зарезервированным словом
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private int quantity;

    private BigDecimal totalPrice;

    private LocalDateTime orderDate;

    public Order() {}

    public Order(Customer customer, Product product, int quantity) {
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = calculateTotalPrice(product.getPrice(), quantity);  // вычисляем totalPrice автоматически
        this.orderDate = LocalDateTime.now();
    }

    private BigDecimal calculateTotalPrice(BigDecimal price, int quantity) {
        return price.multiply(BigDecimal.valueOf(quantity));  // пример для вычисления цены
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.totalPrice = calculateTotalPrice(product.getPrice(), quantity);  // обновляем totalPrice при изменении quantity
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer.getId() +
                ", product=" + product.getId() +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                ", orderDate=" + orderDate +
                '}';
    }
}

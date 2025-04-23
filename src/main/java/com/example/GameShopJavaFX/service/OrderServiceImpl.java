package com.example.GameShopJavaFX.service;

import com.example.GameShopJavaFX.interfaces.OrderService;
import com.example.GameShopJavaFX.model.Order;
import com.example.GameShopJavaFX.repository.OrderRepository;
import javafx.collections.*;
import org.springframework.stereotype.Service;

import java.math.*;
import java.time.*;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Optional<Order> add(Order order) {
        // Проверка доступности продукта на складе
        if (order.getProduct().getQuantity() < order.getQuantity()) {
            throw new IllegalStateException("Недостаточно товара на складе");
        }

        // Расчет общей стоимости, если она не указана
        if (order.getTotalPrice() == null) {
            BigDecimal totalPrice = order.getProduct().getPrice()
                    .multiply(BigDecimal.valueOf(order.getQuantity()));
            order.setTotalPrice(totalPrice);
        }

        return Optional.of(orderRepository.save(order));
    }

    @Override
    public ObservableList<Order> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return FXCollections.observableArrayList(orders);
    }

    @Override
    public BigDecimal calculateRevenueForDay(LocalDate day) {
        LocalDateTime start = day.atStartOfDay();
        LocalDateTime end = day.atTime(LocalTime.MAX);
        Double revenue = orderRepository.calculateRevenueBetween(start, end);
        return revenue != null ? BigDecimal.valueOf(revenue).setScale(2, RoundingMode.HALF_UP) : BigDecimal.ZERO;
    }

    @Override
    public BigDecimal calculateRevenueForMonth(int year, int month) {
        LocalDateTime start = LocalDateTime.of(year, month, 1, 0, 0);
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate endOfMonth = yearMonth.atEndOfMonth();
        LocalDateTime end = endOfMonth.atTime(LocalTime.MAX);
        Double revenue = orderRepository.calculateRevenueBetween(start, end);
        return revenue != null ? BigDecimal.valueOf(revenue).setScale(2, RoundingMode.HALF_UP) : BigDecimal.ZERO;
    }

    @Override
    public BigDecimal calculateRevenueForYear(int year) {
        LocalDateTime start = LocalDateTime.of(year, 1, 1, 0, 0);
        LocalDateTime end = LocalDateTime.of(year, 12, 31, 23, 59, 59);
        Double revenue = orderRepository.calculateRevenueBetween(start, end);
        return revenue != null ? BigDecimal.valueOf(revenue).setScale(2, RoundingMode.HALF_UP) : BigDecimal.ZERO;
    }

    @Override
    public List<Object[]> getSalesRatingAllTime() {
        return orderRepository.findProductSalesRatingAllTime();
    }

    @Override
    public List<Object[]> getSalesRatingByYear(int year) {
        return orderRepository.findProductSalesRatingByYear(year);
    }

    @Override
    public List<Object[]> getSalesRatingByMonth(int year, int month) {
        return orderRepository.findProductSalesRatingByMonth(month);
    }

    @Override
    public List<Object[]> getSalesRatingByWeek(int year, int week) {
        return orderRepository.findProductSalesRatingByWeek(week);
    }
}
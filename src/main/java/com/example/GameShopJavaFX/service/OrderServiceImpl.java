package com.example.GameShopJavaFX.service;

import com.example.GameShopJavaFX.interfaces.OrderService;
import com.example.GameShopJavaFX.model.Order;
import com.example.GameShopJavaFX.repository.OrderRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Optional<Order> add(Order order) {
        return Optional.of(orderRepository.save(order));
    }

    @Override
    public ObservableList<Order> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return FXCollections.observableArrayList(orders);
    }

    @Override
    public Double calculateRevenueForDay(LocalDate day) {
        LocalDateTime start = day.atStartOfDay();
        LocalDateTime end = day.atTime(LocalTime.MAX);
        return orderRepository.calculateRevenueBetween(start, end);
    }


    @Override
    public Double calculateRevenueForMonth(int year, int month) {
        LocalDateTime start = LocalDateTime.of(year, month, 1, 0, 0);
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate endOfMonth = yearMonth.atEndOfMonth();
        LocalDateTime end = endOfMonth.atTime(LocalTime.MAX);
        return orderRepository.calculateRevenueBetween(start, end);
    }

    @Override
    public Double calculateRevenueForYear(int year) {
        LocalDateTime start = LocalDateTime.of(year, 1, 1, 0, 0);
        LocalDateTime end = LocalDateTime.of(year, 12, 31, 23, 59, 59);
        return orderRepository.calculateRevenueBetween(start, end);
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

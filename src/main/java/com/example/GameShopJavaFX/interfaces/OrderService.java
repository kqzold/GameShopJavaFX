package com.example.GameShopJavaFX.interfaces;

import com.example.GameShopJavaFX.model.Order;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.List;

public interface OrderService extends AppService<Order> {
    ObservableList<Order> getAllOrders();

    Double calculateRevenueForDay(LocalDate day);

    Double calculateRevenueForMonth(int year, int month);

    Double calculateRevenueForYear(int year);

    List<Object[]> getSalesRatingAllTime();
    List<Object[]> getSalesRatingByMonth(int year, int month);
    List<Object[]> getSalesRatingByYear(int year);
    List<Object[]> getSalesRatingByWeek(int year, int week);

}

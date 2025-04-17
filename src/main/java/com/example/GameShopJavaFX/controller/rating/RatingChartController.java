package com.example.GameShopJavaFX.controller.rating;


import com.example.GameShopJavaFX.interfaces.OrderService;
import com.example.GameShopJavaFX.model.Product;
import com.example.GameShopJavaFX.tool.loader.main.MainFormLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.util.StringConverter;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class RatingChartController implements Initializable {

    @FXML private BarChart<String, Number> barChartRating;
    @FXML private CategoryAxis xAxis;
    @FXML private NumberAxis yAxis;

    private final OrderService orderService;
    private final MainFormLoader mainFormLoader;

    public RatingChartController(OrderService orderService, MainFormLoader mainFormLoader) {
        this.orderService = orderService;
        this.mainFormLoader = mainFormLoader;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        xAxis.setLabel("Товар");
        yAxis.setLabel("Продано");

        // 1) Устанавливаем шаг равным 1
        yAxis.setTickUnit(1);
        yAxis.setMinorTickCount(0);

        // 2) Задаём формат меток, чтобы выводились только целые числа
        yAxis.setTickLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number value) {
                return String.format("%.0f", value.doubleValue());
            }
            @Override
            public Number fromString(String string) {
                return Double.valueOf(string);
            }
        });

        // По умолчанию показываем рейтинг за всё время
        showAllTimeRating();
    }

    @FXML
    private void showAllTimeRating() {
        List<Object[]> data = orderService.getSalesRatingAllTime();
        updateChart("За всё время", data);
    }

    @FXML
    private void showYearRating() {
        int year = LocalDate.now().getYear();
        List<Object[]> data = orderService.getSalesRatingByYear(year);
        updateChart("За год " + year, data);
    }

    @FXML
    private void showMonthRating() {
        int year = LocalDate.now().getYear();
        int month = LocalDate.now().getMonthValue();
        List<Object[]> data = orderService.getSalesRatingByMonth(year, month);
        updateChart("За " + month + "." + year, data);
    }

    @FXML
    private void showWeekRating() {
        int year = LocalDate.now().getYear();
        int week = LocalDate.now().get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
        List<Object[]> data = orderService.getSalesRatingByWeek(year, week);
        updateChart("За неделю " + week + " (" + year + ")", data);
    }

    private void updateChart(String category, List<Object[]> data) {
        barChartRating.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(category);

        ObservableList<XYChart.Data<String, Number>> chartData = FXCollections.observableArrayList();
        if (data != null) {
            for (Object[] row : data) {
                Product product = (Product) row[0];
                Long sold = (Long) row[1];
                chartData.add(new XYChart.Data<>(product.getName(), sold != null ? sold : 0));
            }
        }
        series.setData(chartData);
        barChartRating.getData().add(series);
    }

    @FXML
    private void cancel() {
        mainFormLoader.loadMainForm();
    }
}
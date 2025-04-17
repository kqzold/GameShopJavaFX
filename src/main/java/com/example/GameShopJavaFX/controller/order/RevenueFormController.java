package com.example.GameShopJavaFX.controller.order;

import com.example.GameShopJavaFX.interfaces.OrderService;
import com.example.GameShopJavaFX.tool.loader.main.MainFormLoader;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class RevenueFormController implements Initializable {

    @FXML
    private RadioButton dayRadioButton;

    @FXML
    private RadioButton monthRadioButton;

    @FXML
    private RadioButton yearRadioButton;

    @FXML
    private StackPane spPeriodContainer;

    @FXML
    private HBox hBoxDay;

    @FXML
    private HBox hBoxMonth;

    @FXML
    private HBox hBoxYear;

    @FXML
    private ComboBox<String> cbDay;

    @FXML
    private ComboBox<String> cbMonth;

    @FXML
    private ComboBox<String> cbYear;

    @FXML
    private ComboBox<String> cbYearMonth;

    @FXML
    private Label lblRevenue;

    private ToggleGroup periodToggleGroup;

    private final OrderService orderService;
    private final MainFormLoader mainFormLoader;

    public RevenueFormController(OrderService orderService, MainFormLoader mainFormLoader) {
        this.orderService = orderService;
        this.mainFormLoader = mainFormLoader;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        periodToggleGroup = new ToggleGroup();
        dayRadioButton.setToggleGroup(periodToggleGroup);
        monthRadioButton.setToggleGroup(periodToggleGroup);
        yearRadioButton.setToggleGroup(periodToggleGroup);

        dayRadioButton.setSelected(true);

        initComboBoxes();

        hBoxDay.setVisible(true);
        hBoxMonth.setVisible(false);
        hBoxYear.setVisible(false);

        periodToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == dayRadioButton) {
                hBoxDay.setVisible(true);
                hBoxMonth.setVisible(false);
                hBoxYear.setVisible(false);
            } else if (newValue == monthRadioButton) {
                hBoxDay.setVisible(false);
                hBoxMonth.setVisible(true);
                hBoxYear.setVisible(false);
            } else if (newValue == yearRadioButton) {
                hBoxDay.setVisible(false);
                hBoxMonth.setVisible(false);
                hBoxYear.setVisible(true);
            }
        });
    }

    private void initComboBoxes() {
        // Список дней от 1 до 31
        List<String> days = new ArrayList<>();
        for (int i = 1; i <= 31; i++) {
            days.add(String.valueOf(i));
        }
        cbDay.setItems(FXCollections.observableArrayList(days));

        // Список месяцев от 1 до 12
        List<String> months = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            months.add(String.valueOf(i));
        }
        cbMonth.setItems(FXCollections.observableArrayList(months));

        // Список годов (например, 2023-2025)
        List<String> years = new ArrayList<>();
        for (int y = 2023; y <= 2025; y++) {
            years.add(String.valueOf(y));
        }
        cbYearMonth.setItems(FXCollections.observableArrayList(months));
        cbYear.setItems(FXCollections.observableArrayList(years));
    }

    @FXML
    private void calculateRevenue() {
        RadioButton selectedRadioButton = (RadioButton) periodToggleGroup.getSelectedToggle();
        if (selectedRadioButton == null) {
            lblRevenue.setText("Выберите период");
            return;
        }

        double revenue = 0.0;
        try {
            if (selectedRadioButton == dayRadioButton) {
                String dayStr = cbDay.getSelectionModel().getSelectedItem();
                if (dayStr == null) {
                    lblRevenue.setText("Выберите день");
                    return;
                }
                int day = Integer.parseInt(dayStr);
                LocalDate date = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), day);
                Double result = orderService.calculateRevenueForDay(date);
                if (result == null) {
                    lblRevenue.setText("За " + date + " не было покупок.");
                } else {
                    revenue = result;
                    lblRevenue.setText("Выручка за " + date + ": " + revenue);
                }
            } else if (selectedRadioButton == monthRadioButton) {
                String monthStr = cbMonth.getSelectionModel().getSelectedItem();
                String yearStr = cbYearMonth.getSelectionModel().getSelectedItem();
                if (monthStr == null || yearStr == null) {
                    lblRevenue.setText("Выберите месяц и год");
                    return;
                }
                int month = Integer.parseInt(monthStr);
                int year = Integer.parseInt(yearStr);
                LocalDate date = LocalDate.of(year, month, 1);
                Double result = orderService.calculateRevenueForMonth(year, month);
                if (result == null) {
                    lblRevenue.setText("За " + date + " не было покупок.");
                } else {
                    revenue = result;
                    lblRevenue.setText("Выручка за " + date + ": " + revenue);
                }
            } else if (selectedRadioButton == yearRadioButton) {
                String monthStr = cbMonth.getSelectionModel().getSelectedItem();
                String yearStr = cbYearMonth.getSelectionModel().getSelectedItem();
                if (monthStr == null || yearStr == null) {
                    lblRevenue.setText("Выберите месяц и год.");
                    return;
                }
                int month = Integer.parseInt(monthStr);
                int year = Integer.parseInt(yearStr);
                Double result = orderService.calculateRevenueForMonth(year, month);
                if (result == null) {
                    lblRevenue.setText("За " + month + "." + year + " не было покупок.");
                } else {
                    revenue = result;
                    lblRevenue.setText("Доход за " + month + "." + year + ": " + revenue);
                }
            }
        } catch (Exception e) {
            lblRevenue.setText("Произошла ошибка при расчете выручки. Проверьте введенные данные.");
        }
    }

    @FXML
    private void cancel() {
        mainFormLoader.loadMainForm();
    }
}
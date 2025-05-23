package com.example.GameShopJavaFX;

import com.example.GameShopJavaFX.tool.loader.main.MainFormLoader;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class GameShopJavaFxApplication extends Application {

	public static ConfigurableApplicationContext applicationContext;
	public static Stage primaryStage;

	public static void main(String[] args) {
		applicationContext = SpringApplication.run(GameShopJavaFxApplication.class, args);
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		MainFormLoader mainFormLoader = applicationContext.getBean(MainFormLoader.class);
		mainFormLoader.loadLoginForm();

	}
}
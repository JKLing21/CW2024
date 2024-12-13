package com.example.demo;

import java.lang.reflect.InvocationTargetException;

import com.example.demo.controller.Controller;

import javafx.application.Application;
import javafx.stage.Stage;
/**
 * Main class serves as entry point for the application. 
 * It initialises game window and sets up the controller.
 */
public class Main extends Application {

	private static final int SCREEN_WIDTH = 1300;
	private static final int SCREEN_HEIGHT = 750;
	private static final String TITLE = "THE SKYLORD";
	private Controller myController;
	/**
     * start method is called when the JavaFX application is launched. 
     * It initialises primary stage and sets up the game controller.
     */
	@Override
	public void start(Stage stage) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		stage.setTitle(TITLE);
		stage.setResizable(false);
		stage.setHeight(SCREEN_HEIGHT);
		stage.setWidth(SCREEN_WIDTH);
		myController = new Controller(stage, SCREEN_WIDTH); 
		myController.showMainMenu();
	}
	/**
     * main method is entry point of the application which launches the JavaFX application.
     *
     * @param args: Command-line arguments passed to the application.
     */
	public static void main(String[] args) {
		launch();
	}
}
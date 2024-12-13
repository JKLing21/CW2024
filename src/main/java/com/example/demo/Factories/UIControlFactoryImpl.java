package com.example.demo.Factories;

import com.example.demo.Factories.Interfaces.UIControlFactory;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
/**
 * UIControlFactoryImpl class is responsible for creating various UI controls by implementing the UIControlFactory interface.
 */
public class UIControlFactoryImpl implements UIControlFactory {
	/**
     * Creates a new Label with the specified text and style classes.
     *
     * @param text The text to be displayed on the label.
     * @param styleClasses The style classes to be applied to the label.
     * @return A new Label object.
     */
    @Override
    public Label createLabel(String text, String... styleClasses) {
        Label label = new Label(text);
        label.getStyleClass().addAll(styleClasses);
        return label;
    }
    /**
     * Creates a new Button with the specified text and action.
     *
     * @param text The text to be displayed on the button.
     * @param action The action to be performed when the button is clicked.
     * @return A new Button object.
     */
    @Override
    public Button createButton(String text, EventHandler<ActionEvent> action) {
        Button button = new Button(text);
        button.setOnAction(action);
        return button;
    }
    /**
     * Creates a new Slider with the specified minimum, maximum, value, and width.
     *
     * @param min The minimum value of the slider.
     * @param max The maximum value of the slider.
     * @param value The initial value of the slider.
     * @param width The width of the slider.
     * @return A new Slider object.
     */
    @Override
    public Slider createSlider(double min, double max, double value, double width) {
        Slider slider = new Slider(min, max, value);
        slider.setPrefWidth(width);
        return slider;
    }
    /**
     * Creates a new ToggleButton with the specified initial selection state.
     *
     * @param selected The initial selection state of the toggle button.
     * @return A new ToggleButton object.
     */
    @Override
    public ToggleButton createToggleButton(boolean selected) {
        ToggleButton toggleButton = new ToggleButton();
        toggleButton.setSelected(selected);
        return toggleButton;
    }
    /**
     * Creates a new menu Button with the specified text and action.
     *
     * @param text The text to be displayed on the button.
     * @param action The action to be performed when the button is clicked.
     * @return A new Button object.
     */
    @Override
    public Button createMenuButton(String text, EventHandler<ActionEvent> action) {
        Button button = new Button(text);
        button.setOnAction(action);
        button.getStyleClass().add("menu-button");
        return button;
    }
}
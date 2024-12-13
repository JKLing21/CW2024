package com.example.demo.Factories.Interfaces;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
/**
 * The UIControlFactory interface defines methods for creating various UI controls.
 */
public interface UIControlFactory {
	/**
     * Creates a new Label with the specified text and style classes.
     *
     * @param text The text to be displayed on the label.
     * @param styleClasses The style classes to be applied to the label.
     * @return A new Label object.
     */
    Label createLabel(String text, String... styleClasses);
    /**
     * Creates a new Button with the specified text and action.
     *
     * @param text The text to be displayed on the button.
     * @param action The action to be performed when the button is clicked.
     * @return A new Button object.
     */
    Button createButton(String text, EventHandler<ActionEvent> action);
    /**
     * Creates a new Slider with the specified minimum, maximum, value, and width.
     *
     * @param min The minimum value of the slider.
     * @param max The maximum value of the slider.
     * @param value The initial value of the slider.
     * @param width The width of the slider.
     * @return A new Slider object.
     */
    Slider createSlider(double min, double max, double value, double width);
    /**
     * Creates a new ToggleButton with the specified initial selection state.
     *
     * @param selected The initial selection state of the toggle button.
     * @return A new ToggleButton object.
     */
    ToggleButton createToggleButton(boolean selected);
    /**
     * Creates a new menu Button with the specified text and action.
     *
     * @param text The text to be displayed on the button.
     * @param action The action to be performed when the button is clicked.
     * @return A new Button object.
     */
    Button createMenuButton(String text, EventHandler<ActionEvent> action);
}
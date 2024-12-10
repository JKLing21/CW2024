package factories.interfaces;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;

public interface UIControlFactory {

    Label createLabel(String text, String... styleClasses);

    Button createButton(String text, EventHandler<ActionEvent> action);

    Slider createSlider(double min, double max, double value, double width);

    ToggleButton createToggleButton(boolean selected);

    Button createMenuButton(String text, EventHandler<ActionEvent> action);
}
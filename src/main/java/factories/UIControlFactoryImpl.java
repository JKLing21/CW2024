package factories;

import factories.interfaces.UIControlFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;

public class UIControlFactoryImpl implements UIControlFactory {

    @Override
    public Label createLabel(String text, String... styleClasses) {
        Label label = new Label(text);
        label.getStyleClass().addAll(styleClasses);
        return label;
    }

    @Override
    public Button createButton(String text, EventHandler<ActionEvent> action) {
        Button button = new Button(text);
        button.setOnAction(action);
        return button;
    }

    @Override
    public Slider createSlider(double min, double max, double value, double width) {
        Slider slider = new Slider(min, max, value);
        slider.setPrefWidth(width);
        return slider;
    }

    @Override
    public ToggleButton createToggleButton(boolean selected) {
        ToggleButton toggleButton = new ToggleButton();
        toggleButton.setSelected(selected);
        return toggleButton;
    }

    @Override
    public Button createMenuButton(String text, EventHandler<ActionEvent> action) {
        Button button = new Button(text);
        button.setOnAction(action);
        button.getStyleClass().add("menu-button");
        return button;
    }
}
module com.example.demo {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires java.prefs;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    exports com.example.demo.controller;
    exports factories;
    exports factories.interfaces;
    exports com.example.demo.Strategy;
    
}
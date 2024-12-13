module com.example.demo {
	requires transitive javafx.controls;
	requires javafx.fxml;
	requires transitive javafx.graphics;
	requires java.prefs;
	requires transitive javafx.media;

	opens com.example.demo to javafx.fxml;

	exports com.example.demo;
	exports com.example.demo.Assets;
	exports com.example.demo.Managers;
	exports com.example.demo.Levels;
	exports com.example.demo.Screens;
	exports com.example.demo.Actors;
	exports com.example.demo.Actors.Planes;
	exports com.example.demo.Actors.Projectiles;
	exports com.example.demo.Actors.Components;
	exports com.example.demo.Strategy;
	exports com.example.demo.Factories;
	exports com.example.demo.Factories.Interfaces;

}
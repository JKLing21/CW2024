package com.example.demo.Managers;

import com.example.demo.Controller;
import com.example.demo.Factories.Interfaces.ComponentsFactory;
import com.example.demo.Levels.LevelParent;
import com.example.demo.Screens.PauseScreen;

import javafx.scene.Group;
import javafx.scene.Scene;
/**
* PauseManager class manages pause state of the game.
* Handles toggling of pause state, pausing and resuming game timeline,
* and also displaying and hiding the pause screen.
*/
@SuppressWarnings("unused")
public class PauseManager {

    private final Group root;
    private final Scene scene;
    private final LevelParent levelParent;
    private final Controller controller;
    private final AudioManager audioManager;
    private final ComponentsFactory componentsFactory;
    private final PauseScreen pauseScreen;
    public boolean isPaused = false;
    /**
     * Constructs PauseManager object with specified root node, scene, LevelParent, and Controller.
     *
     * @param root: Root node of game scene.
     * @param scene: Game scene.
     * @param levelParent: Manages the game state.
     * @param controller: Controller for handling game logic.
     */
    public PauseManager(Group root, Scene scene, LevelParent levelParent, Controller controller, AudioManager audioManager) {
        this.root = root;
        this.scene = scene;
        this.levelParent = levelParent;
        this.controller = controller;
        this.audioManager = audioManager;
        this.componentsFactory = levelParent.getComponentsFactory();
        this.pauseScreen = new PauseScreen(root, scene, levelParent, controller);
        this.isPaused = false;
    }
    /**
     * Checks if the game is paused.
     *
     * @return true: if game is paused, false otherwise.
     */
    public boolean isPaused() {
        return isPaused;
    }
    /**
     * Toggles pause state of the game.
     * If game is paused, it pauses the game timeline then shows the pause menu. 
     * If the game is not paused, it resumes the game timeline and hides the pause menu.
     */
    public void togglePause() {
        isPaused = !isPaused;
        if (isPaused) {
            levelParent.getTimeline().pause();
            audioManager.pauseBackgroundMusic();
            pauseScreen.showPauseMenu();
        } else {
            levelParent.getTimeline().play();
            audioManager.resumeBackgroundMusic();
            pauseScreen.hidePauseMenu();
        }
        root.requestFocus();
    }
}

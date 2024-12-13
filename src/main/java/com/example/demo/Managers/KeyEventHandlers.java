package com.example.demo.Managers;

import com.example.demo.Actors.Planes.UserPlane;
import com.example.demo.Levels.LevelParent;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
/**
 * KeyEventHandlers class handles the keyboard events, which includes movements, firing, and pausing.
 * It manages interaction between the player's inputs and game logic
 * through UserPlane and LevelParent classes.
 */
public class KeyEventHandlers {
    private final UserPlane user;
    private final LevelParent levelParent;
    private final KeyBindings keyBindings = new KeyBindings();
    private boolean isSpaceBarPressed = false;
    private boolean isSpaceBarHeld = false;
    /**
     * Constructs KeyEventHandlers object with specified UserPlane and LevelParent.
     *
     * @param user: player's plane controlled using keyboard inputs
     * @param levelParent: manages the overall game state
     */
    public KeyEventHandlers(UserPlane user, LevelParent levelParent) {
        this.user = user;
        this.levelParent = levelParent;
    }
    /**
     * Attaches key press and release handlers to game scene.
     *
     * @param scene: game scene which the key event handlers will be attached
     */
    public void attachHandlers(Scene scene) {
        scene.setOnKeyPressed(e -> handleKeyPressed(e.getCode()));
        scene.setOnKeyReleased(e -> handleKeyReleased(e.getCode()));
    }
    /**
     * Handles key press events by performing appropriate action based on key pressed.
     *
     * @param keyCode: key code of the pressed key
     */
    protected void handleKeyPressed(KeyCode keyCode) {
        KeyAction action = keyBindings.getAction(keyCode);
        if (action == null) return;

        switch (action) {
            case MOVE_UP -> user.moveUp();
            case MOVE_DOWN -> user.moveDown();
            case FIRE -> {
				if (!isSpaceBarHeld) {
					isSpaceBarPressed = true;
                }
				isSpaceBarHeld = true;
            }
            case PAUSE -> levelParent.togglePause();
            default -> {}
        }
    }
    /**
     * Handles key release events by stopping movement or resetting firing state.
     *
     * @param keyCode the key code of the released key
     */
    protected void handleKeyReleased(KeyCode keyCode) {
        KeyAction action = keyBindings.getAction(keyCode);
        if (action == null) return;

        switch (action) {
            case MOVE_UP, MOVE_DOWN -> user.stop();
            case FIRE -> isSpaceBarHeld = false;
            default -> {}
        }
    }
    /**
     * Key binding for pause action
     *
     * @param scene: Game scene to which the pause key binding will be added.
     * @param togglePauseAction: Action to be executed when pause key is pressed.
     */
    public void addPauseKeyBinding(Scene scene, Runnable togglePauseAction) {
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.P) {
                togglePauseAction.run();
            }
        });
    }
    /**
     * Checks if space bar is being held down.
     *
     * @return true: if space bar is held down, false otherwise
     */
    public boolean isSpaceBarHeld() {
        return isSpaceBarHeld;
    }
    /**
     * Checks if space bar was pressed.
     *
     * @return true: if space bar was pressed, false otherwise
     */
    public boolean isSpaceBarPressed() {
        return isSpaceBarPressed;
    }
    /**
     * Sets space bar pressed state.
     *
     * @param spaceBarPressed: set state of space bar press
     */
    public void setSpaceBarPressed(boolean spaceBarPressed) {
        isSpaceBarPressed = spaceBarPressed;
    }
}

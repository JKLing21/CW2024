package com.example.demo;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyEventHandlers {

    private final UserPlane user;
    private final LevelParent levelParent;
    private boolean isSpaceBarPressed = false;
    private boolean isSpaceBarHeld = false;

    public KeyEventHandlers(UserPlane user, LevelParent levelParent) {
        this.user = user;
        this.levelParent = levelParent;
    }

    public void attachHandlers(Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                KeyCode kc = e.getCode();
                if (kc == KeyCode.UP) user.moveUp();
                if (kc == KeyCode.DOWN) user.moveDown();
                if (kc == KeyCode.SPACE) {
                    if (!isSpaceBarHeld) {
                        isSpaceBarPressed = true;
                    }
                    isSpaceBarHeld = true;
                }
                if (kc == KeyCode.P) {
                    levelParent.togglePause();
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                KeyCode kc = e.getCode();
                if (kc == KeyCode.UP || kc == KeyCode.DOWN) user.stop();
                if (kc == KeyCode.SPACE) {
                    isSpaceBarHeld = false;
                }
            }
        });
    }

    public boolean isSpaceBarHeld() {
        return isSpaceBarHeld;
    }

    public boolean isSpaceBarPressed() {
        return isSpaceBarPressed;
    }

    public void setSpaceBarPressed(boolean spaceBarPressed) {
        isSpaceBarPressed = spaceBarPressed;
    }
}
package com.example.demo;

import javafx.scene.input.KeyCode;
import java.util.EnumMap;
import java.util.Map;
/**
 * Manages mapping between key codes and the corresponding player's plane actions.
 * It provides an abstraction layer for handling key bindings
 * allowing it to be customisable or extended key mappings in the future.
 */
public class KeyBindings {
    private final Map<KeyCode, KeyAction> keyActionMap = new EnumMap<>(KeyCode.class);
    /**
     * Constructs KeyBindings object with default key to action mappings.
     */
    public KeyBindings() {
        keyActionMap.put(KeyCode.UP, KeyAction.MOVE_UP);
        keyActionMap.put(KeyCode.DOWN, KeyAction.MOVE_DOWN);
        keyActionMap.put(KeyCode.SPACE, KeyAction.FIRE);
        keyActionMap.put(KeyCode.P, KeyAction.PAUSE);
    }
    /**
     * Retrieves action associated with specified key code.
     *
     * @param keyCode: key code for which the action is to be retrieved
     * @return corresponding {@link KeyAction} or null if no action is mapped
     */
    public KeyAction getAction(KeyCode keyCode) {
        return keyActionMap.get(keyCode);
    }
}

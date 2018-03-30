package engine;

import java.util.HashMap;

import javafx.scene.input.KeyCode;

/**
 * Keyboard input key map.
 */
public final class KeyboardInputs {
  
  public static final HashMap<KeyCode, Boolean> KEYMAP = fill();
  
  private static HashMap<KeyCode, Boolean> fill() {
    HashMap<KeyCode, Boolean> map = new HashMap<KeyCode, Boolean>();
    for (KeyCode val : KeyCode.values()) {
      map.put(val, false);
    }
    return map;
  }

}

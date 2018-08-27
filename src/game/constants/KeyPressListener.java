package game.constants;

import java.util.HashMap;
import java.util.Map;

import engine.KeyboardInputs;
import game.Game;
import game.structures.GameOperation;
import javafx.scene.input.KeyCode;

// Register callbacks for certain keys. Runs callback on keydown and does not fire again until keyup and next keydown.
public class KeyPressListener {
  
  private static Game instance;
  private static final Map<KeyCode, KeyPressCallback> keyReleaseMap = new HashMap<KeyCode, KeyPressCallback>();
  
  public static void init(Game instance) {
    KeyPressListener.instance = instance;
  }
  
  // Register a callback for a key.
  public static void register(KeyCode k, GameOperation run) {
    keyReleaseMap.put(k, new KeyPressCallback(run));
  }
  
  // Run on every frame to process input and run appropriate callbacks.
  public static void handle() {
    for (KeyCode k : keyReleaseMap.keySet()) {
      KeyPressCallback current = keyReleaseMap.get(k);
      if (current.needsRelease && !KeyboardInputs.KEYMAP.get(k)) {
        current.needsRelease = false;
      }
      else if (!current.needsRelease && KeyboardInputs.KEYMAP.get(k)) {
        current.needsRelease = true;
        current.run.run(instance);
      }
    }
  }

}

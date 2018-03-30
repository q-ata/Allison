package game;

import engine.KeyboardInputs;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyboardInputReleasedHandler implements EventHandler<KeyEvent> {
  
  @Override
  public void handle(KeyEvent ev) {
    KeyboardInputs.KEYMAP.replace(ev.getCode(), false);
  }
  
}

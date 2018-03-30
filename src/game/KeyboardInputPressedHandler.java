package game;

import engine.KeyboardInputs;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyboardInputPressedHandler implements EventHandler<KeyEvent> {

  @Override
  public void handle(KeyEvent ev) {
    if (KeyboardInputs.KEYMAP.get(ev.getCode())) {
      return;
    }
    KeyboardInputs.KEYMAP.replace(ev.getCode(), true);
  }

}

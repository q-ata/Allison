package game.constants;

import javafx.scene.input.KeyCode;

public class Keybind {
  
  public final KeyCode key;
  public final Direction dir;
  public int last = 0;
  public boolean active = false;
  
  public Keybind(KeyCode key, Direction dir) {
    this.key = key;
    this.dir = dir;
  }

}

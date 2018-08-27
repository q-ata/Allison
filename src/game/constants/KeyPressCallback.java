package game.constants;

import game.structures.GameOperation;

public class KeyPressCallback {
  
  public boolean needsRelease = false;
  public GameOperation run;
  
  public KeyPressCallback(GameOperation run) {
    this.run = run;
  }

}

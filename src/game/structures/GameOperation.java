package game.structures;

import game.Game;

// Modified Runnable to take Game instance as argument.
public interface GameOperation {
  
  public void run(Game instance);

}

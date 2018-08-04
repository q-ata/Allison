package game.structures;

import game.Game;

public interface AI {
  
  public default void ai(final Game INSTANCE) {
    return;
  }

}

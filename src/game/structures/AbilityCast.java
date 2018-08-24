package game.structures;

import game.Game;

public interface AbilityCast {
  
  public default void precast(Game instance) {
    return;
  }
  
  public default void cast(Game instance) {
    return;
  }
  
  public default void aftercast(Game instance) {
    return;
  }

}

package game.blocks;

import engine.Vec2;
import game.Game;
import game.structures.Entity;
import game.structures.MapItem;

public abstract class DefenseMatrix extends MapItem {
  
  public DefenseMatrix() {
    super(new Vec2());
  }

  @Override
  public boolean collisionProperties(final Game INSTANCE, MapItem collision) {
    // TODO: Decide on what I actually want to block.
    return collision instanceof Entity;
  }

}

package game.blocks;

import engine.Vec2;
import game.Game;
import game.structures.MapItem;

public abstract class RoomTransitioner extends MapItem {
  
  private static boolean open = false;
  private Vec2 transitionTo;

  public RoomTransitioner(Vec2 pos, Vec2 transitionTo) {
    super(pos);
    this.transitionTo = transitionTo;
  }
  
  @Override
  public boolean collisionProperties(final Game INSTANCE, MapItem collision) {
    if (!isOpen()) {
      return true;
    }
    INSTANCE.getRun().setCurrentRoom(new Vec2((int) transitionTo.x(), (int) transitionTo.y()));
    return true;
  }

  public static boolean isOpen() {
    return open;
  }

  public static void setOpen(boolean open) {
    RoomTransitioner.open = open;
  }

}

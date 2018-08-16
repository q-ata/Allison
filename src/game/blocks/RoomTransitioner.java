package game.blocks;

import java.util.HashMap;

import engine.Vec2;
import game.AnimationSequence;
import game.Game;
import game.Room;
import game.constants.Direction;
import game.structures.MapItem;
import javafx.scene.image.Image;

public abstract class RoomTransitioner extends MapItem {
  
  private boolean open = false;
  private Vec2 transitionTo;
  private static final String prefix = "file:resources/blocks/room_transitioner/";
  static final AnimationSequence lropen = new AnimationSequence(new Image[] {new Image(prefix + "portal_leftright.png")});
  static AnimationSequence udopen = new AnimationSequence(new Image[] {new Image(prefix + "portal_updown.png")});;
  private boolean updown;

  public RoomTransitioner(Vec2 pos, Vec2 transitionTo, boolean updown) {
    super(pos);
    this.transitionTo = transitionTo;
    this.updown = updown;
  }
  
  @Override
  public boolean collisionProperties(final Game INSTANCE, MapItem collision) {
    if (!isOpen()) {
      return true;
    }
    INSTANCE.getRun().setCurrentRoom(new Vec2((int) transitionTo.x(), (int) transitionTo.y()));
    return true;
  }

  public boolean isOpen() {
    return open;
  }

  public void setOpen(boolean open) {
    this.open = open;
    if (open) {
      HashMap<Direction, AnimationSequence> map = new HashMap<Direction, AnimationSequence>();
      map.put(Direction.UP, updown ? udopen : lropen);
      setSpriteSet(map);
    }
  }
  
  public static void setAllOpen(boolean open, Room room) {
    for (RoomTransitioner rt : room.getRts()) {
      rt.setOpen(open);
    }
  }

}

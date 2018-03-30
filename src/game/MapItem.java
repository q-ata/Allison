package game;

import java.util.ArrayList;
import java.util.HashMap;

import engine.Sprite;
import engine.Vec2;

public abstract class MapItem {
  
  private HashMap<Direction, AnimationSequence> spriteSet = new HashMap<Direction, AnimationSequence>();
  private Vec2 pos = new Vec2();
  private Direction dir;
  private boolean needMoving = true;
  private boolean moving = false;
  private boolean solid = true;
  private Vec2 vel = new Vec2();
  private ArrayList<CollisionData> touched = new ArrayList<CollisionData>();
  
  public MapItem(MapItemData data) {
    for (AnimationSequence direction : data.sequences()) {
      getSpriteSet().put(direction.dir(), direction);
    }
    move(data.pos());
    setDir(Direction.UP);
  }
  
  public abstract void collisionProperties(final Game INSTANCE);
  
  public Sprite getCurrentSprite() {
    return getSpriteSet().get(dir()).getSprite();
  }
  
  public void move(Vec2 dir) {
    for (AnimationSequence an : getSpriteSet().values()) {
      an.move(dir);
    }
    pos().add(dir);
  }
  
  public void moveTo(Vec2 dest) {
    setPos(dest.clone());
    for (AnimationSequence an : getSpriteSet().values()) {
      an.move(dest.clone().sub(pos()));
    }
  }
  
  public CollisionData touchedIncludes(CollisionData i) {
    for (CollisionData c : getTouched()) {
      if (i.equals(c)) {
        return c;
      }
    }
    return null;
  }

  public HashMap<Direction, AnimationSequence> getSpriteSet() {
    return spriteSet;
  }

  public void setSpriteSet(HashMap<Direction, AnimationSequence> spriteSet) {
    this.spriteSet = spriteSet;
  }
  
  public Vec2 pos() {
    return pos;
  }

  public void setPos(Vec2 pos) {
    this.pos = pos;
  }

  public Direction dir() {
    return dir;
  }

  public void setDir(Direction dir) {
    this.dir = dir;
  }

  public boolean isNeedMoving() {
    return needMoving;
  }

  public void setNeedMoving(boolean needMoving) {
    this.needMoving = needMoving;
  }

  public boolean isMoving() {
    return moving;
  }

  public void setMoving(boolean moving) {
    this.moving = moving;
  }

  public boolean isSolid() {
    return solid;
  }

  public void setSolid(boolean solid) {
    this.solid = solid;
  }

  public Vec2 vel() {
    return vel;
  }

  public void setVel(Vec2 vel) {
    this.vel = vel;
  }

  public ArrayList<CollisionData> getTouched() {
    return touched;
  }

  public void setTouched(ArrayList<CollisionData> touched) {
    this.touched = touched;
  }

}

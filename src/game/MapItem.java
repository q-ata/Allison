package game;

import java.util.HashMap;

import engine.Vec2;
import javafx.scene.image.Image;

public abstract class MapItem {
  
  // Direction : AnimationSequence hashmap for each direction that should be animated.
  private HashMap<Direction, AnimationSequence> spriteSet = new HashMap<Direction, AnimationSequence>();
  private Vec2 pos = new Vec2();
  private Direction dir;
  // Whether the MapItem must be moving in order to be animated.
  private boolean needMoving = true;
  // Whether the item is moving.
  private boolean moving = false;
  // Whether the item should stop the movement of other items and be stopped by them.
  private boolean solid = true;
  // Velocity vector.
  private Vec2 vel = new Vec2();
  private Hitbox hitbox;
  
  public MapItem(MapItemData data) {
    for (AnimationSequence direction : data.sequences()) {
      getSpriteSet().put(direction.dir(), direction);
    }
    setDir(Direction.UP);
    setHitbox(data.box());
    move(data.pos());
  }
  
  // Special collision properties of the item.
  public abstract void collisionProperties(final Game INSTANCE);
  
  public void move(Vec2 direction) {
    pos().add(direction);
    getHitbox().move(direction);
  }
  
  public Image getCurrentSprite() {
    return getSpriteSet().get(dir()).getSprite();
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

  public Hitbox getHitbox() {
    return hitbox;
  }

  public void setHitbox(Hitbox hitbox) {
    this.hitbox = hitbox;
  }

}

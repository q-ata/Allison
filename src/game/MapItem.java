package game;

import java.util.HashMap;
import java.util.Map;

import engine.Circle;
import engine.Convex;
import engine.Shape;
import engine.Vec2;
import javafx.scene.image.Image;

public abstract class MapItem implements Collider {
  
  private static Map<String, MapItemData> dataMap = populateDataMap();
  
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
  
  public MapItem(Vec2 pos) {
    init(dataMap.get(getClass().getSimpleName()), pos);
  }
  
  public MapItem(MapItemData data, Vec2 pos) {
    init(data, pos);
  }
  
  public MapItem(String name, Vec2 pos) {
    init(dataMap.computeIfAbsent(name, (c) -> DataLoader.loadMapItem(c)), pos);
  }
  
  public void init(MapItemData data, Vec2 pos) {
    for (AnimationSequence direction : data.sequences()) {
      getSpriteSet().put(direction.dir(), direction);
    }
    setDir(Direction.UP);
    Convex[] shapeModel = data.box().getShapes();
    Convex[] shapes = new Convex[shapeModel.length];
    for (int s = 0; s < shapes.length; s++) {
      Convex box = shapeModel[s];
      if (box instanceof Circle) {
        Circle model = (Circle) box;
        shapes[s] = new Circle(model.getCenter().clone(), model.getRadius());
        continue;
      }
      Shape shape = (Shape) box;
      Vec2[] points = new Vec2[shape.getPoints().length];
      for (int p = 0; p < points.length; p++) {
        Vec2 point = shape.getPoints()[p].clone();
        points[p] = point;
      }
      shapes[s] = new Shape(points);
    }
    setHitbox(new Hitbox(shapes));
    move(pos);
  }
  
  public void move(Vec2 direction) {
    pos().add(direction);
    getHitbox().move(direction);
  }
  
  private static Map<String, MapItemData> populateDataMap() {
    try {
      Map<String, MapItemData> map = new HashMap<>();
      DataFileReader reader = new DataFileReader("mappings");
      int mapCount = Integer.parseInt(reader.readLine());
      for (int i = 0; i < mapCount; i++) {
        map.put(reader.readLine(), DataLoader.loadMapItem(reader.readLine()));
      }
      reader.close();
      return map;
    }
    catch(Exception e) {
      e.printStackTrace();
      return null;
    }
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

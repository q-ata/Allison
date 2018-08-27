package game.structures;

import java.util.HashMap;
import java.util.Map;

import engine.Circle;
import engine.Convex;
import engine.Shape;
import engine.Vec2;
import game.AnimationSequence;
import game.DataFileReader;
import game.DataLoader;
import game.Hitbox;
import game.MapItemData;
import game.constants.Direction;
import javafx.scene.image.Image;

public abstract class MapItem implements Collider {
  
  private static final Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
  private static final Map<String, MapItemData> dataMap = populateDataMap();
  
  // Direction : AnimationSequence hashmap for each direction that should be animated.
  private Map<Direction, AnimationSequence> spriteSet = new HashMap<Direction, AnimationSequence>();
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
      Map<String, MapItemData> map = new HashMap<String, MapItemData>();
      DataFileReader reader = new DataFileReader("mappings");
      int mapCount = Integer.parseInt(reader.readLine());
      for (int i = 0; i < mapCount; i++) {
        String simpleName = reader.readLine();
        String qualName = reader.readLine();
        String path = reader.readLine();
        map.put(simpleName, DataLoader.loadMapItem(path));
        classMap.put(simpleName, Class.forName(qualName));
      }
      reader.close();
      return map;
    }
    catch(Exception e) {
      e.printStackTrace();
      return null;
    }
  }
  
  public static Map<String, Class<?>> classMap() {
    return classMap;
  }
  
  public Image getCurrentSprite() {
    return getSpriteSet().get(dir()).getSprite();
  }
  
  public static Vec2 calculateVelocity(Direction dir, double v) {
    Vec2 vel;
    switch (dir) {
    case UP:
      vel = new Vec2(0, -v);
      break;
    case DOWN:
      vel = new Vec2(0, v);
      break;
    case LEFT:
      vel = new Vec2(-v, 0);
      break;
    case RIGHT:
      vel = new Vec2(v, 0);
      break;
    default:
      vel = new Vec2();
    }
    return vel;
  }

  public Map<Direction, AnimationSequence> getSpriteSet() {
    return spriteSet;
  }

  public void setSpriteSet(Map<Direction, AnimationSequence> spriteSet) {
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

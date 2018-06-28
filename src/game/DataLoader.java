package game;

import java.util.HashMap;

import engine.Vec2;
import javafx.scene.image.Image;

// Reads and loads MapItemData from a local data file.
public final class DataLoader {
  
  // String : Direction hashmap for converting string to Direction enum.
  private static HashMap<String, Direction> directionMap = mapDirections();
  
  private static HashMap<String, Direction> mapDirections() {
    HashMap<String, Direction> map = new HashMap<String, Direction>();
    map.put("UP", Direction.UP);
    map.put("DOWN", Direction.DOWN);
    map.put("LEFT", Direction.LEFT);
    map.put("RIGHT", Direction.RIGHT);
    return map;
  }
  
  private static DataFileReader reader;
  
  public static AnimationSequence[] loadSequences(String path) {
    reader = new DataFileReader(path);
    // Number of animated directions.
    final int DIRECTIONS = Integer.parseInt(reader.readLine());
    AnimationSequence[] sequences = new AnimationSequence[DIRECTIONS];
    
    for (int d = 0; d < DIRECTIONS; d++) {
      Direction dir = directionMap.get(reader.readLine());
      // The number of sprites in this sequence. One for no animation.
      final int COUNT = Integer.parseInt(reader.readLine());
      int frameDuration = COUNT == 1 ? 0 : Integer.parseInt(reader.readLine());
      Image[] sprites = new Image[COUNT];
      
      for (int s = 0; s < COUNT; s++) {
        sprites[s] = new Image("file:resources/" + reader.readLine() + ".png");
      }
      
      sequences[d] = sprites.length == 1 ? new AnimationSequence(sprites) : new AnimationSequence(sprites, frameDuration, dir);
    }
    return sequences;
  }
  
  // Get MapItemData from data file.
  public static MapItemData loadMapItem(String path) {
    AnimationSequence[] sequences = loadSequences(path);
    String boxPath = reader.readLine();
    reader.close();
    return new MapItemData(sequences, new Hitbox(boxPath));
  }
  
  public static Vec2 loadHudWeaponSpriteOffset(String path) {
    reader = new DataFileReader("weapons/" + path);
    Vec2 offset = new Vec2(Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()));
    reader.close();
    return offset;
  }
  
  // Reading this method will lose you brain cells.
  public static ProjectileData loadProjectileData(String path) {
    
    reader = new DataFileReader(path);
    int rateBoost = Integer.parseInt(reader.readLine());
    double rateMulti = Double.parseDouble(reader.readLine());
    double speedBoost = Double.parseDouble(reader.readLine());
    double speedMulti = Double.parseDouble(reader.readLine());
    int damageBoost = Integer.parseInt(reader.readLine());
    double damageMulti = Double.parseDouble(reader.readLine());
    double rangeBoost = Double.parseDouble(reader.readLine());
    double rangeMulti = Double.parseDouble(reader.readLine());
    int scaleBoost = Integer.parseInt(reader.readLine());
    double scaleMulti = Double.parseDouble(reader.readLine());
    
    Hitbox upBox = new Hitbox(reader.readLine());
    int upCount = Integer.parseInt(reader.readLine());
    Image[] upSprites = new Image[upCount];
    for (int i = 0; i < upCount; i++) {
      upSprites[i] = new Image("file:resources/" + reader.readLine() + ".png");
    }
    Hitbox downBox = new Hitbox(reader.readLine());
    int downCount = Integer.parseInt(reader.readLine());
    Image[] downSprites = new Image[downCount];
    for (int i = 0; i < downCount; i++) {
      downSprites[i] = new Image("file:resources/" + reader.readLine() + ".png");
    }
    Hitbox leftBox = new Hitbox(reader.readLine());
    int leftCount = Integer.parseInt(reader.readLine());
    Image[] leftSprites = new Image[leftCount];
    for (int i = 0; i < leftCount; i++) {
      leftSprites[i] = new Image("file:resources/" + reader.readLine() + ".png");
    }
    Hitbox rightBox = new Hitbox(reader.readLine());
    int rightCount = Integer.parseInt(reader.readLine());
    Image[] rightSprites = new Image[rightCount];
    for (int i = 0; i < rightCount; i++) {
      rightSprites[i] = new Image("file:resources/" + reader.readLine() + ".png");
    }
    
    MapItemData upData = new MapItemData(new AnimationSequence(upSprites, Direction.UP), upBox);
    MapItemData downData = new MapItemData(new AnimationSequence(downSprites, Direction.DOWN), downBox);
    MapItemData leftData = new MapItemData(new AnimationSequence(leftSprites, Direction.LEFT), leftBox);
    MapItemData rightData = new MapItemData(new AnimationSequence(rightSprites, Direction.RIGHT), rightBox);
    
    return new ProjectileData(rateBoost, rateMulti, speedBoost, speedMulti, damageBoost, damageMulti,
        rangeBoost, rangeMulti, scaleBoost, scaleMulti, new ProjectileSprites(upData, downData, leftData, rightData));
    
  }

}

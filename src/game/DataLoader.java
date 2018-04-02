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
  public static MapItemData loadMapItem(String path, Vec2 pos) {
    AnimationSequence[] sequences = loadSequences(path);
    String boxPath = reader.readLine();
    reader.close();
    return new MapItemData(sequences, pos, new Hitbox(boxPath));
  }
  
  public static Vec2 loadHudWeaponSpriteOffset(String path) {
    reader = new DataFileReader("weapons/" + path);
    Vec2 offset = new Vec2(Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()));
    reader.close();
    return offset;
  }

}

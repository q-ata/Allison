package game;

import java.util.HashMap;

import engine.Vec2;
import javafx.scene.image.Image;

public final class MapItemLoader {
  
  private static HashMap<String, Direction> directionMap = mapDirections();
  
  private static HashMap<String, Direction> mapDirections() {
    HashMap<String, Direction> map = new HashMap<String, Direction>();
    map.put("UP", Direction.UP);
    map.put("DOWN", Direction.DOWN);
    map.put("LEFT", Direction.LEFT);
    map.put("RIGHT", Direction.RIGHT);
    return map;
  }
  
  public static MapItemData load(String path, Vec2 pos) {
      /*
4
UP
3
15
characters/main/back_0
characters/main/back_1
characters/main/back_2
DOWN
3
15
characters/main/forward_0
characters/main/forward_1
characters/main/forward_2
LEFT
3
15
characters/main/left_0
characters/main/left_1
characters/main/left_2
RIGHT
3
15
characters/main/right_0
characters/main/right_1
characters/main/right_2
       */
    DataFileReader reader = new DataFileReader(path);
    final int DIRECTIONS = Integer.parseInt(reader.readLine());
    AnimationSequence[] sequences = new AnimationSequence[DIRECTIONS];
    
    for (int d = 0; d < DIRECTIONS; d++) {
      // Image[] sprites, int frameDuration, Direction dir
      Direction dir = directionMap.get(reader.readLine());
      final int COUNT = Integer.parseInt(reader.readLine());
      int frameDuration = COUNT == 1 ? 0 : Integer.parseInt(reader.readLine());
      Image[] sprites = new Image[COUNT];
      
      for (int s = 0; s < COUNT; s++) {
        sprites[s] = new Image("file:resources/" + reader.readLine() + ".png");
      }
      
      sequences[d] = sprites.length == 1 ? new AnimationSequence(sprites) : new AnimationSequence(sprites, frameDuration, dir);
    }
    String boxPath = reader.readLine();
    reader.close();
    return new MapItemData(sequences, pos, new Hitbox(boxPath));
  }

}

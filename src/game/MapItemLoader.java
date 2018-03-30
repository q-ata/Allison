package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

import engine.Sprite;
import engine.Vec2;

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
    try {
      String currentDir = new File("").getAbsolutePath() + "/resources/";
      BufferedReader reader = new BufferedReader(new FileReader(currentDir + path + ".dat"));
      
      int directionCount = Integer.parseInt(reader.readLine());
      AnimationSequence[] sequences = new AnimationSequence[directionCount];
      for (int d = 0; d < directionCount; d++) {
        Direction dir = directionMap.get(reader.readLine());
        int frames = Integer.parseInt(reader.readLine());
        Sprite[] sprites = new Sprite[frames];
        int frameTime = frames == 1 ? 0 : Integer.parseInt(reader.readLine());
        for (int s = 0; s < frames; s++) {
          String imagePath = reader.readLine();
          String hitboxPath = reader.readLine();
          sprites[s] = new Sprite(imagePath, hitboxPath);
        }
        sequences[d] = frames == 1 ? new AnimationSequence(sprites) : new AnimationSequence(sprites, frameTime, dir);
      }
      
      reader.close();
      return new MapItemData(sequences, pos);
    }
    catch(Exception e) {
      e.printStackTrace();
      return null;
    }
  }

}

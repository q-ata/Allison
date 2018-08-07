package game.structures;

import java.lang.reflect.InvocationTargetException;

import engine.Vec2;
import game.MapItemData;

public class MapItemDefinition {
  
  private String name;
  private MapItemData data;
  
  public MapItemDefinition(String name, MapItemData data) {
    this.name = name;
    this.data = data;
  }
  
  public MapItem create(Vec2 pos) {
    Class<?> clazz;
    try {
      clazz = Class.forName(name);
      return (MapItem) clazz.getDeclaredConstructor(MapItemData.class, Vec2.class)
          .newInstance(data, pos);
    }
    catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException |
        InvocationTargetException | NoSuchMethodException | SecurityException e) {
      e.printStackTrace();
      return null;
    }
  }

}

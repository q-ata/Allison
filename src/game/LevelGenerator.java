package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import engine.Vec2;

public class LevelGenerator {
  
  public class Floor {
    Vec2 spawn;
    Room[][] rooms;
    public Floor(Vec2 spawn, Room[][] rooms) {
      this.spawn = spawn;
      this.rooms = rooms;
    }
  }
  
  // Valid characters for seed.
  private final char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
  private final long seed;
  private final Random generator;
  
  public LevelGenerator() {
    seed = generateSeed();
    generator = new Random(seed);
  }
  
  private long generateSeed() {
    char[] code = new char[9];
    for (int i = 0; i < 9; i++) {
      code[i] = chars[(int) Math.floor(Math.random() * chars.length)];
    }
    String seed = "";
    for (char c : code) {
      seed += (int) c;
    }
    return Long.parseLong(seed);
  }
  
  public Floor generateFloor(int count, PlayableCharacter player) {
    List<List<Room>> rooms = new ArrayList<List<Room>>(count * 2);
    for (int i = 0; i < count + 2; i++) {
      List<Room> col = new ArrayList<Room>(count * 2);
      for (int j = 0; j < count + 2; j++) {
        col.add(null);
      }
      rooms.add(col);
    }
    
    // TODO: Replace later.
    // new Room(new Vec2(count - 1, count - 1), 0, player);
    Room spawn = DataLoader.loadRoom(1, new Vec2(count - 1, count - 1), player);
    List<Room> pop = new ArrayList<Room>();
    pop.add(spawn);
    rooms.get((int) spawn.coord().x()).set((int) spawn.coord().y(), spawn);
    
    List<Vec2> valid = new ArrayList<Vec2>();
    valid.add(new Vec2(spawn.coord().x(), spawn.coord().y() - 1));
    valid.add(new Vec2(spawn.coord().x(), spawn.coord().y() + 1));
    valid.add(new Vec2(spawn.coord().x() - 1, spawn.coord().y()));
    valid.add(new Vec2(spawn.coord().x() + 1, spawn.coord().y()));
    
    for (int q = 0; q < count - 1; q++) {
      
      for (int i = 0; i < valid.size(); i++) {
        Vec2 coord = valid.get(i);
        if (tooMany(coord, rooms)) {
          valid.remove(i--);
        }
      }
     
      // TODO: Use random generator.
      int ind = (int) Math.floor(Math.random() * valid.size());
      Vec2 c = valid.get(ind);
      valid.remove(ind);
      // Replace 1 with random room ID.
      int id = 1;
      if (q == count - 2) {
        // Boss ID.
        id = -1;
      }
      else if (q == count - 3) {
        // Shop ID.
        id = -2;
      }
      else if (q == count - 4) {
        // Mystery ID.
        id = -3;
      }
      Room n = DataLoader.loadRoom(id, c, player);
      
      Vec2 up = new Vec2(c.x(), c.y() - 1);
      Vec2 down = new Vec2(c.x(), c.y() + 1);
      Vec2 left = new Vec2(c.x() - 1, c.y());
      Vec2 right = new Vec2(c.x() + 1, c.y());
      if (rooms.get((int) up.x()).get((int) up.y()) == null) {
        valid.add(up);
      }
      if (rooms.get((int) down.x()).get((int) down.y()) == null) {
        valid.add(down);
      }
      if (rooms.get((int) left.x()).get((int) left.y()) == null) {
        valid.add(left);
      }
      if (rooms.get((int) right.x()).get((int) right.y()) == null) {
        valid.add(right);
      }
      if (rooms.size() < c.x() + 3) {
        List<Room> list = new ArrayList<Room>();
        for (int i = 0; i < rooms.get(0).size(); i++) {
          list.add(null);
        }
        rooms.add(list);
      }
      if (rooms.get(0).size() < c.y() + 3) {
        for (int i = 0; i < rooms.size(); i++) {
          rooms.get(i).add(null);
        }
      }
      rooms.get((int) c.x()).set((int) c.y(), n);
      
    }
    
    int xShift = 0;
    boolean empty = true;
    for (int i = 0; i < rooms.size(); i++) {
      for (int j = 0; j < rooms.get(0).size(); j++) {
        if (rooms.get(i).get(j) != null) {
          empty = false;
          break;
        }
      }
      if (empty) {
        rooms.remove(i--);
        xShift++;
      }
      else {
        break;
      }
    }
    
    int yShift = 0;
    empty = true;
    for (int i = 0; i < rooms.get(0).size(); i++) {
      for (int j = 0; j < rooms.size(); j++) {
        if (rooms.get(j).get(i) != null) {
          empty = false;
          break;
        }
      }
      if (empty) {
        for (int j = 0; j < rooms.size(); j++) {
          rooms.get(j).remove(i);
        }
        i--;
        yShift++;
      }
      else {
        break;
      }
    }
    
    empty = true;
    for (int i = rooms.size() - 1; i > -1; i--) {
      for (int j = 0; j < rooms.get(0).size(); j++) {
        if (rooms.get(i).get(j) != null) {
          empty = false;
          break;
        }
      }
      if (empty) {
        rooms.remove(i);
      }
      else {
        break;
      }
    }
    
    empty = true;
    for (int i = rooms.get(0).size() - 1; i > -1; i--) {
      for (int j = 0; j < rooms.size(); j++) {
        if (rooms.get(j).get(i) != null) {
          empty = false;
          break;
        }
      }
      if (empty) {
        for (int j = 0; j < rooms.size(); j++) {
          rooms.get(j).remove(i);
        }
      }
      else {
        break;
      }
    }
    
    for (int i = 0; i < rooms.size(); i++) {
      for (int j = 0; j < rooms.get(0).size(); j++) {
        Room v = rooms.get(i).get(j);
        if (v != null) {
          v.coord().sub(new Vec2(xShift, yShift));
        }
      }
    }
    
    Room[][] layout = new Room[rooms.size()][rooms.get(0).size()];
    for (int i = 0; i < layout.length; i++) {
      layout[i] = rooms.get(i).toArray(new Room[rooms.get(i).size()]);
    }
    return new Floor(spawn.coord(), layout);
    
  }
  
  private static boolean tooMany(Vec2 c, List<List<Room>> rooms) {
    int a = 0;
    if (rooms.get((int) c.x()).get((int) (c.y() - 1)) != null) {
      a++;
    }
    if (rooms.get((int) c.x()).get((int) (c.y() + 1)) != null) {
      if (a > 0) {
        return true;
      }
      a++;
    }
    if (rooms.get((int) (c.x() - 1)).get((int) c.y()) != null) {
      if (a > 0) {
        return true;
      }
      a++;
    }
    if (rooms.get((int) (c.x() + 1)).get((int) c.y()) != null && a > 0) {
      return true;
    }
    return false;
  }

}

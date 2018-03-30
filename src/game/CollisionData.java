package game;

public class CollisionData {
  
  private final Direction dir;
  private final int id;
  
  public CollisionData(Direction dir, int id) {
    this.dir = dir;
    this.id = id;
  }
  
  public boolean equals(CollisionData c) {
    return id() == c.id();
  }

  public Direction dir() {
    return dir;
  }

  public int id() {
    return id;
  }

}

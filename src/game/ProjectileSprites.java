package game;

public class ProjectileSprites {
  
  private MapItemData up;
  private MapItemData down;
  private MapItemData left;
  private MapItemData right;
  
  // [up, down, left, right]
  public ProjectileSprites(MapItemData[] dirs) {
    this.up = dirs[0];
    this.down = dirs[1];
    this.left = dirs[2];
    this.right = dirs[3];
  }
  
  public ProjectileSprites(MapItemData up, MapItemData down, MapItemData left, MapItemData right) {
    this.up = up;
    this.down = down;
    this.left = left;
    this.right = right;
  }
  
  public MapItemData get(Direction dir) {
    switch (dir) {
    case UP:
      return up();
    case DOWN:
      return down();
    case LEFT:
      return left();
    case RIGHT:
      return right();
     default:
      return up();
    }
  }
  
  public MapItemData up() {
    return up;
  }
  
  public MapItemData down() {
    return down;
  }
  
  public MapItemData left() {
    return left;
  }
  
  public MapItemData right() {
    return right;
  }

}

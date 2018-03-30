package engine;

/**
 * Represents a convex 2D polygon.
 */
public interface Convex {
  
  /**
   * Returns the farthest point of this convex in the given direction vector.
   * @param direction The direction vector to search in.
   * @return The farthest point.
   */
  public abstract Vec2 getFarthestPoint(Vec2 direction);
  
  /**
   * Change the position of this convex in world space.
   * @param direction The direction vector to move in.
   */
  public abstract void changePosition(Vec2 direction);

}

package engine;

/**
 * Represents a circle.
 */
public class Circle implements Convex {
  
  private Vec2 center;
  private double radius;
  
  public Circle(Vec2 center, double radius) {
    setCenter(center);
    setRadius(radius);
  }

  /**
   * Get the center coordinates of this circle.
   * @return A 2 component vector containing this circle's center.
   */
  public Vec2 getCenter() {
    return center;
  }

  /**
   * Set the center of this circle.
   * @param center A 2 component vector of the new center.
   */
  public void setCenter(Vec2 center) {
    this.center = center;
  }

  /**
   * Returns the radius of this circle.
   * @return The radius.
   */
  public double getRadius() {
    return radius;
  }

  /**
   * Set the radius of this circle.
   * @param radius The new radius.
   */
  public void setRadius(double radius) {
    this.radius = radius;
  }
  
  @Override
  public Vec2 getFarthestPoint(Vec2 direction) {
    Vec2 copy = direction.getNormalized();
    Vec2 cent = center.clone();
    cent.setX(cent.x() + (getRadius() * copy.x()));
    cent.setY(cent.y() + (getRadius() * copy.y()));
    return cent;
  }
  
  @Override
  public void changePosition(Vec2 direction) {
    center.add(direction);
  }

}

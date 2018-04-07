package engine;

/**
 * A polygon that can have any number of points. Shapes should always be convex.
 */
public class Shape implements Convex {
  
  private int npoints = 0;
  private Vec2[] points = {};
  
  /**
   * Creates a new convex shape using the given points.
   * @param points The points to use when creating the shape.
   */
  public Shape(Vec2[] points) {
    setNpoints(points.length);
    setPoints(points);
  }

  /**
   * The number of vertices in this shape.
   * @return The number of vertices.
   */
  public int getNpoints() {
    return npoints;
  }

  private void setNpoints(int npoints) {
    this.npoints = npoints;
  }

  /**
   * An array of x coordinates for all the vertices of this shape.
   * @return The array of x coordinates.
   */

  /**
   * An array of 2 component vectors representing the vertices of this shape.
   * @return The array of 2 component vectors.
   */
  public Vec2[] getPoints() {
    return points;
  }

  private void setPoints(Vec2[] points) {
    this.points = points;
  }
  
  @Override
  public void flip(boolean vertical) {
    double mean = 0;
    double max = 0;
    int len = getPoints().length;
    for (int i = 0; i < len; i++) {
      double value = vertical ? getPoints()[i].y() : getPoints()[i].x();
      if (value > max) {
        max = value;
      }
      mean += value;
    }
    mean /= len;
    for (Vec2 point : getPoints()) {
      if (vertical) {
        point.setY((mean * 2) - point.y());
      }
      else {
        point.setX((mean * 2) - point.x());
      }
    }
  }

  @Override
  public Vec2 getFarthestPoint(Vec2 direction) {
    double max = -Double.MAX_VALUE;
    int index = -1;
    
    for (int i = 0; i != getNpoints(); i++) {
      double dot = direction.dot(getPoints()[i]);
      
      if (dot > max) {
        max = dot;
        index = i;
      }
    }
    
    return getPoints()[index].clone();
  }
  
  @Override
  public void changePosition(Vec2 direction) {
    for (Vec2 point : getPoints()) {
      point.add(direction);
    }
  }
  
  @Override
  public Vec2 getCenter() {
    Vec2 center = new Vec2();
    for (Vec2 point : getPoints()) {
      center.add(point);
    }
    center.set(center.x() / getPoints().length, center.y() / getPoints().length);
    return center;
  }
  
  @Override
  public Shape clone() {
    Vec2[] points = new Vec2[getPoints().length];
    for (int i = 0; i < points.length; i++) {
      points[i] = getPoints()[i].clone();
    }
    return new Shape(points);
  }

}

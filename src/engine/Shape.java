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
    for (int i = 0; i < getNpoints(); i++) {
      getPoints()[i].add(direction);
    }
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

package engine;

/**
 * A 2 component vector, with points represented by x and y.
 */
public final class Vec2 {
  
  private double x;
  private double y;
  
  /**
   * Creates a 2 component vector using the given points.
   * @param x The first point of this vector.
   * @param y The second point of this vector.
   */
  public Vec2(double x, double y) {
    setX(x);
    setY(y);
  }
  
  /**
   * Creates a 2 component vector with 0 as the points.
   */
  public Vec2() {
    setX(0.0);
    setY(0.0);
  }
  
  /**
   * Transforms the points of this vector to their negative counterparts.
   * @return This vector.
   */
  public Vec2 negate() {
    setX(-x());
    setY(-y());
    return this;
  }
  
  /**
   * Adds the points of the provided vector to this vector.
   * @param vec2 The vector to be added.
   * @return This vector.
   */
  public Vec2 add(Vec2 vec2) {
    setX(x() + vec2.x());
    setY(y() + vec2.y());
    return this;
  }
  
  /**
   * Adds the two coordinates of this vector.
   * @return The sum of this vector's x and y.
   */
  public double sum() {
    return x() + y();
  }
  
  /**
   * Subtracts the points of the provided vector from this vector.
   * @param vec2 The vector to be subtracted.
   * @return This vector.
   */
  public Vec2 sub(Vec2 vec2) {
    setX(x() - vec2.x());
    setY(y() - vec2.y());
    return this;
  }
  
  /**
   * Normalizes a copy of this vector and returns the copy.
   * @return A normalized copy of this vector.
   */
  public Vec2 getNormalized() {
    double magnitude = Math.sqrt((x() * x()) + (y() * y()));
    if (magnitude <= Epsilon.EPSILON) {
      return new Vec2();
    }
    magnitude = 1.0 / magnitude;
    return new Vec2(x() * magnitude, y() * magnitude);
  }
  
  /**
   * Normalizes this vector.
   * @return The magnitude of this vector before normalizing.
   */
  public double normalize() {
    double magnitude = Math.sqrt(x() * x() + y() * y());
    if (magnitude <= Epsilon.EPSILON) return 0;
    double m = 1.0 / magnitude;
    setX(x() * m);
    setY(y() * m);
    return magnitude;
  }
  
  /**
   * Sets the values of this vector to its right normals.
   * @return This vector.
   */
  public Vec2 right() {
    double temp = x();
    setX(-y());
    setY(temp);
    return this;
  }
  
  /**
   * Sets the values of this vector to its left normals.
   * @return This vector.
   */
  public Vec2 left() {
    double temp = x();
    setX(y());
    setY(-temp);
    return this;
  }
  
  /**
   * Returns the cross product of this vector and the given vector.
   * @param vector The vector to calculate with.
   * @return The cross product.
   */
  public double cross(Vec2 vector) {
    return x() * vector.y() - y() * vector.x();
  }
  
  /**
   * Returns the cross product of this vector and thegiven z value.
   * @param z the z component of this vector
   * @return The cross product of this vector and the provided z value.
   */
  public Vec2 cross(double z) {
    return new Vec2(-1.0 * y() * z, x() * z);
  }
  
  /**
   * Returns the dot product of this vector and the provided vector.
   * @param vec2 The vector to compute with this vector.
   * @return The dot product of this vector and the provided vector.
   */
  public double dot(Vec2 vec2) {
    return (x() * vec2.x()) + (y() * vec2.y());
  }

  /**
   * The first point of this vector.
   * @return The first point.
   */
  public double x() {
    return x;
  }

  /**
   * Set the first point of this vector.
   * @param x The new point.
   */
  public void setX(double x) {
    this.x = x;
  }

  /**
   * The second point of this vector.
   * @return The second point.
   */
  public double y() {
    return y;
  }

  /**
   * Set the second point of this vector.
   * @param y The new point.
   */
  public void setY(double y) {
    this.y = y;
  }

  /**
   * Set the points of this vector.
   * @param nx The new first point.
   * @param ny The new second point.
   */
  public void set(double nx, double ny) {
    setX(nx);
    setY(ny);
  }
  
  @Override
  public Vec2 clone() {
    return new Vec2(x(), y());
  }
  
  @Override
  public String toString() {
    return "(" + x() + ", " + y() + ")";
  }

}

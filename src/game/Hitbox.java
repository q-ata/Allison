package game;

import engine.Circle;
import engine.Convex;
import engine.Shape;
import engine.Vec2;

// A hitbox composed of one or more Convex polygons.
public class Hitbox {
  
  private Convex[] shapes;
  private double width;
  private double height;
  
  // Create a hitbox by parsing a data file.
  public Hitbox(String path) {
    DataFileReader reader = new DataFileReader(path);
    // The number of shapes in this hitbox.
    final int NUM = Integer.parseInt(reader.readLine());
    setShapes(new Convex[NUM]);
    
    for (int n = 0; n < NUM; n++) {
      // The number of sides of this polygon.
      final int SIDES = Integer.parseInt(reader.readLine());
      
      // If it is a circle.
      if (SIDES == 0) {
        Vec2 center = new Vec2(Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()));
        double radius = Double.parseDouble(reader.readLine());
        Circle shape = new Circle(center, radius);
        getShapes()[n] = shape;
        continue;
      }
      
      // If it is not a circle.
      final Vec2[] points = new Vec2[SIDES];
      for (int s = 0; s < SIDES; s++) {
        points[s] = new Vec2(Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()));
      }
      getShapes()[n] = new Shape(points);
    }
    
    reader.close();
    
    calculateDimensions();
  }
  
  public Hitbox(Convex[] shapes) {
    setShapes(shapes);
    calculateDimensions();
  }
  
  public void move(Vec2 direction) {
    for (Convex shape : getShapes()) {
      shape.changePosition(direction);
    }
  }
  
  public Vec2 getCenter() {
    Vec2 center = new Vec2();
    for (Convex s : getShapes()) {
      center.add(s.getCenter());
    }
    center.set(center.x() / getShapes().length, center.y() / getShapes().length);
    return center;
  }
  
  private void calculateDimensions() {
    double minX = Double.MAX_VALUE;
    double maxX = -Double.MAX_VALUE;
    double minY = Double.MAX_VALUE;
    double maxY = -Double.MAX_VALUE;
    
    for (Convex c : getShapes()) {
      minX = Math.min(minX, c.getFarthestPoint(new Vec2(-1, 0)).x());
      maxX = Math.max(maxX, c.getFarthestPoint(new Vec2(1, 0)).x());
      minY = Math.min(minY, c.getFarthestPoint(new Vec2(0, -1)).y());
      maxY = Math.max(maxY, c.getFarthestPoint(new Vec2(0, 1)).y());
    }
    
    width = maxX - minX;
    height = maxY - minY;
  }
  
  public double getWidth() {
    return width;
  }
  
  public double getHeight() {
    return height;
  }
  
  public Hitbox clone() {
    return new Hitbox(getShapes().clone());
  }

  public Convex[] getShapes() {
    return shapes;
  }

  public void setShapes(Convex[] shapes) {
    this.shapes = shapes;
  }

}

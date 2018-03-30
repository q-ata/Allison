package game;

import engine.Circle;
import engine.Convex;
import engine.Shape;
import engine.Vec2;

public class Hitbox {
  
  private Convex[] shapes;
  
  public Hitbox(String path) {
    // path/to/file.dat
    DataFileReader reader = new DataFileReader(path);
    final int NUM = Integer.parseInt(reader.readLine());
    setShapes(new Convex[NUM]);
    
    for (int n = 0; n < NUM; n++) {
      final int SIDES = Integer.parseInt(reader.readLine());
      
      if (SIDES == 0) {
        Vec2 center = new Vec2(Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()));
        double radius = Double.parseDouble(reader.readLine());
        Circle shape = new Circle(center, radius);
        getShapes()[n] = shape;
        continue;
      }
      
      final Vec2[] points = new Vec2[SIDES];
      for (int s = 0; s < SIDES; s++) {
        points[s] = new Vec2(Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()));
      }
      getShapes()[n] = new Shape(points);
    }
    
    reader.close();
  }
  
  public void move(Vec2 direction) {
    for (Convex shape : getShapes()) {
      shape.changePosition(direction);
    }
  }

  public Convex[] getShapes() {
    return shapes;
  }

  public void setShapes(Convex[] shapes) {
    this.shapes = shapes;
  }

}

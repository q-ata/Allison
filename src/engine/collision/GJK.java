package engine.collision;

import java.util.ArrayList;
import java.util.List;

import engine.Convex;
import engine.Vec2;

/**
 * Provides methods to check for collisions between 2 convex 2D polygons.
 */
public class GJK {
  
  /**
   * Check whether or not 2 convex polygons intersect.
   * @param a The first polygon to check.
   * @param b The second polygon to check.
   * @return Whether or not the polygons are intersecting.
   */
  public static boolean intersects(final Convex a, final Convex b) {
    final List<Vec2> simplex = new ArrayList<Vec2>();
    final Vec2 direction = new Vec2(1.0, 0.0);
    simplex.add(minkowski(a, b, direction));
    direction.negate();
    
    while (true) {
      Vec2 point = minkowski(a, b, direction);
      if (point.dot(direction) < 0) {
        return false;
      }
      
      simplex.add(point);
      
      if (evaluate(simplex, direction)) {
        return true;
      }
    }
  }
  
  private static Vec2 minkowski(final Convex a, final Convex b, final Vec2 direction) {
    final Vec2 result = a.getFarthestPoint(direction);
    result.sub(b.getFarthestPoint(direction.clone().negate()));
    return result;
  }
  
  private static boolean sameDirection(final Vec2 a, final Vec2 b) {
    return a.dot(b) > 0;
  }
  
  private static Vec2 createVector(final Vec2 a, final Vec2 b) {
    return new Vec2(b.x() - a.x(), b.y() - a.y());
  }
  
  private static boolean evaluate(List<Vec2> simplex, Vec2 direction) {
    if (simplex.size() == 2) {
      final Vec2 a = simplex.get(1);
      final Vec2 b = simplex.get(0);
      
      final Vec2 ao = createVector(a, new Vec2());
      final Vec2 ab = createVector(a, b);
      
      direction.set(-ab.y(), ab.x());
      if (!sameDirection(direction, ao)) {
        direction.negate();
      }
      
      return false;
    }
    else if (simplex.size() == 3) {
      final Vec2 a = simplex.get(2);
      final Vec2 b = simplex.get(1);
      final Vec2 c = simplex.get(0);
      
      final Vec2 ao = createVector(a, new Vec2());
      final Vec2 ab = createVector(a, b);
      final Vec2 ac = createVector(a, c);
      
      direction.set(-ab.y(), ab.x());
      if (sameDirection(direction, c)) {
        direction.negate();
      }
      
      if (sameDirection(direction, ao)) {
        simplex.remove(0);
        return false;
      }
      
      direction.set(-ac.y(), ac.x());
      if (sameDirection(direction, b)) {
        direction.negate();
      }
      
      if (sameDirection(direction, ao)) {
        simplex.remove(1);
        return false;
      }
      
      return true;
    }
    System.out.println("Invalid simplex!");
    return false;
  }
  
}
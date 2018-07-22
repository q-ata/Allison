package game;

import engine.Convex;
import engine.collision.GJK;

public class CollisionDetector extends GJK {
  
  // Check whether or not two hiboxes intersect.
  public static boolean hitboxIntersects(Hitbox a, Hitbox b) {
    for (Convex shape1 : a.getShapes()) {
      for (Convex shape2 : b.getShapes()) {
        if (intersects(shape1, shape2)) {
          return true;
        }
      }
    }
    return false;
  }

}

package game;

import engine.Convex;
import engine.collision.GJK;

public class CollisionDetector extends GJK {
  
  public static boolean hitboxIntersects(Hitbox a, Hitbox b) {
    for (Convex shape1 : a.getShapes()) {
      for (Convex shape2 : b.getShapes()) {
        if (GJK.intersects(shape1, shape2)) {
          return true;
        }
      }
    }
    return false;
  }

}

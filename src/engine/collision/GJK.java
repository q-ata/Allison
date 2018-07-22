package engine.collision;

import engine.Convex;
import engine.Vec2;

public class GJK {
  
  private static Vec2 tripleProduct(Vec2 a, Vec2 b, Vec2 c) {
    double ac = a.dot(c);
    double bc = b.dot(c);
    return new Vec2((b.x() * ac) - (a.x() * bc), (b.y() * ac) - (a.y() * bc));
  }
  
  private static Vec2 support(Convex shape1, Convex shape2, Vec2 dir) {
    return shape1.getFarthestPoint(dir).clone().sub(shape2.getFarthestPoint(dir.clone().negate()));
  }
  
  public static boolean intersects(Convex shape1, Convex shape2) {
    int index = 0;
    Vec2 pos1 = shape1.getCenter();
    Vec2 pos2 = shape2.getCenter();
    
    Vec2 d = pos1.clone().sub(pos2);
    
    if (d.x() == 0 && d.y() == 0) {
      d.setX(1);
    }
    
    Vec2[] simplex = new Vec2[3];
    Vec2 a = support(shape1, shape2, d);
    simplex[0] = a;
    
    if (a.dot(d) <= 0) {
      return false;
    }
    
    d = a.clone().negate();
    
    while (true) {
      a = support(shape1, shape2, d);
      simplex[++index] = a;
      
      if (a.dot(d) <= 0) {
        return false;
      }
      
      Vec2 ao = a.clone().negate();
      Vec2 b;
      Vec2 ab;
      
      if (index < 2) {
        b = simplex[0];
        ab = b.clone().sub(a);
        d = tripleProduct(ab, ao, ab);
        if (d.dot(d) == 0) {
          d = new Vec2(ab.y(), -ab.x());
        }
        continue;
      }
      
      b = simplex[1];
      Vec2 c = simplex[0];
      ab = b.clone().sub(a);
      Vec2 ac = c.clone().sub(a);
      
      Vec2 acp = tripleProduct(ab, ac, ac);
      
      if (acp.dot(ao) >= 0) {
        d = acp;
      }
      else {
        Vec2 abp = tripleProduct(ac, ab, ab);
        if (abp.dot(ao) < 0) {
          return true;
        }
        simplex[0] = simplex[1];
        d = abp;
      }
      simplex[1] = simplex[2];
      --index;
    }
  }

}

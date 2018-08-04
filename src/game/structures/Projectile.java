package game.structures;

import engine.Circle;
import engine.Convex;
import engine.Shape;
import engine.Vec2;
import game.Game;
import game.PlayableCharacter;
import game.ProjectileData;
import game.ProjectileSequence;
import game.Stats;
import game.blocks.RoomTransitioner;
import game.constants.Direction;

public class Projectile extends MapItem {
  
  private ProjectileData proj;
  private ProjectileSequence seq;
  private double width;
  private double height;
  private MapItem firer;
  
  public Projectile(Vec2 pos, ProjectileData data, Stats stats, MapItem firer, Direction dir, ProjectileSequence seq) {
    super(data.sprites().get(dir), pos);
    init(data, stats, firer, dir, seq);
  }
  
  public Projectile(Vec2 pos, ProjectileData data, Stats stats, PlayableCharacter player, Direction dir, ProjectileSequence seq) {
    super(data.sprites().get(dir), pos);
    init(data, stats, player, dir, seq);
  }                                                                                                                                                                                                                          
  
  private void init(ProjectileData data, Stats stats, MapItem firer, Direction dir, ProjectileSequence seq) {
    setFirer(firer);
    setSeq(seq);
    proj = new ProjectileData(data.rateBoost() + stats.rateBoost(), data.rateMulti() + stats.rateMulti(),
        data.speedBoost() + stats.speedBoost(), data.speedMulti() + stats.speedMulti(), data.damageBoost() + stats.damageBoost(),
        data.damageMulti() + stats.damageMulti(), data.rangeBoost() + stats.rangeBoost(), data.rangeMulti() + stats.rangeMulti(),
        data.scaleBoost() + stats.scaleBoost(), data.scaleMulti() + stats.scaleMulti(), data.sprites());
    setDir(dir);
    width = getCurrentSprite().getWidth();
    height = getCurrentSprite().getHeight();
    scale(proj().scaleMulti(), proj().scaleBoost());
  }
  
  public void scale(double multiplier, int add) {
    // Scale hitboxes. This is only needed for projectiles so far but may be moved to MapItem if needed.
    for (Convex convex : getHitbox().getShapes()) {
      if (convex instanceof Shape) {
        Shape shape = (Shape) convex;
        for (Vec2 point : shape.getPoints()) {
          point.set((point.x() * multiplier) + add, (point.y() * multiplier) + add);
        }
      }
      else {
        Circle circle = (Circle) convex;
        circle.setRadius((circle.getRadius() * multiplier) + add);
        // This math could probably be made more efficient.
        // It gets the farthest bottom right point and subtracts the radius from both coordinates.
        circle.setCenter(circle.getFarthestPoint(new Vec2(1, 1).sub(new Vec2(circle.getRadius(), circle.getRadius()))));
      }
    }
    
    // Set width and height for rendering.
    width *= multiplier;
    width += add;
    height *= multiplier;
    height += add;
  }
  
  @Override
  public boolean collisionProperties(final Game INSTANCE, MapItem collision) {
    if (collision instanceof Projectile || collision.equals(getFirer())) {
      return false;
    }
    INSTANCE.getRun().getCurrentRoom().getItems().removeProj(this);
    if (collision instanceof Entity) {
      Entity killable = (Entity) collision;
      if (killable.takeDamage((int) (proj().damageBoost() * proj().damageMulti()))) {
        INSTANCE.getRun().getCurrentRoom().getItems().removeEntity(killable);
        if (INSTANCE.getRun().getCurrentRoom().getItems().entities().size() == 0) {
          INSTANCE.getRun().getCurrentRoom().setCleared(true);
          RoomTransitioner.setOpen(true);
        }
      }
    }
    return true;
  }
  
  @Override
  public boolean collisionValid(final Game INSTANCE, MapItem collision) {
    return !collision.equals(getFirer());
  }
  
  public double getWidth() {
    return width;
  }
  
  public double getHeight() {
    return height;
  }
  
  public ProjectileData proj() {
    return proj;
  }

  public ProjectileSequence seq() {
    return seq;
  }

  public void setSeq(ProjectileSequence seq) {
    this.seq = seq;
  }

  public MapItem getFirer() {
    return firer;
  }

  public void setFirer(MapItem firer) {
    this.firer = firer;
  }
  
}

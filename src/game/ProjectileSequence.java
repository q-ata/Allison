package game;

import engine.Vec2;

public interface ProjectileSequence {
  
  public default Projectile locate(Direction dir, Game instance, ProjectileData data) {
    PlayableCharacter player = instance.getRun().getPlayer();
    Projectile proj = new Projectile(new Vec2(), data, player.getStats(), dir, this);
    double x = player.pos().x() + (player.getCurrentSprite().getWidth() / 2) - (proj.getWidth() / 2);
    double y = player.pos().y() + (player.getCurrentSprite().getHeight() / 2) - (proj.getHeight() / 2);
    proj.move(new Vec2(x, y));
    return proj;
  }
  
  public default void generate(Projectile proj, Game instance) {
    proj.setSolid(false);
    instance.getRun().getPlayer().setWeaponCooldown(1);
    instance.getRun().getCurrentRoom().mapItems().add(proj);
  }
  
  // TODO: Determine if additional arguments are needed.
  public default void behaviour(Projectile proj) {
    switch (proj.dir()) {
    case UP:
      proj.move(new Vec2(0, -3));
      break;
    case DOWN:
      proj.move(new Vec2(0, 3));
      break;
    case LEFT:
      proj.move(new Vec2(-3, 0));
      break;
    case RIGHT:
      proj.move(new Vec2(3, 0));
      break;
    default:
      proj.move(new Vec2(0, -3));
      break;
    }
  }

}

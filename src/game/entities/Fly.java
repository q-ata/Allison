package game.entities;

import engine.Vec2;
import game.Entity;
import game.Game;
import game.MapItem;
import game.PlayableCharacter;

public class Fly extends Entity {
  
  private int hitInterval = 1;
  private int requiredInterval = 0;
  private Vec2 dir;
  private boolean needsIdle = false;
  private int idleLimit = 0;
  private int idleTime = 0;

  public Fly(Vec2 pos) {
    super(pos, "enemies/fly/damaged"); 
  }
  
  @Override
  public void ai(final Game INSTANCE) {
    if (!isSolid()) {
      setSolid(true);
    }
    PlayableCharacter player = INSTANCE.getRun().getPlayer();
    Vec2 center = player.getHitbox().getCenter();
    Vec2 diff = center.sub(getHitbox().getCenter());
    Vec2 newVel = new Vec2();
    
    if (hitInterval >= requiredInterval && !needsIdle) {
      double d = Math.sqrt(Math.pow(diff.x(), 2) + Math.pow(diff.y(), 2));
      double t = d / 2.2;
      
      newVel.set(diff.x() / t, diff.y() / t);
    }
    else {
      if (hitInterval >= requiredInterval && ++idleTime > idleLimit) {
        needsIdle = false;
      }
      else if (hitInterval < requiredInterval) {
        hitInterval++;
        newVel.add(dir);
      }
      else {
        int x = (int) Math.floor(Math.random() * 3);
        int y = (int) Math.floor(Math.random() * 3);
        if (Math.random() < 0.5) {
          x = -x;
        }
        if (Math.random() < 0.5) {
          y = -y;
        }
        newVel.add(new Vec2(x, y));
      }
    }
    setVel(newVel);
  }
  
  @Override
  public void collisionProperties(final Game INSTANCE, MapItem collision) {
    if (!(collision instanceof PlayableCharacter)) {
      if (isSolid()) {
        setSolid(false);
      }
      return;
    }
    needsIdle = true;
    idleTime = 0;
    idleLimit = (int) Math.floor(Math.random() * 120) + 120;
    requiredInterval = (int) Math.floor(Math.random() * 60) + 60;
    hitInterval = 0;
    dir = new Vec2(Math.floor(Math.random() * 2) + 2, Math.floor(Math.random() * 2) + 1);
    if (Math.random() < 0.5) {
      dir.setX(-dir.x());
    }
    if (Math.random() < 0.5) {
      dir.setY(-dir.y());
    }
  }

}

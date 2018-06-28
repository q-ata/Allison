package game;

import engine.Vec2;

public abstract class Entity extends Killable implements AI {
  
  private int damage = 0;

  public Entity(Vec2 pos, String damagedSprites) {
    super(pos, damagedSprites);
  }

  public int getDamage() {
    return damage;
  }

  public void setDamage(int damage) {
    this.damage = damage;
  }

}

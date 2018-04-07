package game;

public abstract class Entity extends Killable implements AI {
  
  private int damage = 10;

  public Entity(MapItemData data, AnimationSequence[] damagedSprites) {
    super(data, damagedSprites);
  }

  public int getDamage() {
    return damage;
  }

  public void setDamage(int damage) {
    this.damage = damage;
  }

}

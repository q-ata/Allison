package game;

public abstract class Entity extends Killable implements AI {

  public Entity(MapItemData data, AnimationSequence[] damagedSprites) {
    super(data, damagedSprites);
  }

}

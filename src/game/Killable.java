package game;

import java.util.HashMap;

public abstract class Killable extends MapItem {
  
  private int health = 100;
  private HashMap<Direction, AnimationSequence> damagedSprites = new HashMap<Direction, AnimationSequence>();
  private boolean damaged;
  
  public Killable(MapItemData data, AnimationSequence[] damagedSprites) {
    super(data);
    for (AnimationSequence sequence : damagedSprites) {
      getDamagedSprites().put(sequence.dir(), sequence);
    }
  }

  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  public HashMap<Direction, AnimationSequence> getDamagedSprites() {
    return damagedSprites;
  }

  public void setDamagedSprites(HashMap<Direction, AnimationSequence> damagedSprites) {
    this.damagedSprites = damagedSprites;
  }

  public boolean isDamaged() {
    return damaged;
  }

  public void setDamaged(boolean damaged) {
    this.damaged = damaged;
  }

}

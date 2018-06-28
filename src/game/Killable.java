package game;

import java.util.HashMap;

import engine.Vec2;

public abstract class Killable extends MapItem {
  
  private int health = 100;
  private HashMap<Direction, AnimationSequence> damagedSprites = new HashMap<Direction, AnimationSequence>();
  private boolean damaged;
  private double resistance = 1.0;
  
  public Killable(Vec2 pos, String damagedSprites) {
    super(pos);
    // TODO: Optimize so I'm not reading a file every time.
    AnimationSequence[] sequences = DataLoader.loadSequences(damagedSprites);
    for (AnimationSequence sequence : sequences) {
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

  public double getResistance() {
    return resistance;
  }

  public void setResistance(double resistance) {
    this.resistance = resistance;
  }

}

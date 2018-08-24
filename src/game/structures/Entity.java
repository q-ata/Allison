package game.structures;

import java.util.HashMap;

import engine.Vec2;
import game.AnimationSequence;
import game.DataLoader;
import game.Run;
import game.constants.Direction;

public abstract class Entity extends MapItem implements AI {
  
  private int health = 100;
  private HashMap<Direction, AnimationSequence> damagedSprites = new HashMap<Direction, AnimationSequence>();
  private boolean damaged;
  private double baseResistance = 1.0;
  private double resistance = 1.0;
  private int damage = 0;
  
  public Entity(Vec2 pos, String damagedSprites) {
    super(pos);
    // TODO: Optimize so I'm not reading a file every time.
    AnimationSequence[] sequences = DataLoader.loadSequences(damagedSprites);
    for (AnimationSequence sequence : sequences) {
      getDamagedSprites().put(sequence.dir(), sequence);
    }
  }
  
  public Entity(Vec2 pos, String damagedSprites, int damage) {
    super(pos);
    AnimationSequence[] sequences = DataLoader.loadSequences(damagedSprites);
    for (AnimationSequence sequence : sequences) {
      getDamagedSprites().put(sequence.dir(), sequence);
    }
    setDamage(damage);
  }
  
  // Returns true if Killable is now dead.
  public boolean takeDamage(int dmg) {
    setHealth((int) (getHealth() - (dmg * getResistance())));
    return getHealth() <= 0;
  }
  
  public boolean takeDamage(int dmg, int invincible, Run run) {
    if (takeDamage(dmg)) {
      return true;
    }
    if (getResistance() != 0) {
      setResistance(0);
      Scheduler s = new Scheduler(invincible, run) {
        @Override
        public void run() {
          if (counter + 1 == invincible) {
            setResistance(getBaseResistance());
          }
        }
      };
      run.addScheduler(s);
    }
    return false;
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

  public double getBaseResistance() {
    return baseResistance;
  }

  public void setBaseResistance(double baseResistance) {
    this.baseResistance = baseResistance;
  }

  public double getResistance() {
    return resistance;
  }

  public void setResistance(double resistance) {
    this.resistance = resistance;
  }

  public int getDamage() {
    return damage;
  }

  public void setDamage(int damage) {
    this.damage = damage;
  }

}

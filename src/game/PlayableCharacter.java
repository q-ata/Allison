package game;

import engine.Vec2;
import game.weapons.TestWeapon;

public class PlayableCharacter extends Killable {
  
  private Stats stats = new Stats();
  private Weapon weapon = new TestWeapon();
  
  public PlayableCharacter() {
    super(DataLoader.loadMapItem("characters/main/build", new Vec2(453, 239)), DataLoader.loadSequences("characters/main/damaged"));
  }

  public Stats getStats() {
    return stats;
  }

  public void setStats(Stats stats) {
    this.stats = stats;
  }

  public Weapon getWeapon() {
    return weapon;
  }

  public void setWeapon(Weapon weapon) {
    this.weapon = weapon;
  }
  
}

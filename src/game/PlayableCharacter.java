package game;

import engine.Vec2;
import game.structures.Ability;
import game.structures.Entity;

public class PlayableCharacter extends Entity {
  
  // fire rate boost.
  // fire rate multiplier.
  // speed increase.
  // speed multiplier.
  // projectile speed boost.
  // projectile speed multiplier.
  // damage increase.
  // damage multiplier.
  // range increase.
  // range multiplier.
  private Stats stats = new Stats();
  private Weapon weapon;
  private int weaponCooldown = 0;
  
  private Ability abilA;
  private Ability abilB;
  
  private double moveSpeed = 2.8;
  
  public PlayableCharacter() {
    super(new Vec2(), "characters/main/damaged");
  }
  
  public int increaseWeaponCooldown() {
    setWeaponCooldown(getWeaponCooldown() + 1);
    return getWeaponCooldown();
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

  public int getWeaponCooldown() {
    return weaponCooldown;
  }

  public void setWeaponCooldown(int weaponCooldown) {
    this.weaponCooldown = weaponCooldown;
  }

  public Ability getAbilA() {
    return abilA;
  }

  public void setAbilA(Ability abilA) {
    this.abilA = abilA;
  }

  public Ability getAbilB() {
    return abilB;
  }

  public void setAbilB(Ability abilB) {
    this.abilB = abilB;
  }

  public double getMoveSpeed() {
    return moveSpeed;
  }

  public void setMoveSpeed(double moveSpeed) {
    this.moveSpeed = moveSpeed;
  }
  
}

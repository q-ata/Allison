package game.weapons;

import game.Game;
import game.Weapon;
import game.projectile_sequences.StraightProjectileSequence;

public class TestWeapon extends Weapon {

  public TestWeapon(Game game) {
    super("test_weapon/hud", "test_weapon/hud_offset", "weapons/test_weapon/shot/build", new StraightProjectileSequence());
  }

}

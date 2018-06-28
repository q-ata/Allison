package projectile_sequences;

import game.Game;
import game.Projectile;
import game.ProjectileSequence;

public class TripleBurstProjectileSequence implements ProjectileSequence {
  
  private int counter = 0;
  private int generated = 1;
  
  @Override
  public void generate(Projectile proj, Game instance) {
    proj.setSolid(false);
    instance.getRun().getPlayer().setWeaponCooldown(1);
    instance.getRun().getCurrentRoom().mapItems().add(proj);
    instance.getRun().addScheduler(new Runnable() {
      @Override
      public void run() {
        if (++counter == 10) {
          counter = 0;
          Projectile projec = locate(proj.dir(), instance, instance.getRun().getPlayer().getWeapon().getData());
          projec.setSolid(false);
          instance.getRun().getPlayer().setWeaponCooldown(1);
          instance.getRun().getCurrentRoom().mapItems().add(projec);
          if (++generated == 3) {
            instance.getRun().removeScheduler(this);
            generated = 1;
          }
        }
      }
    });
    
  }

}

package game.projectile_sequences;

import game.Game;
import game.ProjectileSequence;
import game.structures.Projectile;
import game.structures.Scheduler;

public class TripleBurstProjectileSequence implements ProjectileSequence {
  
  private int counter = 0;
  
  @Override
  public void generate(Projectile proj, Game instance) {
    instance.getRun().getPlayer().setWeaponCooldown(1);
    instance.getRun().getCurrentRoom().getItems().addProj(proj);
    Scheduler.create(new Runnable() {
      @Override
      public void run() {
        if (++counter == 10) {
          counter = 0;
          Projectile projec = locate(proj.dir(), instance, instance.getRun().getPlayer().getWeapon().getData());
          instance.getRun().getPlayer().setWeaponCooldown(1);
          instance.getRun().getCurrentRoom().getItems().addProj(projec);
        }
      }
    }, 20, instance.getRun());
    
  }

}

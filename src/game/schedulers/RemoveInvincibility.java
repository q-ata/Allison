package game.schedulers;

import game.Entity;
import game.Run;

public class RemoveInvincibility implements Runnable {
  
  private int counter = 0;
  private int required;
  private Entity e;
  private Run run;
  
  public RemoveInvincibility(int required, Entity e, Run run) {
    this.required = required;
    this.e = e;
    this.run = run;
  }

  @Override
  public void run() {
    if (++counter == required) {
      e.setResistance(e.getBaseResistance());
      run.removeScheduler(this);
    }
  }

}

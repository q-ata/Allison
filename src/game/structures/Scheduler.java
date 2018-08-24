package game.structures;

import game.Run;

public abstract class Scheduler implements Runnable {
  
  private int frames;
  int counter = 0;
  private Run instance;
  
  public Scheduler(int frames, Run instance) {
    this.frames = frames;
    this.instance = instance;
  }
  
  public void inc() {
    run();
    if (++counter == frames) {
      instance.removeScheduler(this);
    }
  }
  
  public static void create(Runnable task, int frames, Run instance) {
    instance.addScheduler(new Scheduler(frames, instance) {
      @Override
      public void run() {
        task.run();
      }
    });
  }

}

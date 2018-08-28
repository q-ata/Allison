package game.structures;

import game.Game;
import javafx.scene.input.KeyCode;

public abstract class Ability implements AbilityCast {
  
  private final double MAX;
  private final double REQ;
  private double charge = 0;
  
  private boolean active = false;
  
  private KeyCode bind = KeyCode.E;
  
  public Ability(double max, double req) {
    MAX = max;
    REQ = req;
  }
  
  public void end(Game instance) {
    setActive(false);
    aftercast(instance);
  }

  public double getMax() {
    return MAX;
  }

  public double getReq() {
    return REQ;
  }

  public double getCharge() {
    return charge;
  }
  
  public void addCharge(double charge) {
    this.charge += charge;
    if (this.charge > MAX) {
      this.charge = MAX;
    }
  }

  public void setCharge(double charge) {
    this.charge = charge;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public KeyCode getBind() {
    return bind;
  }

  public void setBind(KeyCode bind) {
    this.bind = bind;
  }

}

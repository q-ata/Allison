package game.structures;

public abstract class Ability implements AbilityCast {
  
  private final double REQUIRED;
  private double charge = 0;
  
  private boolean active = false;
  
  public Ability(double req) {
    REQUIRED = req;
  }

  public double getRequired() {
    return REQUIRED;
  }

  public double getCharge() {
    return charge;
  }
  
  public void addCharge(double charge) {
    this.charge += charge;
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

}

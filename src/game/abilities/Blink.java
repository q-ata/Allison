package game.abilities;

import game.Game;
import game.structures.Ability;

public class Blink extends Ability {

  public Blink() {
    super(100);
  }
  
  @Override
  public void precast(Game instance) {
    addCharge(getRequired() / 2);
  }
  
  @Override
  public void cast(Game instance) {
    
  }

}

package game.blocks;

import engine.Vec2;
import game.DataLoader;
import game.Game;
import game.MapItem;

public class BasicRock extends MapItem {

  public BasicRock(Vec2 pos) {
    super(DataLoader.loadMapItem("blocks/basic_rock/build", pos));
  }
  
  public void collisionProperties(final Game INSTANCE) {
    return;
  }

}

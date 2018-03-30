package game.blocks;

import engine.Vec2;
import game.Game;
import game.MapItem;
import game.MapItemLoader;

public class BasicRock extends MapItem {

  public BasicRock(Vec2 pos) {
    super(MapItemLoader.load("blocks/basic_rock/build", pos));
  }
  
  public void collisionProperties(final Game INSTANCE) {
    return;
  }

}

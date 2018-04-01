package game;

import engine.Vec2;

public class PlayableCharacter extends MapItem {
  
  public PlayableCharacter(Vec2 pos) {
    super(DataLoader.loadMapItem("characters/main/build", pos));
  }

  @Override
  public void collisionProperties(final Game INSTANCE) {
    return;
  }
  
}

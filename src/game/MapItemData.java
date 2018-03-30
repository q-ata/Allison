package game;

import engine.Vec2;

public class MapItemData {
  
  private AnimationSequence[] sequences;
  private Vec2 pos;
  
  public MapItemData(AnimationSequence[] sequences, Vec2 pos) {
    this.sequences = sequences;
    this.pos = pos;
  }
  
  public AnimationSequence[] sequences() {
    return sequences;
  }
  
  public Vec2 pos() {
    return pos;
  }

}

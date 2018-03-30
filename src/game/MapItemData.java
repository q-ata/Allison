package game;

import engine.Vec2;

public class MapItemData {
  
  private AnimationSequence[] sequences;
  private Vec2 pos;
  private Hitbox box;
  
  public MapItemData(AnimationSequence[] sequences, Vec2 pos, Hitbox box) {
    this.sequences = sequences;
    this.pos = pos;
    this.box = box;
  }
  
  public AnimationSequence[] sequences() {
    return sequences;
  }
  
  public Vec2 pos() {
    return pos;
  }
  
  public Hitbox box() {
    return box;
  }

}

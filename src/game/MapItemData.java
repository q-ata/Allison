package game;

// Encapsulated MapItem properties for the MapItem constructor.
public class MapItemData {
  
  private AnimationSequence[] sequences;
  private Hitbox box;
  
  public MapItemData(AnimationSequence[] sequences, Hitbox box) {
    this.sequences = sequences;
    this.box = box;
  }
  
  public MapItemData(AnimationSequence sequence, Hitbox box) {
    this.sequences = new AnimationSequence[] {sequence};
    this.box = box;
  }
  
  public AnimationSequence[] sequences() {
    return sequences;
  }
  
  public Hitbox box() {
    return box;
  }

}

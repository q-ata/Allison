package game;

public class Run {
  
  private final long SEED;
  private final PlayableCharacter PLAYER;
  
  private Room[] rooms;

  public Run(long seed, PlayableCharacter player) {
    SEED = seed;
    PLAYER = player;
    // TODO: Procedurally generate levels.
  }
  
  public long SEED() {
    return SEED;
  }

  public PlayableCharacter PLAYER() {
    return PLAYER;
  }

  public Room[] getRooms() {
    return rooms;
  }

  public void setRooms(Room[] rooms) {
    this.rooms = rooms;
  }

}

package game;

public class Run {
  
  private final long SEED;
  private final PlayableCharacter PLAYER;
  
  private Room[] rooms;
  private Room currentRoom;

  public Run(long seed, PlayableCharacter player) {
    SEED = seed;
    PLAYER = player;
    setCurrentRoom(new Room(1, getPLAYER()));
    // TODO: Procedurally generate levels.
  }
  
  public long getSEED() {
    return SEED;
  }

  public PlayableCharacter getPLAYER() {
    return PLAYER;
  }

  public Room[] getRooms() {
    return rooms;
  }

  public void setRooms(Room[] rooms) {
    this.rooms = rooms;
  }

  public Room getCurrentRoom() {
    return currentRoom;
  }

  public void setCurrentRoom(Room currentRoom) {
    this.currentRoom = currentRoom;
  }

}

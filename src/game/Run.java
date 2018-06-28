package game;

import java.util.ArrayList;
import java.util.List;

public class Run {
  
  private final long SEED;
  private PlayableCharacter player;
  
  private Room[] rooms;
  private Room currentRoom;
  
  private List<Runnable> schedulers = new ArrayList<Runnable>();
  private List<Runnable> toRemove = new ArrayList<Runnable>();

  public Run(long seed, PlayableCharacter player) {
    SEED = seed;
    this.player = player;
    setCurrentRoom(new Room(1, getPlayer()));
    // TODO: Procedurally generate levels.
  }
  
  public void addScheduler(Runnable runnable) {
    schedulers.add(runnable);
  }
  
  public void removeScheduler(Runnable runnable) {
    toRemove.add(runnable);
  }
  
  public List<Runnable> getSchedulers() {
    return schedulers;
  }
  
  public List<Runnable> toRemove() {
    return toRemove;
  }
  
  public long getSEED() {
    return SEED;
  }

  public PlayableCharacter getPlayer() {
    return player;
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

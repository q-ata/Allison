package game;

import java.util.ArrayList;
import java.util.List;

public class Room {
  
  private final int ID;
  private PlayableCharacter player;
  
  private List<MapItem> mapItems = new ArrayList<MapItem>();
  
  private boolean visited = false;
  private boolean cleared = false;
  
  public Room(int id, PlayableCharacter player) {
    ID = id;
    this.player = player;
    mapItems().add(getPlayer());
    // TODO: Load room based on ID.
  }

  public int getID() {
    return ID;
  }

  public PlayableCharacter getPlayer() {
    return player;
  }

  public List<MapItem> mapItems() {
    return mapItems;
  }

  public boolean isVisited() {
    return visited;
  }

  public void setVisited(boolean visited) {
    this.visited = visited;
  }

  public boolean isCleared() {
    return cleared;
  }

  public void setCleared(boolean cleared) {
    this.cleared = cleared;
  }

}

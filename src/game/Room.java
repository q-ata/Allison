package game;

import java.util.ArrayList;
import java.util.List;

public class Room {
  
  private final int ID;
  private final PlayableCharacter PLAYER;
  
  private List<MapItem> mapItems = new ArrayList<MapItem>();
  private List<Killable> killables = new ArrayList<Killable>();
  
  private boolean visited = false;
  private boolean cleared = false;
  
  public Room(int id, PlayableCharacter player) {
    ID = id;
    PLAYER = player;
    mapItems().add(getPLAYER());
    killables().add(getPLAYER());
    // TODO: Load room based on ID.
  }

  public int getID() {
    return ID;
  }

  public PlayableCharacter getPLAYER() {
    return PLAYER;
  }

  public List<MapItem> mapItems() {
    return mapItems;
  }

  public List<Killable> killables() {
    return killables;
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

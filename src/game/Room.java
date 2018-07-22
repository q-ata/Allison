package game;

public class Room {
  
  private final int ID;
  private PlayableCharacter player;
  
  private MapItemStore items = new MapItemStore();
  
  private boolean visited = false;
  private boolean cleared = false;
  
  public Room(int id, PlayableCharacter player) {
    ID = id;
    this.player = player;
    // getItems().all().add(getPlayer());
    // TODO: Load room based on ID.
  }

  public int getID() {
    return ID;
  }

  public PlayableCharacter getPlayer() {
    return player;
  }
  
  public MapItemStore getItems() {
    return items;
  }

  public void setItems(MapItemStore items) {
    this.items = items;
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

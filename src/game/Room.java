package game;

import java.util.ArrayList;
import java.util.List;

import engine.Vec2;
import game.blocks.RoomTransitioner;

public class Room {
  
  private final int ID;
  private PlayableCharacter player;
  
  private MapItemStore items = new MapItemStore();
  
  private boolean visited = false;
  private boolean cleared = false;
  private Vec2 coord;
  private List<RoomTransitioner> rts = new ArrayList<RoomTransitioner>(4);
  
  public Room(Vec2 coord, int id, PlayableCharacter player) {
    this.setCoord(coord);
    ID = id;
    this.player = player;
    if (getItems().entities().size() == 0) {
      setCleared(true);
    }
  }

  public int getID() {
    return ID;
  }

  // TODO: Why does Room need a player instance?
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

  public Vec2 coord() {
    return coord;
  }

  public void setCoord(Vec2 coord) {
    this.coord = coord;
  }

  public List<RoomTransitioner> getRts() {
    return rts;
  }

  public void setRts(List<RoomTransitioner> rts) {
    this.rts = rts;
  }

}

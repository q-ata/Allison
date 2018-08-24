package game;

import java.util.ArrayList;
import java.util.List;

import engine.Vec2;
import game.blocks.RoomTransitioner;
import game.blocks.RoomTransitionerX;
import game.blocks.RoomTransitionerY;
import game.structures.Scheduler;

public class Run {
  
  private static final int[] roomCount = {(int) Math.floor(Math.random() * 3) + 10, (int) Math.floor(Math.random() * 3) + 12,
      (int) Math.floor(Math.random() * 3) + 14, (int) Math.floor(Math.random() * 3) + 16, (int) Math.floor(Math.random() * 3) + 18,
      (int) Math.floor(Math.random() * 3) + 20};
  
  private final long SEED;
  private Game instance;
  private PlayableCharacter player;
  
  private Room[][] rooms;
  private Room currentRoom;
  private Vec2 currentPos;
  private int floor = 0;
  
  private List<Scheduler> schedulers = new ArrayList<Scheduler>();
  private List<Scheduler> toRemove = new ArrayList<Scheduler>();
  
  private LevelGenerator generator = new LevelGenerator();

  public Run(long seed, Game instance, PlayableCharacter player) {
    SEED = seed;
    this.instance = instance;
    this.player = player;
    LevelGenerator.Floor data = generator.generateFloor(roomCount[floor], player);
    setRooms(data.rooms);
    setCurrentPos(new Vec2((int) data.spawn.x(), (int) data.spawn.y()));
    // TODO: Procedurally generate levels.
  }
  
  public int addScheduler(Scheduler s) {
    schedulers.add(s);
    return schedulers.size();
  }
  
  public void removeScheduler(Scheduler s) {
    toRemove.add(s);
  }
  
  public List<Scheduler> getSchedulers() {
    return schedulers;
  }
  
  public List<Scheduler> toRemove() {
    return toRemove;
  }
  
  public long getSEED() {
    return SEED;
  }

  public PlayableCharacter getPlayer() {
    return player;
  }

  public Room[][] getRooms() {
    return rooms;
  }

  public void setRooms(Room[][] rooms) {
    this.rooms = rooms;
  }

  public Room getCurrentRoom() {
    return currentRoom;
  }

  public void setCurrentRoom(Vec2 coord) {
    setCurrentPos(coord);
    int x = (int) coord.x();
    int y = (int) coord.y();
    Room room = getRooms()[x][y];
    currentRoom = room;
    if (!room.isCleared()) {
      RoomTransitioner.setAllOpen(false, room);
    }
    getPlayer().move(new Vec2(12, 409).sub(getPlayer().pos()));
    instance.tui().updateMinimap();
    if (room.getRts().size() != 0) {
      return;
    }
    int nx = x;
    int ny = y - 1;
    // Left side
    if (ny >= 0 && getRooms()[nx][ny] != null) {
      room.getRts().add(new RoomTransitionerY(new Vec2(0, 228), new Vec2(nx, ny)));
    }
    ny += 2;
    // Right side
    if (ny < getRooms()[0].length && getRooms()[nx][ny] != null) {
      room.getRts().add(new RoomTransitionerY(new Vec2(948, 228), new Vec2(nx, ny)));
    }
    ny--;
    nx--;
    // Up
    if (nx >= 0 && getRooms()[nx][ny] != null) {
      room.getRts().add(new RoomTransitionerX(new Vec2(438, 0), new Vec2(nx, ny)));
    }
    nx += 2;
    // Down
    if (nx < getRooms().length && getRooms()[nx][ny] != null) {
      room.getRts().add(new RoomTransitionerX(new Vec2(438, 528), new Vec2(nx, ny)));
    }
    for (RoomTransitioner rt : room.getRts()) {
      room.getItems().addBlock(rt);
    }
    if (room.getItems().entities().size() == 0 && !room.isCleared()) {
      room.setCleared(true);
      RoomTransitioner.setAllOpen(true, room);
    }
  }

  public Vec2 getCurrentPos() {
    return currentPos;
  }

  public void setCurrentPos(Vec2 currentPos) {
    this.currentPos = currentPos;
  }

}

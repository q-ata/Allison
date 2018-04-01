package game;

import java.util.ArrayList;

import engine.GameProcess;
import engine.KeyboardInputs;
import engine.Shape;
import engine.Vec2;
import game.blocks.BasicRock;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;

public class GameLoop implements GameProcess, EventHandler<ActionEvent> {
  
  private final Game INSTANCE;
  private long passed = 0;
  private long prev = System.nanoTime();
  private ArrayList<MapItem> mapItems = new ArrayList<MapItem>();
  
  public GameLoop(Game instance) {
    INSTANCE = instance;
    PlayableCharacter player = new PlayableCharacter(new Vec2(200, 200));
    mapItems.add(player);
    player.move(new Vec2(400, 300));
    INSTANCE.setRun(new Run(System.nanoTime(), player));
    mapItems.add(new BasicRock(new Vec2()));
  }
  
  // TODO: Documentation for input, physics and render methods.
  @Override
  public void input() {
    MapItem main = mapItems.get(0);
    boolean advance = true;
    if (KeyboardInputs.KEYMAP.get(KeyCode.W)) {
      if (main.dir() != Direction.UP) {
        main.setDir(Direction.UP);
      }
      main.setVel(new Vec2(0, -2));
    }
    else if (KeyboardInputs.KEYMAP.get(KeyCode.S)) {
      if (main.dir() != Direction.DOWN) {
        main.setDir(Direction.DOWN);
      }
      main.setVel(new Vec2(0, 2));
    }
    else if (KeyboardInputs.KEYMAP.get(KeyCode.A)) {
      if (main.dir() != Direction.LEFT) {
        main.setDir(Direction.LEFT);
      }
      main.setVel(new Vec2(-2, 0));
    }
    else if (KeyboardInputs.KEYMAP.get(KeyCode.D)) {
      if (main.dir() != Direction.RIGHT) {
        main.setDir(Direction.RIGHT);
      }
      main.setVel(new Vec2(2, 0));
    }
    else {
      advance = false;
    }
    if (advance && !main.isMoving()) {
      main.getSpriteSet().get(main.dir()).advanceAnimation();
      main.setMoving(true);
    }
    else if (!advance){
      main.setMoving(false);
      main.setVel(new Vec2());
    }
  }

  @Override
  public void physics() {
    
    for (int i = 0; i < mapItems().size(); i++) {
      MapItem item = mapItems().get(i);
      if (item.vel().x() != 0 || item.vel().y() != 0) {
        item.move(item.vel());
        for (int j = 0; j < mapItems().size(); j++) {
          if (j == i) {
            continue;
          }
          if (CollisionDetector.hitboxIntersects(item.getHitbox(), mapItems().get(j).getHitbox())) {
            item.move(item.vel().clone().negate());
            item.collisionProperties(INSTANCE);
            mapItems().get(j).collisionProperties(INSTANCE);
          }
        }
      }
    }
    
  }

  @Override
  public void render() {
    
    INSTANCE.gc().fillRect(0, 0, 800, 600);
    
    for (MapItem item : mapItems()) {
      if (item == null) {
        continue;
      }
      
      AnimationSequence seq = item.getSpriteSet().get(item.dir());
      if (!(item.isNeedMoving() && !item.isMoving())) {
        seq.advanceAnimationTimer();
      }
      INSTANCE.gc().drawImage(seq.getSprite(), item.pos().x(), item.pos().y());
      
      for (int q = 0; q < item.getHitbox().getShapes().length; q++) {
        for (int i = 0; i < ((Shape) item.getHitbox().getShapes()[q]).getPoints().length; i++) {
          Shape s = (Shape) item.getHitbox().getShapes()[q];
          Vec2 vec = s.getPoints()[i];
          Vec2 next = i == s.getPoints().length - 1 ? s.getPoints()[0] : s.getPoints()[i + 1];
          INSTANCE.gc().strokeLine(vec.x(), vec.y(), next.x(), next.y());
        }
      }
    }
    
  }

  @Override
  public void handle(ActionEvent ev) {
    input();
    physics();
    render();
    
    // Calculate FPS.
    INSTANCE.setFps(INSTANCE.getFps() + 1);
    long now = System.nanoTime();
    long delta = now - prev;
    passed += delta;
    prev = now;
    if (passed >= 1000000000) {
      passed = 0;
      System.out.println(INSTANCE.getFps() + " FPS");
      INSTANCE.setFps(0);
    }
  }

  public ArrayList<MapItem> mapItems() {
    return mapItems;
  }

  public void setMapItems(ArrayList<MapItem> mapItems) {
    this.mapItems = mapItems;
  }

}

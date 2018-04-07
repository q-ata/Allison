package game;

import java.util.List;

import engine.Circle;
import engine.Convex;
import engine.GameProcess;
import engine.KeyboardInputs;
import engine.Shape;
import engine.Vec2;
import game.blocks.BasicRock;
import game.entities.Fly;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;

public class GameLoop implements GameProcess, EventHandler<ActionEvent> {
  
  private final Game INSTANCE;
  private long passed = 0;
  private long prev = System.nanoTime();
  
  public GameLoop(Game instance) {
    INSTANCE = instance;
    PlayableCharacter player = new PlayableCharacter();
    INSTANCE.setRun(new Run(System.nanoTime(), player));
    // TODO: Remove later.
    INSTANCE.getRun().getCurrentRoom().mapItems().add(new BasicRock(new Vec2(200, 150)));
    INSTANCE.getRun().getCurrentRoom().mapItems().add(new BasicRock(new Vec2(600, 250)));
    INSTANCE.getRun().getCurrentRoom().mapItems().add(new BasicRock(new Vec2(400, 150)));
    Fly fly = new Fly(new Vec2(150, 350));
    INSTANCE.getRun().getCurrentRoom().mapItems().add(fly);
    INSTANCE.getRun().getCurrentRoom().entities().add(fly);
  }
  
  // TODO: Documentation for input, physics and render methods.
  @Override
  public void input() {
    MapItem main = INSTANCE.getRun().getCurrentRoom().mapItems().get(0);
    boolean advance = true;
    if (KeyboardInputs.KEYMAP.get(KeyCode.W)) {
      if (main.dir() != Direction.UP) {
        main.setDir(Direction.UP);
      }
      main.setVel(new Vec2(0, -2.5));
    }
    else if (KeyboardInputs.KEYMAP.get(KeyCode.S)) {
      if (main.dir() != Direction.DOWN) {
        main.setDir(Direction.DOWN);
      }
      main.setVel(new Vec2(0, 2.5));
    }
    else if (KeyboardInputs.KEYMAP.get(KeyCode.A)) {
      if (main.dir() != Direction.LEFT) {
        main.setDir(Direction.LEFT);
      }
      main.setVel(new Vec2(-2.5, 0));
    }
    else if (KeyboardInputs.KEYMAP.get(KeyCode.D)) {
      if (main.dir() != Direction.RIGHT) {
        main.setDir(Direction.RIGHT);
      }
      main.setVel(new Vec2(2.5, 0));
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
    
    for (Entity entity : INSTANCE.getRun().getCurrentRoom().entities()) {
      entity.ai(INSTANCE);
    }
    
    List<MapItem> mapItems = INSTANCE.getRun().getCurrentRoom().mapItems();
    
    for (int i = 0; i < mapItems.size(); i++) {
      MapItem item = mapItems.get(i);
      if (item.vel().x() != 0 || item.vel().y() != 0) {
        item.move(item.vel());
        for (int j = 0; j < mapItems.size(); j++) {
          if (j == i) {
            continue;
          }
          if (CollisionDetector.hitboxIntersects(item.getHitbox(), mapItems.get(j).getHitbox())) {
            if (item.isSolid() && mapItems.get(j).isSolid()) {
              item.collisionProperties(INSTANCE, mapItems.get(j));
              mapItems.get(j).collisionProperties(INSTANCE, item);
              // It is possible for collision properties method to change whether or not the item is solid.
              if (item.isSolid() && mapItems.get(j).isSolid()) {
                item.move(item.vel().clone().negate());
              }
            }
          }
        }
      }
    }
    
  }

  @Override
  public void render() {
    
    INSTANCE.gc().fillRect(0, 0, INSTANCE.gc().getCanvas().getWidth(), INSTANCE.gc().getCanvas().getHeight());
    
    // Render all map items and process animations.
    for (MapItem item : INSTANCE.getRun().getCurrentRoom().mapItems()) {
      if (item == null) {
        continue;
      }
      
      AnimationSequence seq = item.getSpriteSet().get(item.dir());
      if (!(item.isNeedMoving() && !item.isMoving())) {
        seq.advanceAnimationTimer();
      }
      INSTANCE.gc().drawImage(seq.getSprite(), item.pos().x(), item.pos().y());
      
      for (int q = 0; q < item.getHitbox().getShapes().length; q++) {
        Convex c = item.getHitbox().getShapes()[q];
        if (!(c instanceof Shape)) {
          Circle circle = (Circle) c;
          INSTANCE.gc().strokeOval(circle.getCenter().x() - circle.getRadius(), circle.getCenter().y() - circle.getRadius(), circle.getRadius() * 2, circle.getRadius() * 2);
          continue;
        }
        for (int i = 0; i < ((Shape) c).getPoints().length; i++) {
          Shape s = (Shape) c;
          Vec2 vec = s.getPoints()[i];
          Vec2 next = i == s.getPoints().length - 1 ? s.getPoints()[0] : s.getPoints()[i + 1];
          INSTANCE.gc().strokeLine(vec.x(), vec.y(), next.x(), next.y());
        }
      }

    }
    
    // Render HUD.
    INSTANCE.gc().drawImage(HUDSprites.HUDBASE, 3, 3);
    // Render health and mana bar depending on amount remaining.
    INSTANCE.gc().drawImage(HUDSprites.HUDHEALTH, 0, 0, 127, 11, 66, 24, (int) Math.round(127 / (100 / INSTANCE.getRun().getPLAYER().getHealth())), 11);
    Weapon weapon = INSTANCE.getRun().getPLAYER().getWeapon();
    INSTANCE.gc().drawImage(HUDSprites.HUDMANA, 0, 0, 123, 6, 66, 40, (int) Math.round(123 / (100 / weapon.getMana())), 6);
    
    // Render weapon sprite in HUD.
    INSTANCE.gc().drawImage(weapon.getHudSprite(), 10 + weapon.getHudOffset().x(), 10 + weapon.getHudOffset().y());
    
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

}

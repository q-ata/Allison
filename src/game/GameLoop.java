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
import game.weapons.TestWeapon;
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
    player.setWeapon(new TestWeapon(INSTANCE));
    INSTANCE.setRun(new Run(System.nanoTime(), player));
    // TODO: Remove later.
    BasicRock rock = new BasicRock(new Vec2(200, 150));
    Fly fly = new Fly(new Vec2(150, 350));
    INSTANCE.getRun().getCurrentRoom().getItems().addBlock(rock);
    INSTANCE.getRun().getCurrentRoom().getItems().addBlock(new BasicRock(new Vec2(600, 250)));
    INSTANCE.getRun().getCurrentRoom().getItems().addBlock(new BasicRock(new Vec2(400, 150)));
    INSTANCE.getRun().getCurrentRoom().getItems().addEntity(fly);
  }
  
  private void handleProjectileInput() {
    PlayableCharacter player = INSTANCE.getRun().getPlayer();
    Projectile proj;
    if (player.getWeaponCooldown() != 0) {
      return;
    }
    if (KeyboardInputs.KEYMAP.get(KeyCode.UP)) {
      proj = player.getWeapon().getSequence().locate(Direction.UP, INSTANCE, player.getWeapon().getData());
    }
    else if (KeyboardInputs.KEYMAP.get(KeyCode.DOWN)) {
      proj = player.getWeapon().getSequence().locate(Direction.DOWN, INSTANCE, player.getWeapon().getData());
    }
    else if (KeyboardInputs.KEYMAP.get(KeyCode.LEFT)) {
      proj = player.getWeapon().getSequence().locate(Direction.LEFT, INSTANCE, player.getWeapon().getData());
    }
    else if (KeyboardInputs.KEYMAP.get(KeyCode.RIGHT)) {
      proj = player.getWeapon().getSequence().locate(Direction.RIGHT, INSTANCE, player.getWeapon().getData());
    }
    else {
      return;
    }
    
    player.getWeapon().getSequence().generate(proj, INSTANCE);
  }
  
  // TODO: Documentation for input, physics and render methods.
  @Override
  public void input() {
    
    handleProjectileInput();
    for (Runnable runnable : INSTANCE.getRun().getSchedulers()) {
      runnable.run();
    }
    for (Runnable runnable : INSTANCE.getRun().toRemove()) {
      INSTANCE.getRun().getSchedulers().remove(runnable);
    }
    INSTANCE.getRun().toRemove().clear();
    
    MapItem main = INSTANCE.getRun().getCurrentRoom().getItems().all().get(0);
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
    
    PlayableCharacter player = INSTANCE.getRun().getPlayer();
    if (player.getWeaponCooldown() != 0 && player.increaseWeaponCooldown() >= player.getWeapon().getCooldown()) {
      player.setWeaponCooldown(0);
    }
    
    List<MapItem> all = INSTANCE.getRun().getCurrentRoom().getItems().all();
    
    for (Entity e : INSTANCE.getRun().getCurrentRoom().getItems().entities()) {
      e.ai(INSTANCE);
    }
    
    for (Projectile p : INSTANCE.getRun().getCurrentRoom().getItems().projectiles()) {
      player.getWeapon().getSequence().behaviour(p);
    }
    
    for (int i = 0; i < all.size(); i++) {
      MapItem item = all.get(i);
      if (item.vel().sum() == 0.0 && !(item instanceof Projectile)) {
        continue;
      }
      item.move(item.vel());
      for (int j = 0; j < all.size(); j++) {
        if (j == i || !CollisionDetector.hitboxIntersects(item.getHitbox(), all.get(j).getHitbox())) {
          continue;
        }
        MapItem target = all.get(j);
        if (!item.isSolid() || !target.isSolid() || !item.collisionValid(INSTANCE, target) || !target.collisionValid(INSTANCE, item)) {
          continue;
        }
        item.collisionProperties(INSTANCE, all.get(j));
        target.collisionProperties(INSTANCE, item);
        // It is possible for collision properties method to change whether or not the item is solid.
        if (!item.isSolid() || !target.isSolid()) {
          continue;
        }
        item.move(item.vel().clone().negate());
      }
    }
    
  }

  @Override
  public void render() {
    
    INSTANCE.gc().fillRect(0, 0, INSTANCE.gc().getCanvas().getWidth(), INSTANCE.gc().getCanvas().getHeight());
    
    // TODO: Apply depending on room state.
    INSTANCE.gc().drawImage(Backgrounds.DEFAULT, 0, 0);
    INSTANCE.gc().drawImage(Backgrounds.BLOCKED, 0, 0);
    
    // Render all map items and process animations.
    for (MapItem item : INSTANCE.getRun().getCurrentRoom().getItems().all()) {
      if (item == null) {
        continue;
      }
      
      AnimationSequence seq = item.getSpriteSet().computeIfAbsent(item.dir(), (a) -> item.getSpriteSet().get(Direction.UP));
      if (!(item.isNeedMoving() && !item.isMoving())) {
        seq.advanceAnimationTimer();
      }
      if (item instanceof Projectile) {
        Projectile proj = (Projectile) item;
        INSTANCE.gc().drawImage(seq.getSprite(), item.pos().x(), item.pos().y(), proj.getWidth(), proj.getHeight());
      }
      else {
        INSTANCE.gc().drawImage(seq.getSprite(), item.pos().x(), item.pos().y());
      }
      
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
    INSTANCE.gc().drawImage(HUDSprites.HUDHEALTH, 0, 0, 127, 11, 66, 24, (int) Math.round(127d / (100d / INSTANCE.getRun().getPlayer().getHealth())), 11);
    Weapon weapon = INSTANCE.getRun().getPlayer().getWeapon();
    INSTANCE.gc().drawImage(HUDSprites.HUDMANA, 0, 0, 123, 6, 66, 40, (int) Math.round(123d / (100d / weapon.getMana())), 6);
    
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

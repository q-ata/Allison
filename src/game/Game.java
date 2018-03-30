package game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public final class Game extends Application {
  
  private final int TARGETFPS = 60;
  private final long INTERVAL = 16666666;
  private int fps = 0;
  private GraphicsContext gc;
  private boolean started = false;
  
  private PlayableCharacter player;
  
  public void initGame() {
    launch();
  }

  @Override
  public void start(Stage stage) {
    try {
      Canvas canvas = new Canvas(800, 600);
      GraphicsContext gc = canvas.getGraphicsContext2D();
      setGc(gc);
      gc().setFill(Color.WHITE);
      Scene scene = new Scene(new Group(canvas));
      scene.setOnKeyPressed(new KeyboardInputPressedHandler());
      scene.setOnKeyReleased(new KeyboardInputReleasedHandler());
      stage.setScene(scene);
      stage.setTitle("Game");
      Image icon = new Image("file:resources/misc/window_icon.png");
      stage.getIcons().add(icon);
      stage.show();
      setStarted(true);
      System.out.println("started");
      
      GameLoop loop = new GameLoop(this);
      Timeline gameLoop = new Timeline();
      gameLoop.setCycleCount(Timeline.INDEFINITE);
      KeyFrame keyframe = new KeyFrame(Duration.millis(16.66), loop);
      gameLoop.getKeyFrames().add(keyframe);
      gameLoop.play();
      
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public int getTARGETFPS() {
    return TARGETFPS;
  }
  
  public long getINTERVAL() {
    return INTERVAL;
  }

  public int getFps() {
    return fps;
  }

  public void setFps(int fps) {
    this.fps = fps;
  }

  public GraphicsContext gc() {
    return gc;
  }
  
  private void setGc(GraphicsContext gc) {
    this.gc = gc;
  }

  public boolean isStarted() {
    return started;
  }

  public void setStarted(boolean started) {
    this.started = started;
  }

  public PlayableCharacter getPlayer() {
    return player;
  }

  public void setPlayer(PlayableCharacter player) {
    this.player = player;
  }

}

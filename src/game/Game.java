package game;

import engine.audio.AudioManager;
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
  // Nanoseconds between each frame.
  private int fps = 0;
  private GraphicsContext gc;
  private boolean started = false;
  
  public final int WINDOW_WIDTH = 960;
  public final int WINDOW_HEIGHT = 540;
  
  private Run run;
  private AudioManager audio = new AudioManager();
  
  public void initGame() {
    launch();
  }

  @Override
  public void start(Stage stage) {
    try {
      Canvas canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
      GraphicsContext gc = canvas.getGraphicsContext2D();
      setGc(gc);
      gc().setFill(Color.WHITE);
      gc().setStroke(Color.RED);
      gc().setLineWidth(2.0);
      Scene scene = new Scene(new Group(canvas));
      // Attach input handlers.
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
      // Dynamic millisecond interval depending on TARGETFPS. Yes it's pretty convoluted.
      double interval = (double) (Math.round(((double) 1 / getTARGETFPS()) * 1000000)) / 1000;
      KeyFrame keyframe = new KeyFrame(Duration.millis(interval), loop);
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

  public Run getRun() {
    return run;
  }

  public void setRun(Run run) {
    this.run = run;
  }

  public AudioManager getAudio() {
    return audio;
  }

  public void setAudio(AudioManager audio) {
    this.audio = audio;
  }

}

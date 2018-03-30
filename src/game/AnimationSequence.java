package game;

import javafx.scene.image.Image;

public class AnimationSequence {
  
  private Image[] sprites;
  private boolean animated = true;
  private final int FRAMEDURATION;
  private int frameTime = 0;
  private int currentFrame = 0;
  private boolean increase = true;
  private Direction dir;
  
  // New animation sequence using the given sprites, duration of each frame, and direction.
  public AnimationSequence(Image[] sprites, int frameDuration, Direction dir) {
    setSprites(sprites);
    FRAMEDURATION = frameDuration;
    setDir(dir);
  }
  
  // New animation sequence using the given sprites, default duration of each frame (15), and direction.
  public AnimationSequence(Image[] sprites, Direction dir) {
    setSprites(sprites);
    FRAMEDURATION = 15;
    setDir(dir);
  }
  
  // New animation sequence with no animations.
  public AnimationSequence(Image[] sprites) {
    setSprites(sprites);
    setAnimated(false);
    FRAMEDURATION = 0;
    setDir(Direction.UP);
  }
  
  /*
  private Sprite[] loadSprites(String[] sprites) {
    Sprite[] spriteList = new Sprite[sprites.length];
    for (int i = 0; i < spriteList.length; i++) {
      spriteList[i] = new Sprite(sprites[i], sprites[i]);
    }
    return spriteList;
  }
  */
  
  public Image getSprite() {
    return getSprites()[currentFrame];
  }
  
  public void advanceAnimationTimer() {
    setFrameTime(getFrameTime() + 1);
    if (getFrameTime() == fDuration()) {
      advanceAnimation();
    }
  }
  
  public void advanceAnimation() {
    currentFrame += increase ? 1 : -1;
    if (currentFrame == getSprites().length) {
      increase = false;
      currentFrame -= 2;
    }
    else if (currentFrame == -1) {
      increase = true;
      currentFrame += 2;
    }
    setFrameTime(0);
  }

  public Image[] getSprites() {
    return sprites;
  }

  public void setSprites(Image[] sprites) {
    this.sprites = sprites;
  }
  
  public boolean isAnimated() {
    return animated;
  }

  public void setAnimated(boolean animated) {
    this.animated = animated;
  }

  public int fDuration() {
    return FRAMEDURATION;
  }

  public int getFrameTime() {
    return frameTime;
  }

  public void setFrameTime(int frameTime) {
    this.frameTime = frameTime;
  }

  public Direction dir() {
    return dir;
  }

  public void setDir(Direction dir) {
    this.dir = dir;
  }

}

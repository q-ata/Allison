package game;

import javafx.scene.image.Image;

public class AnimationSequence {
  
  private Image[] sprites;
  private boolean animated = true;
  // How many frames each sprite should last before animating to the next one.
  private final int FRAMEDURATION;
  // How many frames have passed.
  private int frameTime = 0;
  // The index of the current sprite.
  private int currentFrame = 0;
  // If animating from left to right or vice versa of the array.
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
  
  public Image getSprite() {
    return getSprites()[currentFrame];
  }
  
  // Execute each frame the MapItem is being animated.
  public void advanceAnimationTimer() {
    setFrameTime(getFrameTime() + 1);
    if (getFrameTime() == fDuration()) {
      advanceAnimation();
    }
  }
  
  // Change the sprite to the next one in the animation.
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
  
  public void reset() {
    currentFrame = getSprites().length == 1 ? 0 : 1;
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

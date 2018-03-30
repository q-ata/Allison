package engine;

import game.SpriteLoader;
import javafx.scene.image.Image;

/**
 * An object that can be represented in world space.
 */
public class Sprite {
  
  private Image source;
  
  private Convex[] hitboxes;
  
  /**
   * Create a new sprite.
   * @param sources File sources to create images from.
   * @param hitboxes Convex shapes to use as hitboxes.
   * @param pos Position of sprite in world space.
   */
  public Sprite(String source, String hitboxes) {
    setSource(SpriteLoader.loadImage(source));
    setHitboxes(SpriteLoader.loadHitboxes(hitboxes));
  }

  /**
   * Get the current image source of this sprite.
   * @return The image source.
   */
  public Image getSource() {
    return source;
  }

  /**
   * Set the current image source of this sprite.
   * @param source The new image source.
   */
  public void setSource(Image source) {
    this.source = source;
  }

  /**
   * An array of shapes representing this sprite's hitbox.
   * @return The hitboxes.
   */
  public Convex[] getHitboxes() {
    return hitboxes;
  }

  /**
   * Set the shapes that represent this sprite's hitbox.
   * @param hitboxes The new hitboxes.
   */
  public void setHitboxes(Convex[] hitboxes) {
    this.hitboxes = hitboxes;
  }
  
  /**
   * Move the position of this sprite in the given direction vector.
   * @param direction A direction vector to move to.
   */
  public void move(Vec2 direction) {
    for (Convex box : getHitboxes()) {
      box.changePosition(direction);
    }
  }

}

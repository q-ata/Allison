package engine;

/**
 * Methods to be run in the game loop.
 */
public interface GameProcess {
  
  /**
   * Process user input.
   */
  public void input();
  
  /**
   * Process physics.
   */
  public void physics();

  /**
   * Process rendering.
   */
  public void render();

}

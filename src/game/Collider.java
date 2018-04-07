package game;

public interface Collider {
  
  public default void collisionProperties(final Game INSTANCE, MapItem collision) {
    return;
  }

}

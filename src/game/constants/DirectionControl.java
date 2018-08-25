package game.constants;

import java.util.Comparator;
import java.util.PriorityQueue;

import javafx.scene.input.KeyCode;

public class DirectionControl {
  
  public static final Keybind[] KEYBINDS = {new Keybind(KeyCode.W, Direction.UP),
      new Keybind(KeyCode.S, Direction.DOWN), new Keybind(KeyCode.A, Direction.LEFT), new Keybind(KeyCode.D, Direction.RIGHT)};
  // A maximum binary heap that compares the frame at which each direction key was last pressed.
  private static final Comparator<Keybind> comp = new Comparator<Keybind>() {
    @Override
    public int compare(Keybind k1, Keybind k2) {
      return k2.last - k1.last;
    }
  };
  public static final PriorityQueue<Keybind> QUEUE = new PriorityQueue<Keybind>(4, comp);

}

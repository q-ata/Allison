package engine;

/**
 * Approximate value of epsilon.
 */
public final class Epsilon {
  
  /**
   * Approximate epsilon.
   */
  public static final double EPSILON = calculate();
  
  private static double calculate() {
    double e = 0.5;
    while (1.0 + e > 1.0) {
      e *= 0.5;
    }
    return e;
  }

}

package game;

// I'm retarded LOL.
public class ArrayUtils {
  
  public static boolean arrayIncludes(int[] arr, int element) {
    for (int e : arr) {
      if (e == element) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean arrayIncludes(double[] arr, double element) {
    for (double e : arr) {
      if (e == element) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean arrayIncludes(float[] arr, float element) {
    for (float e : arr) {
      if (e == element) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean arrayIncludes(long[] arr, long element) {
    for (long e : arr) {
      if (e == element) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean arrayIncludes(byte[] arr, byte element) {
    for (byte e : arr) {
      if (e == element) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean arrayIncludes(String[] arr, String element) {
    for (String e : arr) {
      if (e.equals(element)) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean arrayIncludes(boolean[] arr, boolean element) {
    for (boolean e : arr) {
      if (e == element) {
        return true;
      }
    }
    return false;
  }

}

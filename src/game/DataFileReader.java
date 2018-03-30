package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class DataFileReader {
  
  private BufferedReader in;
  
  public DataFileReader(String path) {
    try {
      String currentDir = new File("").getAbsolutePath() + "/resources/";
      this.in = new BufferedReader(new FileReader(currentDir + path + ".dat"));
    }
    catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error creating DataFileReader for path: " + path);
    }
  }
  
  public String readLine() {
    try {
      String line = in.readLine();
      while (line.startsWith("#")) {
        line = in.readLine();
      }
      return line;
    }
    catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error reading line from DataFileReader.");
      return null;
    }
  }
  
  public void close() {
    try {
      in.close();
    }
    catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error closing reader from DataFileReader.");
    }
  }

}

package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import engine.Circle;
import engine.Convex;
import engine.Shape;
import engine.Vec2;
import javafx.scene.image.Image;

public class SpriteLoader {
  
  public static Convex[] loadHitboxes(String path) {
    try {
      String currentDir = new File("").getAbsolutePath() + "/resources/";
      // path/to/file.dat
      BufferedReader reader = new BufferedReader(new FileReader(currentDir + path + ".dat"));
      Convex[] boxes = new Convex[Integer.parseInt(reader.readLine().substring(1))];
      Vec2[] vertices;
      
      for (int boxCounter = 0; boxCounter < boxes.length; boxCounter++) {
        vertices = new Vec2[Integer.parseInt(reader.readLine().substring(1))];
        
        if (vertices.length != 0) {
          for (int vertexCounter = 0; vertexCounter < vertices.length; vertexCounter++) {
            String[] items = reader.readLine().split(" ");
            vertices[vertexCounter] = new Vec2(Integer.parseInt(items[0]), Integer.parseInt(items[1]));
          }
          
          boxes[boxCounter] = new Shape(vertices);
        }
        else {
          String[] items = reader.readLine().split(" ");
          double rad = Integer.parseInt(reader.readLine());
          
          boxes[boxCounter] = new Circle(new Vec2(Integer.parseInt(items[0]), Integer.parseInt(items[1])), rad);
        }
      }
      
      reader.close();
      return boxes;
        
    }
    catch(Exception e) {
      e.printStackTrace();
      System.out.println("\nError loading hitboxes from: " + path);
      return null;
    }
  }
  
  public static Image loadImage(String path) {
    // path/to/file
    return new Image("file:resources/" + path + ".png");
  }

}

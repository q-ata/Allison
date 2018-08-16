package game;

import java.awt.image.BufferedImage;

import game.constants.HUDSprites;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class TabUI {
  
  private Game instance;
  private Image minimap;
  private double mapLocation;
  
  public TabUI(Game instance) {
    this.instance = instance;
  }
  
  // TODO: Call this at beginning of every floor.
  public void updateMinimap() {
    // Constructs an awt render of the current minimap.
    Run run = instance.getRun();
    BufferedImage builder = new BufferedImage(run.getRooms()[0].length * 32, run.getRooms().length * 32, BufferedImage.TYPE_INT_ARGB);
    int x = -32;
    int y = 0;
    for (Room[] row : run.getRooms()) {
      for (Room room : row) {
        x += 32;
        if (room == null) {
          continue;
        }
        java.awt.Image image;
        if (run.getCurrentPos().equals(room.coord())) {
          image = HUDSprites.CURRENT_TILE;
        }
        else if (room.getID() == -1) {
          image = HUDSprites.BOSS_TILE;
        }
        else if (room.getID() == -2) {
          image = HUDSprites.SHOP_TILE;
        }
        else if (room.getID() == -3) {
          image = HUDSprites.MYSTERY_TILE;
        }
        else {
          image = HUDSprites.MINIMAP_TILE;
        }
        builder.createGraphics().drawImage(image, x, y, null);
      }
      x = -32;
      y += 32;
    }
    
    // Convert to JavaFX image and set.
    minimap = SwingFXUtils.toFXImage(builder, null);
    mapLocation = 960 - minimap.getWidth();
  }
  
  public void render() {
    // Darken screen. The overlay is simply a translucent black.
    instance.gc().drawImage(HUDSprites.TAB_OVERLAY, 0, 0);
    // Render minimap.
    instance.gc().drawImage(minimap, mapLocation, 0);
  }

}

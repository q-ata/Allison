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
  
  public void updateMinimap() {
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
        builder.createGraphics().drawImage(HUDSprites.MINIMAP_TILE, x, y, null);
      }
      x = -32;
      y += 32;
    }
    
    minimap = SwingFXUtils.toFXImage(builder, null);
    mapLocation = 960 - minimap.getWidth();
  }
  
  public void render() {
    instance.gc().drawImage(HUDSprites.TAB_OVERLAY, 0, 0);
    instance.gc().drawImage(minimap, mapLocation, 0);
  }

}

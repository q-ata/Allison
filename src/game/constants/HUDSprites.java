package game.constants;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.scene.image.Image;

public final class HUDSprites {
  
  public static final Image TAB_OVERLAY = new Image("file:resources/ui/overlay.png");
  public static final java.awt.Image MINIMAP_TILE = populateMinimapTile();
  
  public static final Image HUD_BASE = new Image("file:resources/misc/hud.png");
  public static final Image HUD_HEALTH = new Image("file:resources/misc/full_health.png");
  public static final Image HUD_MANA = new Image("file:resources/misc/full_mana.png");
  
  private static final java.awt.Image populateMinimapTile() {
    try {
      return ImageIO.read(new File("./resources/ui/minimap_tile.png"));
    }
    catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

}

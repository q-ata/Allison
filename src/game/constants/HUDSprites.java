package game.constants;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.scene.image.Image;

public final class HUDSprites {
  
  public static final Image TAB_OVERLAY = new Image("file:resources/ui/overlay.png");
  public static final java.awt.Image MINIMAP_TILE = populateMinimapTile();
  public static final java.awt.Image CURRENT_TILE = populateCurrentTile();
  public static final java.awt.Image BOSS_TILE = populateBossTile();
  public static final java.awt.Image MYSTERY_TILE = populateMysteryTile();
  public static final java.awt.Image SHOP_TILE = populateShopTile();
  
  public static final Image HUD_BASE = new Image("file:resources/misc/hud.png");
  public static final Image HUD_HEALTH = new Image("file:resources/misc/full_health.png");
  public static final Image HUD_MANA_A = new Image("file:resources/misc/mana_a.png");
  public static final Image HUD_MANA_B = new Image("file:resources/misc/mana_b.png");
  
  private static final java.awt.Image populateMinimapTile() {
    try {
      return ImageIO.read(new File("./resources/ui/minimap_tile.png"));
    }
    catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
  
  private static final java.awt.Image populateCurrentTile() {
    try {
      return ImageIO.read(new File("./resources/ui/current_tile.png"));
    }
    catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
  
  private static final java.awt.Image populateBossTile() {
    try {
      return ImageIO.read(new File("./resources/ui/boss_tile.png"));
    }
    catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
  
  private static final java.awt.Image populateMysteryTile() {
    try {
      return ImageIO.read(new File("./resources/ui/mystery_tile.png"));
    }
    catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
  
  private static final java.awt.Image populateShopTile() {
    try {
      return ImageIO.read(new File("./resources/ui/shop_tile.png"));
    }
    catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

}

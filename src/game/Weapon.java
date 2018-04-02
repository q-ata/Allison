package game;

import engine.Vec2;
import javafx.scene.image.Image;

public abstract class Weapon {
  
  private int mana = 100;
  private double multiplier = 1.0;
  private Image hudSprite;
  private Vec2 hudOffset = new Vec2();
  
  // TODO: Finish weapon super class (sprite data, projectile data and probably more).
  public Weapon(String hudSprite) {
    setHudSprite(new Image("file:resources/weapons/" + hudSprite + ".png"));
  }
  
  public Weapon(String hudSprite, String hudSpriteOffset) {
    setHudSprite(new Image("file:resources/weapons/" + hudSprite + ".png"));
    setHudOffset(DataLoader.loadHudWeaponSpriteOffset(hudSpriteOffset));
  }

  public int getMana() {
    return mana;
  }

  public void setMana(int mana) {
    this.mana = mana;
  }

  public double getMultiplier() {
    return multiplier;
  }

  public void setMultiplier(double multiplier) {
    this.multiplier = multiplier;
  }

  public Image getHudSprite() {
    return hudSprite;
  }

  public void setHudSprite(Image hudSprite) {
    this.hudSprite = hudSprite;
  }

  public Vec2 getHudOffset() {
    return hudOffset;
  }

  public void setHudOffset(Vec2 hudOffset) {
    this.hudOffset = hudOffset;
  }
  
}

package game;

import engine.Vec2;
import javafx.scene.image.Image;

public abstract class Weapon {
  
  // Sprite to use for HUD.
  private Image hudSprite;
  // How much to offset when displaying in HUD.
  private Vec2 hudOffset = new Vec2();
  // Projectile data.
  // Cooldown.
  private int cooldown = 30;
  // Projectile data.
  private ProjectileData data;
  
  private ProjectileSequence sequence;
  
  public Weapon(String hudSprite, String hudSpriteOffset, String dataPath, ProjectileSequence sequence) {
    setHudSprite(new Image("file:resources/weapons/" + hudSprite + ".png"));
    setHudOffset(DataLoader.loadHudWeaponSpriteOffset(hudSpriteOffset));
    setSequence(sequence);
    setData(DataLoader.loadProjectileData(dataPath));
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

  public int getCooldown() {
    return cooldown;
  }

  public void setCooldown(int cooldown) {
    this.cooldown = cooldown;
  }

  public ProjectileSequence getSequence() {
    return sequence;
  }

  public void setSequence(ProjectileSequence sequence) {
    this.sequence = sequence;
  }

  public ProjectileData getData() {
    return data;
  }

  public void setData(ProjectileData data) {
    this.data = data;
  }
  
}

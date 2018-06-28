package game;

public class ProjectileData {

  // fire rate boost.
  // fire rate multiplier.
  // projectile speed boost.
  // projectile speed multiplier.
  // damage increase.
  // damage multiplier.
  // range increase.
  // range multiplier.
  // size increase.
  // size multiplier.
  // TODO: All stats besides size are not being used.
  
  private int rateBoost = 0;
  private double rateMulti = 1.0;
  private double speedBoost = 0.0;
  private double speedMulti = 1.0;
  private int damageBoost = 0;
  private double damageMulti = 1.0;
  private double rangeBoost = 0.0;
  private double rangeMulti = 1.0;
  private int scaleBoost = 0;
  private double scaleMulti = 1.0;
  
  private ProjectileSprites sprites;
  
  public ProjectileData(int rateBoost, double rateMulti, double speedBoost, double speedMulti,
      int damageBoost, double damageMulti, double rangeBoost, double rangeMulti, int scaleBoost, double scaleMulti, ProjectileSprites sprites) {
    setRateBoost(rateBoost);
    setRateMulti(rateMulti);
    setSpeedBoost(speedBoost);
    setSpeedMulti(speedMulti);
    setDamageBoost(damageBoost);
    setDamageMulti(damageMulti);
    setRangeBoost(rangeBoost);
    setRangeMulti(rangeMulti);
    setSprites(sprites);
    setScaleBoost(scaleBoost);
    setScaleMulti(scaleMulti);
  }
  
  public int rateBoost() {
    return rateBoost;
  }
  public void setRateBoost(int rateBoost) {
    this.rateBoost = rateBoost;
  }
  public double rateMulti() {
    return rateMulti;
  }
  public void setRateMulti(double rateMulti) {
    this.rateMulti = rateMulti;
  }
  public double speedBoost() {
    return speedBoost;
  }
  public void setSpeedBoost(double speedBoost) {
    this.speedBoost = speedBoost;
  }
  public double speedMulti() {
    return speedMulti;
  }
  public void setSpeedMulti(double speedMulti) {
    this.speedMulti = speedMulti;
  }
  public int damageBoost() {
    return damageBoost;
  }
  public void setDamageBoost(int damageBoost) {
    this.damageBoost = damageBoost;
  }
  public double damageMulti() {
    return damageMulti;
  }
  public void setDamageMulti(double damageMulti) {
    this.damageMulti = damageMulti;
  }
  public double rangeBoost() {
    return rangeBoost;
  }
  public void setRangeBoost(double rangeBoost) {
    this.rangeBoost = rangeBoost;
  }
  public double rangeMulti() {
    return rangeMulti;
  }
  public void setRangeMulti(double rangeMulti) {
    this.rangeMulti = rangeMulti;
  }
  
  public int scaleBoost() {
    return scaleBoost;
  }

  public void setScaleBoost(int scaleBoost) {
    this.scaleBoost = scaleBoost;
  }
  
  public double scaleMulti() {
    return scaleMulti;
  }

  public void setScaleMulti(double scaleMulti) {
    this.scaleMulti = scaleMulti;
  }

  public ProjectileSprites sprites() {
    return sprites;
  }

  public void setSprites(ProjectileSprites sprites) {
    this.sprites = sprites;
  }
  
}

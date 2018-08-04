package game;

import java.util.ArrayList;
import java.util.List;

import game.structures.Entity;
import game.structures.MapItem;
import game.structures.Projectile;

public class MapItemStore {

  private List<MapItem> all = new ArrayList<MapItem>();
  private List<MapItem> blocks = new ArrayList<MapItem>();
  private List<Entity> entities = new ArrayList<Entity>();
  private List<Projectile> projectiles = new ArrayList<Projectile>();
  
  public List<MapItem> all() {
    return all;
  }
  
  public List<MapItem> blocks() {
    return blocks;
  }
  
  public List<Entity> entities() {
    return entities;
  }
  
  public List<Projectile> projectiles() {
    return projectiles;
  }
  
  public void addBlock(MapItem block) {
    all().add(block);
    blocks().add(block);
  }
  
  public void removeBlock(MapItem block) {
    all().remove(block);
    blocks().remove(block);
  }
  
  public void addEntity(Entity entity) {
    all().add(entity);
    entities().add(entity);
  }
  
  public void removeEntity(Entity entity) {
    all().remove(entity);
    entities().remove(entity);
  }
  
  public void addProj(Projectile proj) {
    all().add(proj);
    projectiles().add(proj);
  }
  
  public void removeProj(Projectile proj) {
    all().remove(proj);
    projectiles().remove(proj);
  }
  
}

package game.abilities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game.AnimationSequence;
import game.CollisionDetector;
import game.DataLoader;
import game.Game;
import game.PlayableCharacter;
import game.constants.Direction;
import game.structures.Ability;
import game.structures.MapItem;

// Grants temporary movement speed, invulnerability and noclip.
public class Blink extends Ability {
  
  private Map<Direction, AnimationSequence> originalSpriteSet;
  private double originalMovement;
  private Map<Direction, AnimationSequence> activeSpriteSet = new HashMap<Direction, AnimationSequence>();
  
  private int passed = 0;
  private final int DURATION = 50;

  public Blink(Game instance) {
    super(100, 50);
    // Store original sprites and movement speed then load the new sprite set for while the ability is active.
    originalSpriteSet = instance.getRun().getPlayer().getSpriteSet();
    originalMovement = instance.getRun().getPlayer().getMoveSpeed();
    AnimationSequence[] sequences = DataLoader.loadSequences("ability/blink/sprite_map");
    for (AnimationSequence as : sequences) {
      activeSpriteSet.put(as.dir(), as);
    }
  }
  
  @Override
  public void precast(Game instance) {
    // Use new sprite set and change movement speed, allow clipping into map items.
    PlayableCharacter player = instance.getRun().getPlayer();
    player.setSpriteSet(activeSpriteSet);
    player.setSolid(false);
    player.setMoveSpeed(10);
    player.setResistance(0);
  }
  
  @Override
  public void cast(Game instance) {
    // Stop ability cast after number of frames specified in DURATION.
    if (++passed == DURATION) {
      passed = 0;
      end(instance);
    }
  }
  
  @Override
  public void aftercast(Game instance) {
    PlayableCharacter player = instance.getRun().getPlayer();
    player.setSpriteSet(originalSpriteSet);
    // Shift the player in their last direction until they are no longer intersecting with any map items.
    while (colliding(instance)) {
      player.move(MapItem.calculateVelocity(player.dir(), 1));
    }
    // Reset player state.
    player.setSolid(true);
    player.setMoveSpeed(originalMovement);
    player.setResistance(player.getBaseResistance());
  }
  
  // Check if the player is current colliding with any items.
  private boolean colliding(Game instance) {
    MapItem player = instance.getRun().getPlayer();
    List<MapItem> blocks = instance.getRun().getCurrentRoom().getItems().blocks();
    blocks.addAll(instance.getRun().getCurrentRoom().getItems().entities());
    for (MapItem block : blocks) {
      if (CollisionDetector.hitboxIntersects(player.getHitbox(), block.getHitbox())) {
        return true;
      }
    }
    return false;
  }

}

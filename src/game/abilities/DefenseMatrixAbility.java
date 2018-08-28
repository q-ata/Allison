package game.abilities;

import java.util.HashMap;
import java.util.Map;

import engine.KeyboardInputs;
import engine.Vec2;
import game.Game;
import game.PlayableCharacter;
import game.blocks.DefenseMatrix;
import game.blocks.DefenseMatrixDown;
import game.blocks.DefenseMatrixLeft;
import game.blocks.DefenseMatrixRight;
import game.blocks.DefenseMatrixUp;
import game.constants.Direction;
import game.structures.Ability;

public class DefenseMatrixAbility extends Ability {
  
  private static final Map<Direction, Vec2> SHIFT_MAP = new HashMap<Direction, Vec2>();
  private static final Map<String, Direction> ORIENTATION_MAP = new HashMap<String, Direction>();
  
  private DefenseMatrix dm;
  
  private final int MIN_THRESHOLD = 36;
  private int currentUse = 0;
  
  public DefenseMatrixAbility(Game instance) {
    super(180, 36);
    // TODO: These values probably need to be polished.
    // Map defense matrix direction to Direction enum and position shift.
    SHIFT_MAP.put(Direction.UP, new Vec2(4, 24));
    SHIFT_MAP.put(Direction.DOWN, new Vec2(4, -69));
    SHIFT_MAP.put(Direction.LEFT, new Vec2(18, 4));
    SHIFT_MAP.put(Direction.RIGHT, new Vec2(-61, 4));
    
    ORIENTATION_MAP.put("DefenseMatrixUp", Direction.UP);
    ORIENTATION_MAP.put("DefenseMatrixDown", Direction.DOWN);
    ORIENTATION_MAP.put("DefenseMatrixLeft", Direction.LEFT);
    ORIENTATION_MAP.put("DefenseMatrixRight", Direction.RIGHT);
  }
  
  @Override
  public void precast(Game instance) {
    addCharge(MIN_THRESHOLD);
    if (isActive()) {
      return;
    }
    PlayableCharacter player = instance.getRun().getPlayer();
    DefenseMatrix dm = null;
    // Create defense matrix of correct orientation.
    switch (player.dir()) {
    case UP:
      dm = new DefenseMatrixUp();
      break;
    case DOWN:
      dm = new DefenseMatrixDown();
      break;
    case LEFT:
      dm = new DefenseMatrixLeft();
      break;
    case RIGHT:
      dm = new DefenseMatrixRight();
      break;
    default:
      dm = new DefenseMatrixUp();
      break;
    }
    instance.getRun().getCurrentRoom().getItems().addBlock(dm);
    this.dm = dm;
  }
  
  @Override
  public void cast(Game instance) {
    // Move the defense matrix to follow the player.
    Vec2 pos = instance.getRun().getPlayer().pos().clone().sub(SHIFT_MAP.get(ORIENTATION_MAP.get(dm.getClass().getSimpleName())));
    dm.move(pos.sub(dm.pos()));
    // Drains 1 charge per frame.
    addCharge(-1);
    // Minimum cast duration of 36 frames, end the ability after that or until key is released, or until no charge is left.
    if (++currentUse >= MIN_THRESHOLD && !KeyboardInputs.KEYMAP.get(getBind()) || getCharge() == 0) {
      end(instance);
    }
  }
  
  @Override
  public void aftercast(Game instance) {
    // Remove the defense matrix from the game.
    instance.getRun().getCurrentRoom().getItems().removeBlock(dm);
    dm = null;
    currentUse = 0;
  }

}

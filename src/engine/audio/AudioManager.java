package engine.audio;

import java.util.ArrayList;
import java.util.List;

public class AudioManager {
  
  private List<ClipManager> clips = new ArrayList<ClipManager>();
  private int counter = 0;
  private String prefix = "./";
  private String suffix = ".wav";
  
  public AudioManager(String prefix, String suffix) {
    if (prefix != null) {
      setPrefix(prefix);
    }
    if (suffix != null) {
      setSuffix(suffix);
    }
  }
  
  public AudioManager() {
    
  }
  
  public void play(int index) {
    clips.get(index).play();
  }
  
  public int register(String path) {
    clips.add(new ClipManager(prefix + path + suffix));
    return counter++;
  }
  
  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }
  
  public void setSuffix(String suffix) {
    this.suffix = suffix;
  }

}

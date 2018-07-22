package engine.audio;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class ClipManager {
  
  List<Clip> instances = new ArrayList<Clip>();
  int counter = 0;
  File source;
  
  public ClipManager(String source) {
    
    this.source = new File(source);
    
    createInstance();
    
  }
  
  private void createInstance() {
    try {
      Clip clip = AudioSystem.getClip();
      clip.open(AudioSystem.getAudioInputStream(source));
      instances.add(clip);
    } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
      e.printStackTrace();
    }
  }
  
  public void play() {
    Clip instance = instances.get(counter++);
    instance.start();
    instance.addLineListener(new LineListener() {
      @Override
      public void update(LineEvent event) {
        if (event.getType() == LineEvent.Type.STOP) {
          instances.remove(--counter);
        }
      }
    });
    createInstance();
  }
  
  public void stop() {
    stop(0);
  }
  
  public void stop(int index) {
    Clip instance = instances.get(index);
    instance.stop();
    instance.close();
  }

}

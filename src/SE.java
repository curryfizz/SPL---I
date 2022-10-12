package src;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;

public class SE {
    Clip clip;
    public void  setFile(URL name){
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(name);
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public  void play(URL name){
        clip.setFramePosition(0);
        clip.start();
    }

    public void loop(URL name)
    {
        clip.loop(clip.LOOP_CONTINUOUSLY);
    }

    public  void  stop(URL name){
        clip.stop();
    }
}

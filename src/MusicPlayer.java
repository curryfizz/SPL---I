package src;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class MusicPlayer {
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

    public  void playMusic(URL url)
    {
        this.setFile(url);
        this.play(url);
        this.loop(url);
    }
}

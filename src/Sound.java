package src;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sound {
    Clip clip;
    float PreviousVolume = 0;
    float currentVolume = -17;
    boolean mute =false;
    FloatControl fc;
    public  void setFile(URL url){

        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(url);
            clip= AudioSystem.getClip();
            clip.open(sound);
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }


    public  void  play(URL url)
    {
        clip.setFramePosition(0);
        clip.start();
    }


    public void loop(URL url)
    {
        clip.loop(clip.LOOP_CONTINUOUSLY);
    }

    public  void  stop(URL url)
    {
        clip.stop();
    }

    public void volumeUp(){
currentVolume +=1.0f;
System.out.println("current Volume: " + currentVolume);
if(currentVolume >6.0f)
{
    currentVolume= 6.0f;
}
fc.setValue(currentVolume);
    }
    public void volumeDown(){

        currentVolume -=1.0f;
        if(currentVolume < -80.0f)
        {
            currentVolume= -80.0f;
        }
        fc.setValue(currentVolume);
    }
    public void volumeMusic(){
if(mute == false)
{
    PreviousVolume = currentVolume;
    currentVolume =-80.0f;
    fc.setValue(currentVolume);
    mute= true;
}
else  if(mute==true) {
    currentVolume = PreviousVolume;
    fc.setValue(currentVolume);
    mute= false;
}
    }

}

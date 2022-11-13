package src;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Sound {
    Clip clip;

    File file[]= new File[30];
    URL soundURL[] = new URL[30];

    float previousVolume ;

    float currentVolume ;

    FloatControl fc;
    boolean mute;
    public Sound(){

        previousVolume=0;
        currentVolume=-17;
        mute=false;
        file[0] = new File("C:\\Users\\nawsh\\Desktop\\SPL---I\\background_music\\levelOneBackground_v2.wav");



    }

    public void setFile(int i){
        try{
            if(file[i].exists()) {
                AudioInputStream ais = AudioSystem.getAudioInputStream(file[i]);
                clip = AudioSystem.getClip();
                clip.open(ais);
                clip.setFramePosition(0);
                fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            }else{
                throw new Exception("File not found!");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void play(){
        clip.start();
    }

    public void stop(){

    }



//    float PreviousVolume = 0;
//    float currentVolume = -17;
//    boolean mute =false;
//    FloatControl fc;
//    public  void setFile(URL url){
//
//        try {
//            fc = null;
//            AudioInputStream sound = AudioSystem.getAudioInputStream(url);
//            clip= AudioSystem.getClip();
//            clip.open(sound);
//            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (UnsupportedAudioFileException e) {
//            throw new RuntimeException(e);
//        } catch (LineUnavailableException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    public  void  play(URL url)
//    {
//        clip.setFramePosition(0);
//        clip.start();
//    }
//
//
//    public void loop(URL url)
//    {
//        clip.loop(clip.LOOP_CONTINUOUSLY);
//    }
//
//    public  void  stop(URL url)
//    {
//        clip.stop();
//    }

    public void volumeUp(){
        currentVolume +=1.0f;
        System.out.println("current Volume: " + currentVolume);
        if(currentVolume >6.0f) {
            currentVolume = 6.0f;
            //6 is max gain of floatcontrol
        }

        fc.setValue(currentVolume);

    }
//    }
    public void volumeDown(){

        currentVolume -=1.0f;
        if(currentVolume < -80.0f)
        {
            currentVolume= -80.0f;
            //lowest float control value or error
        }
        fc.setValue(currentVolume);
    }

    public void volumeMute(){
        if(mute==false){
            previousVolume = currentVolume;
            currentVolume=-80.0f;
            fc.setValue(currentVolume);
            mute=true;
        }else if(mute==true){
            currentVolume = previousVolume;
            fc.setValue(currentVolume);
            mute=false;
        }
    }
//    public void volumeMusic(){
//if(mute == false)
//{
//    PreviousVolume = currentVolume;
//    currentVolume =-80.0f;
//    fc.setValue(currentVolume);
//    mute= true;
//}
//else  if(mute==true) {
//    currentVolume = PreviousVolume;
//    fc.setValue(currentVolume);
//    mute= false;
//}
//    }

}

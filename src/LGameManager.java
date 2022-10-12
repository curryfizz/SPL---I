package src;

import java.awt.*;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class LGameManager {

    //sound
    public URL fieldMusic = getClass().getClassLoader().getResource("images/bgmusic.wav");
    public URL currentMusic;
    music music = new music();
    SE se = new SE();



    LUI lui = new LUI(this);
//    public  static  void  main(String[] args)
//    {
//        new LGameManager();
//    }
    public  LGameManager(){

        currentMusic = fieldMusic;
        playMusic(currentMusic);
    }




    public  void playSE(URL url)
    {
        se.setFile(url);
        se.play(url);
    }
    public  void playMusic(URL url)
    {
        music.setFile(url);
        music.play(url);
    }
    public void stopMusic(URL url)
    {
        music.stop(url);
    }

}

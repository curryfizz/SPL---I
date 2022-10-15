package src;

import java.net.URL;


public class LGameManager {


    //sound
    public URL fieldMusic = getClass().getClassLoader().getResource("images/bgmusic.wav");
    public URL currentMusic;
    src.MusicPlayer MusicPlayer = new MusicPlayer();
//    SE se = new SE();
    LUI lui;
    public  static  void  main(String[] args)
    {
        new LGameManager();
   }
    public  LGameManager(){
//        currentMusic = fieldMusic;
//        music.playMusic(currentMusic);
        lui = new LUI(this);
        MusicPlayer.playMusic(fieldMusic);

    }




//    public  void playSE(URL url)
//    {
//        se.setFile(url);
//        se.play(url);
//    }

    public void stopMusic(URL url)
    {
        MusicPlayer.stop(url);
    }


}

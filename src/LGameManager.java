package src;

import javax.swing.*;
import java.awt.*;
import java.net.URL;


public class LGameManager {

/*
    //sound
    public URL fieldMusic = getClass().getClassLoader().getResource("images/bgmusic.wav");
    public URL currentMusic;
    src.MusicPlayer MusicPlayer = new MusicPlayer();
//    SE se = new SE();

 */
    LUI lui;
    public  static  void  main(String[] args)
    {
        new LGameManager();
   }
    public  LGameManager(){
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setUndecorated(true);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.setVisible(true);
        jFrame.setBackground(Color.white);
        DeviceScreenInformation deviceScreenInformation = new DeviceScreenInformation();
        FontInfo fontInfo = new FontInfo();
        DormRoomScene dormRoomScene = new DormRoomScene(jFrame,deviceScreenInformation,fontInfo);
        MusicSlider musicSlider = new MusicSlider(jFrame,dormRoomScene, deviceScreenInformation,fontInfo);
        dormRoomScene.add(musicSlider);


    }







//    public  void playSE(URL url)
//    {
//        se.setFile(url);
//        se.play(url);
//    }

  /*  public void stopMusic(URL url)
    {
        MusicPlayer.stop(url);
    }

   */


}

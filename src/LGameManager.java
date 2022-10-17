package src;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.net.URL;


public class LGameManager {

    JSlider slider;











//    SE se = new SE();


    LUI lui;
    Sound sound = new Sound();
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

                URL url = getClass().getResource("D:\\SPL---I\\images\\bgmusic.wav");
        playmusic(url);

        DeviceScreenInformation deviceScreenInformation = new DeviceScreenInformation();
        FontInfo fontInfo = new FontInfo();
        DormRoomScene dormRoomScene = new DormRoomScene(jFrame,deviceScreenInformation,fontInfo);
        MusicSlider musicSlider = new MusicSlider(jFrame,dormRoomScene, deviceScreenInformation,fontInfo);
 musicSlider.addChangeListener(new ChangeListener() {
     @Override
     public void stateChanged(ChangeEvent e) {
         sound.currentVolume = musicSlider.getValue();
         sound.fc.setValue(sound.currentVolume);
     }
 });





    }



    public void playmusic(URL url)
    {
sound.setFile(url);
sound.play(url);
sound.loop(url);
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

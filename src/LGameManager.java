package src;

import src.setup.DeviceInformation;
import src.setup.FontInfo;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        jFrame.setTitle("Lost Treasures");
        jFrame.setName("Lost Tresures Game");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setUndecorated(true);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.setVisible(true);
        jFrame.setBackground(Color.white);

        jFrame.setLayout(new GridLayout(1,3));

        JButton volumeUpB = new JButton("Volume UP");
        volumeUpB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sound.volumeUp();
            }
        });



        DeviceInformation deviceInformation = new DeviceInformation();
        FontInfo fontInfo = new FontInfo();
       // DormRoomLevelPanel dormRoomScene = new DormRoomLevelPanel(jFrame, deviceInformation,fontInfo);

      //  MusicSlider musicSlider = new MusicSlider(jFrame,dormRoomScene, deviceInformation,fontInfo);

        jFrame.add(volumeUpB);

       // slider = new JSlider(-80,7);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                sound.currentVolume = slider.getValue();
                sound.fc.setValue(sound.currentVolume);
            }
        });
        jFrame.add(slider);
        jFrame.pack();
        jFrame.setVisible(true);


        URL soundURl = getClass().getResource("/background_music/bgmusic.wav");

        playMusic(soundURl);
    }
    public void playMusic(URL url)
    {
        /** someone changed the parameters to int so these don't work no more*/
//        sound.setFile(url);
//        sound.play(url);
    }
}




















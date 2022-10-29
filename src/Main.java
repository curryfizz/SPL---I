package src;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Main {
    JSlider slider;
    Sound sound = new Sound();
    public  static void main(String[] args){
        new Main();
    }
    public  Main(){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new GridLayout(1,3));

        JButton volumeUpB = new JButton("Volume UP");
        volumeUpB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sound.volumeUp();
            }
        });


        window.add(volumeUpB);
        slider = new JSlider(-40,7);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                sound.currentVolume = slider.getValue();
                if(sound.currentVolume == -40){
                    sound.currentVolume = -80;
                }
                sound.fc.setValue(sound.currentVolume);
            }
        });
        JPanel dummy = new JPanel();
        dummy.setBounds(new DeviceInformation().graphicsEnvironment.getMaximumWindowBounds());
        dummy.setBackground(Color.BLACK);
        dummy.add(slider);
        window.add(dummy);
        window.pack();
        window.setVisible(true);

        URL soundURl = getClass().getResource("/background_music/bgmusic.wav");

        playMusic(soundURl);
    }
    public void playMusic(URL url)
    {
        sound.setFile(url);
        sound.play(url);
    }
}

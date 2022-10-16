package src;

import javax.swing.*;
import java.awt.*;

public class MusicSlider extends JSlider {

    JFrame jFrame;
    JPanel backGroundPanel;
    DeviceScreenInformation deviceScreenInformation;
    FontInfo fontInfo;

    public MusicSlider(JFrame jFrame, JPanel backGroundPanel, DeviceScreenInformation deviceScreenInformation, FontInfo fontInfo) {
        this.jFrame = jFrame;
        this.backGroundPanel = backGroundPanel;
        this.deviceScreenInformation = deviceScreenInformation;
        this.fontInfo = fontInfo;
        SetupMusicSlider();
    }


    public void SetupMusicSlider(){

        this.setBounds(500,5, 100, 50);
        this.setBackground(Color.gray);
        this.setForeground(Color.black);
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.white, 2),
                BorderFactory.createEmptyBorder(0,20,0,20)));
        this.setOpaque(true);

        backGroundPanel.repaint();
        backGroundPanel.revalidate();
        backGroundPanel.add(this);
    }



    }

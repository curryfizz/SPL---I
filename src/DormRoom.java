package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DormRoom extends JPanel {
    JFrame jFrame;
    JLabel gameTitle;
    JButton startGameButton;
    DeviceScreenInformation deviceInfo;
    FontInfo fontInfo;
    CloseButton closeButton;
    public DormRoom(JFrame jFrame, DeviceScreenInformation deviceScreenInformation, FontInfo fontInfo){
        this.jFrame = jFrame;
        this.deviceInfo = deviceScreenInformation;
        this.fontInfo = fontInfo;
        createBackgroundPanel();
        this.add(new CloseButton(deviceScreenInformation,"x",jFrame,fontInfo));
        GameTimer gameTimer = new GameTimer(jFrame,this, deviceScreenInformation, fontInfo);
        jFrame.add(this);
//        addCustomWindowCloseButton(jFrame);
    }

    public void createBackgroundPanel(){
        this.setLayout(null);
        this.setPreferredSize(new Dimension(deviceInfo.screenWidth, deviceInfo.screenHeight));
        this.setBackground(Color.decode("#14171C"));
        this.setForeground(Color.decode("#C64C1D"));
        this.setOpaque(true);
    }

    public void addCustomWindowCloseButton(JFrame jFrame){

        closeButton = new CloseButton(deviceInfo,"X",jFrame, fontInfo);
        this.add(closeButton);
        jFrame.repaint();
    }
}

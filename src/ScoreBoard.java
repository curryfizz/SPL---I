package src;

import javax.swing.*;
import java.awt.*;

public class ScoreBoard extends JLabel{
    JFrame jFrame;
    JPanel backGroundPanel;
    DeviceScreenInformation deviceInfo;
    FontInfo fontInfo;

    public ScoreBoard(JFrame jFrame, JPanel backGroundPanel, DeviceScreenInformation deviceScreenInformation, FontInfo fontInfo) {
        this.jFrame = jFrame;
        this.backGroundPanel = backGroundPanel;
        this.deviceInfo = deviceScreenInformation;
        this.fontInfo = fontInfo;
        SetupScoreBoard();
    }

    public void SetupScoreBoard(){
        this.setBounds(150,10, 200, 60);
        this.setBackground(Color.darkGray);
        this.setForeground(Color.white);
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.lightGray, 2),
                BorderFactory.createEmptyBorder(0,20,0,20)));
        this.setOpaque(true);
        this.setFont(fontInfo.getResizedFont(29f));

        this.setText("Idk ;-;");
        backGroundPanel.repaint();
        backGroundPanel.revalidate();
        backGroundPanel.add(this);
    }


}

package src;

import javax.swing.*;
import java.awt.*;

public class PlayerScoreBoard extends JLabel{
    JFrame jFrame;
    JPanel backGroundPanel;
    DeviceScreenInformation deviceInfo;
    FontInfo fontInfo;

    public PlayerScoreBoard(JFrame jFrame, JPanel backGroundPanel, DeviceScreenInformation deviceScreenInformation, FontInfo fontInfo) {
        super("Current Score: 0", SwingConstants.CENTER);
        this.setVerticalTextPosition(SwingConstants.BOTTOM); //kaj korena
        this.jFrame = jFrame;
        this.backGroundPanel = backGroundPanel;
        this.deviceInfo = deviceScreenInformation;
        this.fontInfo = fontInfo;
        SetupScoreBoard();
    }

    public void SetupScoreBoard(){
        this.setBounds(5,5, 250, 50);
        this.setBackground(Color.decode("#14171C"));
        this.setForeground(Color.decode("#FFFF9F"));
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.white, 2),
                BorderFactory.createEmptyBorder(0,20,0,20)));
        this.setOpaque(true);
        this.setFont(fontInfo.getResizedFont(35f));

        backGroundPanel.repaint();
        backGroundPanel.revalidate();
        backGroundPanel.add(this);
    }


}

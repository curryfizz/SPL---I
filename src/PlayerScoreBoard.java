package src;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
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
        this.setBounds(0,0, 250, 50);
        this.setBackground(Color.decode("#14171C"));
        this.setForeground(Color.decode("#FFD700"));
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.decode("#14171C"),5), BorderFactory.createLineBorder(Color.decode("#FFD700"),2)));
        this.setOpaque(true);
        this.setFont(fontInfo.getResizedFont(35f));

        backGroundPanel.repaint();
        backGroundPanel.revalidate();
        backGroundPanel.add(this);
    }


}

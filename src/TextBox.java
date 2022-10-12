package src;

import javax.swing.*;
import java.awt.*;

public class TextBox extends JLabel {
    JFrame jFrame;
    JPanel backGroundPanel;
    DeviceScreenInformation deviceInfo;
    FontInfo fontInfo;

    public TextBox(JFrame jFrame, JPanel backGroundPanel, DeviceScreenInformation deviceScreenInformation, FontInfo fontInfo) {
        this.jFrame = jFrame;
        this.backGroundPanel = backGroundPanel;
        this.deviceInfo = deviceScreenInformation;
        this.fontInfo = fontInfo;
        SetupTextBoxLabel();
    }

    public void SetupTextBoxLabel(){
        this.setBounds(deviceInfo.screenWidth/25,deviceInfo.screenHeight*2/3, deviceInfo.screenWidth*23/25, deviceInfo.screenHeight/3 -50);
        this.setBackground(Color.darkGray);
        this.setForeground(Color.white);
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.lightGray, 2),
                BorderFactory.createEmptyBorder(0,20,0,20)));
        this.setOpaque(true);
        this.setFont(fontInfo.getResizedFont(29f));

        this.setText("Idk man");
        backGroundPanel.repaint();
        backGroundPanel.revalidate();
        backGroundPanel.add(this);
    }
}

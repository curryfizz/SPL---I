package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StartGameButton extends JButton {

    public StartGameButton(DeviceScreenInformation deviceInfo, FontInfo fontInfo){
        setBounds(deviceInfo.screenWidth/2-150,2*deviceInfo.screenHeight/3, 300,70);
        setHorizontalAlignment(JButton.CENTER);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.white, 2),
                BorderFactory.createEmptyBorder(0,20,0,20)));
        setBackground(Color.decode("#14171C"));
        setForeground(Color.white);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setText("Start Game");
        setOpaque(true);
        setFont(fontInfo.getResizedFont(50f));

    }

}

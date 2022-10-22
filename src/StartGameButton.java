package src;

import javax.swing.*;
import java.awt.*;

public class StartGameButton extends JButton {

    public StartGameButton(){
        setBounds(DeviceInformation.screenWidth /2-150,2* DeviceInformation.screenHeight /3, 300,70);
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
        setFont(FontInfo.getResizedFont(50f));

    }

}

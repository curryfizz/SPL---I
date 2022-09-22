package src;

import javax.swing.*;
import java.awt.*;

public class StartMenu {
    JPanel startMenuPanel;
    JLabel startMenuBackground;
    UI ui;
    public StartMenu(UI ui){
        this.ui = ui;
        startMenuPanel = new JPanel();
        startMenuBackground = new JLabel();
        createBackground();
    }


    public void createBackground(){
        startMenuPanel.setBounds(ui.maxBounds);
        startMenuPanel.setBackground(Color.decode("#14171C"));
        startMenuPanel.setForeground(Color.decode("#C64C1D"));
        ui.window.add(startMenuPanel);
    }
}

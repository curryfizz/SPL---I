package src;

import javax.swing.*;
import java.awt.*;

public class exitWindowPopup extends JOptionPane {

    private UIManager uiManager;
    private FontInfo fontInfo;
    exitWindowPopup(FontInfo fontInfo){
        uiManager = new UIManager();
        this.fontInfo = fontInfo;
        createPopup();
    }

    private void createPopup(){
        uiManager.put("OptionPane.background", Color.decode("#14171C"));
        uiManager.put("OptionPane.messageForeground", Color.white);
        uiManager.put("OptionPane.messageFont", fontInfo.getResizedFont(25f));
        uiManager.put("OptionPane.buttonFont", fontInfo.getResizedFont(20f));
        uiManager.put("Panel.background", Color.decode("#14171C"));
        uiManager.put("Button.background", Color.decode("#14171C"));
        uiManager.put("Button.foreground", Color.white);
        uiManager.put("Button.textShiftOffset", 25);
        uiManager.put("Button.focus", new Color(0, 0, 0, 0));
        uiManager.put("Button.contentAreaFilled",false);
        uiManager.put("Button.border", BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.white, 3),
                BorderFactory.createEmptyBorder(2,20,2,20)));
    }
}

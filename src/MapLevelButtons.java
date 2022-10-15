package src;

import javax.swing.*;
import java.awt.*;

public class MapLevelButtons extends JButton {
    
    public MapLevelButtons( FontInfo fontInfo, int posx, int posy, String text, MapT mapT){
        setText(text);
        setBackground(Color.decode("#14171C"));
        setForeground(Color.white);
        setFont(fontInfo.getResizedFont(29f));
        setBounds(posx,posy,200,36);
        setFocusPainted(false);
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.decode("#14171C"),3), BorderFactory.createLineBorder(Color.decode("#55a38b"),2)));
        addMouseListener(new MenuMouseEvents(mapT, text));
    }
    
    
}

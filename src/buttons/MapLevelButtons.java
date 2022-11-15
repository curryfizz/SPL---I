package src.buttons;

import src.setup.FontInfo;
import src.transitionPanels.MapT;

import javax.swing.*;
import java.awt.*;

public class MapLevelButtons extends JButton {
    public MapT mapT;


    public MapLevelButtons( int posx, int posy, int width,int height, String text, MapT mapT){
        this.mapT = mapT;
        setText(text);
        setBackground(Color.darkGray);
        setForeground(Color.white);
        setFont(FontInfo.getResizedFont(38f));
        setBounds(posx,posy,width,height);
        setFocusPainted(false);
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.decode("#14171C"),3), BorderFactory.createLineBorder(Color.decode("#55a38b"),2)));
//        addMouseListener(new MenuMouseEvents(mapT, text)); //prolly not needed
        setVisible(true);
        mapT.add(this);
        mapT.repaint();
        mapT.revalidate();
    }
    
}

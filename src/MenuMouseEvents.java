package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuMouseEvents implements MouseListener {
    Timer timer = new Timer(50,null);
    JLabel label;
    ImageIcon imageDefault;
    ImageIcon imageHover;
    MenuMouseEvents(JLabel label, ImageIcon imageDefault, ImageIcon imageHover){
        this.imageDefault = imageDefault;
        this.imageHover = imageHover;
        this.label = label;
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        label.setIcon(imageHover);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        label.setIcon(imageDefault);

    }


}

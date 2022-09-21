package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SceneObjectEvents implements MouseListener {
    JLabel imageLabel;

    SceneObjectEvents(JLabel imageLabel){
        this.imageLabel = imageLabel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        imageLabel.setVisible(false);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

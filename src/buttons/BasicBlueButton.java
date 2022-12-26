package src.buttons;

import src.setup.FontInfo;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BasicBlueButton extends JButton {

    public BasicBlueButton(int width, int height, String text){


        addButtonStyles(width,height,text);

        addFocusInOutTransition();
    }

    private void addFocusInOutTransition() {
        addMouseListener(new MouseListener() {
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
                repaint();
                setForeground(Color.decode("#14171C"));
                setBackground(Color.white);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                repaint();
                setBackground(Color.decode("#14171C"));
                setForeground(Color.white);
                repaint();
            }
        });
    }

    private void addButtonStyles(int width, int height, String text) {
        setPreferredSize(new Dimension(width,height));
        setBorder(new LineBorder(Color.white, 2));
        setBackground(Color.decode("#14171C"));
        setForeground(Color.white);
        setFont(FontInfo.getResizedFont(40f));
        setText(text);
        setOpaque(true);
    }

}

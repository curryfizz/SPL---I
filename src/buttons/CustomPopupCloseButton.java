package src.buttons;

import src.popups.LevelToMapConfirmationDialog;
import src.setup.DeviceInformation;
import src.setup.FontInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CustomPopupCloseButton extends JButton {

    public CustomPopupCloseButton(JDialog BaseFrame){

        setBounds(BaseFrame.getWidth() -10, 10, 60,60);
        setBackground(null);
        setBorder(null);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setFont(FontInfo.getResizedFont(35f));
        setText("X");
        setForeground(Color.white);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                BaseFrame.dispose();
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
        });
        BaseFrame.add(this);
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.WHITE);

        g2d.setPaint(Color.decode("#14171C"));
        g2d.fillOval(5, 5,50,50);

        g2d.setStroke(new BasicStroke(4));
        g2d.setColor(Color.WHITE);
        g2d.drawOval(5,5,50,50);
        super.paint(g);
    }
}
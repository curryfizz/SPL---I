package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

public class StartMenu extends JPanel{
    JLabel startMenuBackground;
    DeviceScreenInformation deviceScreenInformation;
    JButton closeButton;
    int width;
    int height;
    public StartMenu(JFrame jFrame){
        deviceScreenInformation = new DeviceScreenInformation();
        width = deviceScreenInformation.screenWidth;
        height = deviceScreenInformation.screenHeight;
        createBackground();
        createButton(jFrame);
        jFrame.add(this);
    }



    public void createButton(JFrame jFrame){
        closeButton = new JButton();
        closeButton.setBounds(width-50,0, 50,50);
        closeButton.setBorder(null);
        closeButton.setBackground(null);
        closeButton.setForeground(Color.white);
        closeButton.setFocusPainted(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setFont(deviceScreenInformation.getResizedFont(50f));
        closeButton.setText("X");
        closeButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jFrame.dispose();
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
//        closeButton.setVisible(true);
        this.add(closeButton);
    }
    public void createBackground(){
        this.setLayout(null);
        this.setPreferredSize(new Dimension(width,height));
        this.setBackground(Color.decode("#14171C"));
        this.setForeground(Color.decode("#C64C1D"));
    }
}

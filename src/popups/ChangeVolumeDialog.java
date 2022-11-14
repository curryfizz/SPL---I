package src.popups;

import src.GameAudioSlider;
import src.Sound;
import src.setup.DeviceInformation;
import src.setup.FontInfo;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ChangeVolumeDialog extends JDialog {

    public ChangeVolumeDialog(JFrame jFrame, Sound sound){
        setModal(true);
        setUndecorated(true);
        getContentPane().setBackground(Color.decode("#14171C"));
//        setLayout(new GridLayout(2,1));
        setLayout(new FlowLayout());
        getRootPane().setBorder(new LineBorder(Color.white,2));

        JSlider jSlider = new JSlider(-40,6);
        jSlider.setFocusable(false);
        add(jSlider);
        jSlider.setBorder(null);
        setLocation(DeviceInformation.screenWidth-100, 50);
        setSize(100,100);
//        setBounds(De.viceInformation.screenWidth-300,50,100,100);
        setResizable(false);
        System.out.println(jSlider.getWidth() + " " + jSlider.getHeight());

        jSlider.setUI(new GameAudioSlider(jSlider));
        jSlider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                sound.currentVolume =jSlider.getValue();
                if(sound.currentVolume==-40){
                    sound.currentVolume = -80;
                }
                sound.fc.setValue(sound.currentVolume);
                System.out.println(jSlider.getValue());
                jSlider.repaint();
            }
        });


        JButton closeButton = new JButton();
        closeButton.setBackground(Color.decode("#14171C"));
        closeButton.setPreferredSize(new Dimension(50,30));
        closeButton.setFocusPainted(false);
        closeButton.setHorizontalAlignment(JButton.CENTER);
        closeButton.setBorder(new LineBorder(Color.white,2));
        closeButton.setForeground(Color.white);
        closeButton.setFont(FontInfo.getResizedFont(25f));
        closeButton.setOpaque(true);
        closeButton.setText("OK");
        closeButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                dispose();
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

        add(closeButton);
        setVisible(true);


    }
}

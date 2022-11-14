package src.buttons;

import src.Sound;
import src.popups.ChangeVolumeDialog;
import src.popups.ExitWindowConfirmationDialog;
import src.setup.DeviceInformation;
import src.setup.FontInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AudioChangeButton extends JButton {


    public AudioChangeButton (JFrame jFrame, Sound sound){
        setBackground(null);
        setBorder(null);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setFont(FontInfo.getResizedFont(50f));
        setText("♬");
        setForeground(Color.white);
        setBounds(DeviceInformation.screenWidth -100, 10, 50,50);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ChangeVolumeDialog changeVolumeDialog = new ChangeVolumeDialog(jFrame,sound);
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

        jFrame.revalidate();
        jFrame.repaint();
    }

}

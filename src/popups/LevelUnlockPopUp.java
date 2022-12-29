package src.popups;

import src.levelObjects.Sound;
import src.levels.ALevelPanel;
import src.levels.ClassRoomSceneT;
import src.setup.FontInfo;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class LevelUnlockPopUp extends JDialog {
    JLabel jLabel;
    JButton closeButton;


    public LevelUnlockPopUp (JFrame jFrame, int level_number)
    {

        setModal(true);
        setUndecorated(true);

        getContentPane().setBackground(Color.decode("#14171C"));
        setLayout(new FlowLayout());
        getRootPane().setBorder(new LineBorder(Color.white,2));
        jLabel = new JLabel();

        jLabel.setPreferredSize(new Dimension(300,200));
        jLabel.setLayout(new FlowLayout());
        jLabel.setForeground(Color.white);
        if(level_number==2){
            jLabel.setText(convertToMultiline("ClassRoom  Unlocked "));
        }
        else if(level_number==3){
            jLabel.setText(convertToMultiline("Library Unlocked "));
        }
        else if (level_number==4){
            jLabel.setText(convertToMultiline("Central Departmental Store  Unlocked "));
        }
        else if (level_number==5){
            jLabel.setText(convertToMultiline("BE AWARE !!! THIS IS THE FINAL LEVEL"));
        }
        jLabel.setFont(FontInfo.getResizedFont(43f));
        add(jLabel);
        jLabel.setHorizontalTextPosition(JLabel.CENTER);
        setSize(320,280);
        setLocationRelativeTo(jFrame);
        setResizable(false);

        closeButton = new JButton();
        closeButton.setBackground(Color.decode("#14171C"));
        closeButton.setPreferredSize(new Dimension(210,40));
        closeButton.setFocusPainted(false);
        closeButton.setHorizontalAlignment(JButton.CENTER);
        closeButton.setBorder(new LineBorder(Color.white,3));
        closeButton.setForeground(Color.white);
        closeButton.setFont(FontInfo.getResizedFont(28f));
        closeButton.setOpaque(true);
        closeButton.setText("Okay");
        closeButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                Sound alert = new Sound();
                alert.setFile("audio/soundeffects/alert.wav");
                alert.play();
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
    public static String convertToMultiline(String orig)
    {
        return "<html>" + orig.replaceAll("\n", "<br>");
    }
}


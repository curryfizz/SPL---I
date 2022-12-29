package src.popups;

import src.levelObjects.Sound;
import src.setup.FontInfo;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FinalLevelCompletedPopUp extends JDialog {
    JLabel jLabel;
    JButton closeButton;


    public FinalLevelCompletedPopUp(JFrame jFrame, int level_number)
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
        jLabel.setText("GAME FINISHED");
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


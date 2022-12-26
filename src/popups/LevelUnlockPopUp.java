package src.popups;

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
    JButton  closeButton2;

    public LevelUnlockPopUp (JFrame jFrame, int level_number)
    {

        setModal(true);
        setUndecorated(true);
        getContentPane().setBackground(Color.PINK);
        setLayout(new FlowLayout());
        getRootPane().setBorder(new LineBorder(Color.BLUE,3));
        jLabel = new JLabel();
        jLabel.setPreferredSize(new Dimension(170,90));
        jLabel.setLayout(new FlowLayout());
        jLabel.setForeground(Color.white);
        jLabel.setText(convertToMultiline("Congratulations\n you unclock the next lavel Classroom.\n do you want to go to classroom??"));
        jLabel.setFont(FontInfo.getResizedFont(26f));
        add(jLabel);
        jLabel.setHorizontalTextPosition(JLabel.CENTER);
        setSize(200,180);
        setLocationRelativeTo(jFrame);
        setResizable(false);
        closeButton = new JButton();
        closeButton.setBackground(Color.BLUE);
        closeButton.setPreferredSize(new Dimension(110,30));
        closeButton.setFocusPainted(false);
        closeButton.setHorizontalAlignment(JButton.CENTER);
        closeButton.setBorder(new LineBorder(Color.white,2));
        closeButton.setForeground(Color.white);
        closeButton.setFont(FontInfo.getResizedFont(25f));
        closeButton.setOpaque(true);
        closeButton.setText("YES");

        closeButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(level_number==2){
                   jLabel.setText("ClassRoom Scene Unlocked");
                }
                else if(level_number==3){
                    jLabel.setText("Library Scene Unlocked");
                }
                if(level_number==4){
                    jLabel.setText("DormRoom Scene Unlocked");
                }
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

        setVisible(true);
    }
    public static String convertToMultiline(String orig)
    {
        return "<html>" + orig.replaceAll("\n", "<br>");
    }
}


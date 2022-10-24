package src;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HintConfirmationDialogue extends JDialog {

    JLabel jLabel;
    JButton YesButton;

    JButton NoButton;
    public HintConfirmationDialogue(JFrame jFrame, JPanel jPanel){

        setModal(true);
        setUndecorated(true);

        getContentPane().setBackground(Color.decode("#14171C"));
        setLayout(new FlowLayout());
        getRootPane().setBorder(new LineBorder(Color.white,2));
        jLabel = new JLabel();
        jLabel.setPreferredSize(new Dimension(170,90));
        jLabel.setLayout(new FlowLayout());
        jLabel.setForeground(Color.white);
        jLabel.setText(convertToMultiline("See Hint for this Item?\nYou will receive get points..."));
        jLabel.setFont(FontInfo.getResizedFont(26f));
        add(jLabel);
        jLabel.setHorizontalTextPosition(JLabel.CENTER);
        setSize(200,180);
        setLocationRelativeTo(jFrame);
        setResizable(false);
        YesButton = new JButton();
        YesButton.setBackground(Color.decode("#14171C"));
        YesButton.setPreferredSize(new Dimension(110,30));
        YesButton.setFocusPainted(false);
        YesButton.setHorizontalAlignment(JButton.CENTER);
        YesButton.setBorder(new LineBorder(Color.white,2));
        YesButton.setForeground(Color.white);
        YesButton.setFont(FontInfo.getResizedFont(25f));
        YesButton.setOpaque(true);
        YesButton.setText("Yes");
        YesButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(jPanel instanceof DormRoomLevelPanelT){
                    ((DormRoomLevelPanelT)jPanel).timerLabel.endLevel();
                    dispose();
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
        add(YesButton);
        NoButton = new JButton();
        NoButton.setFont(FontInfo.getResizedFont(25f));
        NoButton.setBackground(Color.decode("#14171C"));
        NoButton.setPreferredSize(new Dimension(110,30));
        NoButton.setFocusPainted(false);
        NoButton.setHorizontalAlignment(JButton.CENTER);
        NoButton.setBorder(new LineBorder(Color.white,2));
        NoButton.setForeground(Color.white);
        NoButton.setOpaque(true);
        NoButton.setText("No");
        NoButton.addMouseListener(new MouseListener() {
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
        add(NoButton);
        setVisible(true);
    }

    public static String convertToMultiline(String orig)
    {
        return "<html>" + orig.replaceAll("\n", "<br>");
    }
}

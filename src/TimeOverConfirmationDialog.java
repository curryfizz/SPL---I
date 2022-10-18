package src;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TimeOverConfirmationDialog extends JDialog {

    JLabel jLabel;
    JButton closeButton;

    JButton closeButton2;


    public TimeOverConfirmationDialog(JFrame jFrame, FontInfo fontInfo, JPanel jPanel){

        setModal(true);
        setUndecorated(true);

        getContentPane().setBackground(Color.decode("#14171C"));
        setLayout(new FlowLayout());
        getRootPane().setBorder(new LineBorder(Color.white,2));
        jLabel = new JLabel();
        jLabel.setPreferredSize(new Dimension(140,90));
        jLabel.setLayout(new FlowLayout());
        jLabel.setForeground(Color.white);
        jLabel.setText(convertToMultiline("Oh no! Your Time is Up"));
        jLabel.setFont(fontInfo.getResizedFont(28f));
        add(jLabel);
        jLabel.setHorizontalAlignment(JLabel.CENTER);
        setSize(300,150);
        setLocationRelativeTo(jFrame);
        setResizable(false);
        closeButton = new JButton();
        closeButton.setBackground(Color.decode("#14171C"));
        closeButton.setPreferredSize(new Dimension(110,30));
        closeButton.setFocusPainted(false);
        closeButton.setHorizontalAlignment(JButton.CENTER);
        closeButton.setBorder(new LineBorder(Color.white,2));
        closeButton.setForeground(Color.white);
        closeButton.setFont(fontInfo.getResizedFont(25f));
        closeButton.setOpaque(true);
        closeButton.setText("Exit to Menu");
        closeButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(jPanel instanceof DormRoomSceneT){
                    ((DormRoomSceneT)jPanel).timerLabel.endLevel();
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
        add(closeButton);

        setVisible(true);
//        jFrame.add(this);
    }

    public static String convertToMultiline(String orig)
    {
        return "<html>" + orig.replaceAll("\n", "<br>");
    }
}

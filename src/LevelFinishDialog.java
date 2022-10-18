package src;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LevelFinishDialog extends JDialog {

    JLabel jLabel;
    JButton exitToMapButton;

    JButton closeButton2;
    public LevelFinishDialog(JFrame jFrame, FontInfo fontInfo, JPanel jPanel){

        setModal(true);
        setUndecorated(true);

        getContentPane().setBackground(Color.decode("#14171C"));
        setLayout(new FlowLayout());
        getRootPane().setBorder(new LineBorder(Color.white,2));
        jLabel = new JLabel();
        jLabel.setPreferredSize(new Dimension(250,130));
        jLabel.setLayout(new FlowLayout());
        jLabel.setForeground(Color.white);
        jLabel.setText(convertToMultiline("Congratulations!\nYou've cleared this level successfully!"));
        jLabel.setFont(fontInfo.getResizedFont(28f));
        add(jLabel);
        jLabel.setHorizontalAlignment(JLabel.CENTER);
        setSize(300,200);
        setLocationRelativeTo(jFrame);
        setResizable(false);
        exitToMapButton = new JButton();
        exitToMapButton.setBackground(Color.decode("#14171C"));
        exitToMapButton.setPreferredSize(new Dimension(110,50));
        exitToMapButton.setFocusPainted(false);
        exitToMapButton.setHorizontalAlignment(JButton.CENTER);
        exitToMapButton.setBorder(new LineBorder(Color.white,2));
        exitToMapButton.setForeground(Color.white);
        exitToMapButton.setFont(fontInfo.getResizedFont(25f));
        exitToMapButton.setOpaque(true);
        exitToMapButton.setText("Exit to Map!");
        exitToMapButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(jPanel instanceof DormRoomSceneT){
                    ((DormRoomSceneT)jPanel).timerLabel.endLevel();
                    dispose();
                }
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
        add(exitToMapButton);

        setVisible(true);
//        jFrame.add(this);
    }

    public static String convertToMultiline(String orig)
    {
        return "<html>" + orig.replaceAll("\n", "<br>");
    }
}

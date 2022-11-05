package src.popups;

import src.levels.ALevelPanel;
import src.setup.FontInfo;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HintConfirmationDialogue extends JDialog {

    JLabel jLabel;
    ALevelPanel backgroundPanel;
    JButton YesButton;
    int indexOfObjectButton;

    JButton NoButton;
    public HintConfirmationDialogue(JFrame jFrame, ALevelPanel jPanel, int index){
        this.indexOfObjectButton = index;
        this.backgroundPanel = jPanel;
        setModal(true);
        setUndecorated(true);

        getContentPane().setBackground(Color.decode("#14171C"));
        setLayout(new FlowLayout());
        getRootPane().setBorder(new LineBorder(Color.white,2));
        jLabel = new JLabel();
        jLabel.setPreferredSize(new Dimension(170,170));
        jLabel.setLayout(new FlowLayout());
        jLabel.setForeground(Color.white);
        jLabel.setText(convertToMultiline("See Hint for this Item?\nYou will get less points and loose your combo..."));
        jLabel.setFont(FontInfo.getResizedFont(26f));
        add(jLabel);
        jLabel.setHorizontalTextPosition(JLabel.CENTER);
        setSize(200,170+90);
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
                backgroundPanel.buttonList.get(indexOfObjectButton).HintWasUsed = true;
                Point location = backgroundPanel.buttonList.get(indexOfObjectButton).getLocation();
//                location.x -= 50;
                location.y -= 50;
                backgroundPanel.HintAnimationGif.setLocation(location);
                backgroundPanel.HintAnimationGif.setVisible(true);
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
                backgroundPanel.HintAnimationGif.setVisible(false);
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

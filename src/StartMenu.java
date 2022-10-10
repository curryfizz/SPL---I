package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StartMenu extends JPanel{
    JFrame jFrame;
    JLabel gameTitle;
    JButton startGameButton;
    DeviceScreenInformation deviceInfo;
    FontInfo fontInfo;
    CloseButton closeButton;
    public StartMenu(JFrame jFrame, DeviceScreenInformation deviceScreenInformation, FontInfo fontInfo){
        this.jFrame = jFrame;
        this.deviceInfo = deviceScreenInformation;
        this.fontInfo = fontInfo;
        createBackgroundPanel();
        addCustomWindowCloseButton(jFrame);
        createStartGameButton(jFrame);
        createGameTitleLabel();
        this.add(gameTitle);
        this.add(startGameButton);
        jFrame.add(this);

    }



    public void createBackgroundPanel(){
        this.setLayout(null);
        this.setPreferredSize(new Dimension(deviceInfo.screenWidth, deviceInfo.screenHeight));
        this.setBackground(Color.decode("#14171C"));
        this.setForeground(Color.decode("#C64C1D"));
    }

    public void createGameTitleLabel(){
        gameTitle = new JLabel();
        gameTitle.setBounds(0,deviceInfo.screenHeight/2,deviceInfo.screenWidth,100);
        gameTitle.setFont(fontInfo.getResizedFont(100f));
        gameTitle.setText("LOST TREASURES");
        gameTitle.setHorizontalAlignment(JLabel.CENTER);
        gameTitle.setForeground(Color.white);
        gameTitle.setBackground(new Color(0,0,0,0));
    }

    public void createStartGameButton(JFrame jFrame){
        startGameButton = new JButton();
        startGameButton.setBounds(deviceInfo.screenWidth/2-150,2*deviceInfo.screenHeight/3, 300,70);
        startGameButton.setHorizontalAlignment(JButton.CENTER);
        startGameButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.white, 2),
                BorderFactory.createEmptyBorder(0,20,0,20)));
        startGameButton.setBackground(null);
        startGameButton.setForeground(Color.white);
        startGameButton.setFocusPainted(false);
        startGameButton.setContentAreaFilled(false);
        startGameButton.setFont(fontInfo.getResizedFont(50f));
        startGameButton.setText("Start Game");

        startGameButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                StartLoadScreen();
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
    }

    public void StartLoadScreen(){
        DecoyAnimation decoyAnimation = new DecoyAnimation(jFrame, deviceInfo,fontInfo,5);
        jFrame.remove(this);
        jFrame.revalidate();
        jFrame.repaint();
    }

    public void addCustomWindowCloseButton(JFrame jFrame){
        closeButton = new CloseButton(deviceInfo,"X",jFrame, fontInfo);
        this.add(closeButton);
    }
}

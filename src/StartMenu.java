package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StartMenu extends JPanel{
    JFrame jFrame;
    JLabel gameTitle;
    JButton startGameButton;
    DeviceScreenInformation deviceScreenInformation;
    FontInfo fontInfo;
    CloseButton closeButton;
    int width;
    int height;
    public StartMenu(JFrame jFrame){
        this.jFrame = new JFrame();
        fontInfo = new FontInfo();
        getGraphicsEnvironmentInfo();
        createBackgroundPanel();
        addCustomWindowCloseButton(jFrame);
        createStartGameButton(jFrame);
        createGameTitleLabel();
        this.add(gameTitle);
        this.add(startGameButton);
        jFrame.add(this);

    }

    private void getGraphicsEnvironmentInfo(){
        deviceScreenInformation = new DeviceScreenInformation();
        getScreenDimensions();
    }

    private void getScreenDimensions(){
        width = deviceScreenInformation.screenWidth;
        height = deviceScreenInformation.screenHeight;
    }



    public void createBackgroundPanel(){
        this.setLayout(null);
        this.setPreferredSize(new Dimension(width,height));
        this.setBackground(Color.decode("#14171C"));
        this.setForeground(Color.decode("#C64C1D"));
    }

    public void createGameTitleLabel(){
        gameTitle = new JLabel();
        gameTitle.setBounds(0,height/2,width,100);
        gameTitle.setFont(fontInfo.getResizedFont(100f));
        gameTitle.setText("LOST TREASURES");
        gameTitle.setHorizontalAlignment(JLabel.CENTER);
        gameTitle.setForeground(Color.white);
        gameTitle.setBackground(new Color(0,0,0,0));
    }

    public void createStartGameButton(JFrame jFrame){
        startGameButton = new JButton();
        startGameButton.setBounds(width/2-150,2*height/3, 300,70);
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
                DecoyAnimation decoyAnimation = new DecoyAnimation(jFrame, 10);
                jFrame.revalidate();
                jFrame.remove(StartMenu.this);
                jFrame.revalidate();
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

    public void LoadMenu(JFrame jFrame){
        Menu map = new Menu(jFrame);
        jFrame.add(map);
        jFrame.revalidate();
        jFrame.remove(StartMenu.this);
        jFrame.revalidate();
    }

    public void addCustomWindowCloseButton(JFrame jFrame){
        closeButton = new CloseButton(deviceScreenInformation,"X",jFrame, fontInfo);
        this.add(closeButton);
    }
}

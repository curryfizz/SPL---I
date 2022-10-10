package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import static java.awt.SystemColor.menu;
import static java.awt.SystemColor.text;

public class StartMenu extends JPanel{
    JLabel gameTitle;

    JButton startGame;
    DeviceScreenInformation deviceScreenInformation;
    JButton closeButton;
    int width;
    int height;
    public StartMenu(JFrame jFrame){
        deviceScreenInformation = new DeviceScreenInformation();
        width = deviceScreenInformation.screenWidth;
        height = deviceScreenInformation.screenHeight;
        createBackground();
        createButton(jFrame);
        createStartGameButton(jFrame);
        createGameTitle();
        this.add(gameTitle);
        this.add(startGame);
        jFrame.add(this);

    }



    public void createButton(JFrame jFrame){
        closeButton = new JButton();
        closeButton.setBounds(width-50,5, 50,50);
        closeButton.setBorder(null);
        closeButton.setBackground(null);
        closeButton.setForeground(Color.white);
        closeButton.setFocusPainted(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setFont(deviceScreenInformation.getResizedFont(50f));
        closeButton.setText("X");
        closeButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                exitWindowPopup exitWindowPopup = new exitWindowPopup(deviceScreenInformation);
                jFrame.add(exitWindowPopup);
                int choice = exitWindowPopup.showConfirmDialog(jFrame,"Do you want to exit?","QUIT",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE);
                if(choice==JOptionPane.YES_OPTION){
                    System.exit(0);
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
        this.add(closeButton);
    }


    public void createBackground(){
        this.setLayout(null);
        this.setPreferredSize(new Dimension(width,height));
        this.setBackground(Color.decode("#14171C"));
        this.setForeground(Color.decode("#C64C1D"));
    }

    public void createGameTitle(){
        gameTitle = new JLabel();
        gameTitle.setBounds(0,height/2,width,100);
        gameTitle.setFont(deviceScreenInformation.getResizedFont(100f));
        gameTitle.setText("LOST TREASURES");
        gameTitle.setHorizontalAlignment(JLabel.CENTER);
        gameTitle.setForeground(Color.white);
        gameTitle.setBackground(new Color(0,0,0,0));
    }

    public void createStartGameButton(JFrame jFrame){
        startGame = new JButton();
        startGame.setBounds(width/2-150,2*height/3, 300,70);
        startGame.setHorizontalAlignment(JButton.CENTER);
        startGame.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.white, 2),
                BorderFactory.createEmptyBorder(0,20,0,20)));
        startGame.setBackground(null);
        startGame.setForeground(Color.white);
        startGame.setFocusPainted(false);
        startGame.setContentAreaFilled(false);
        startGame.setFont(deviceScreenInformation.getResizedFont(50f));
        startGame.setText("Start Game");

        startGame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Menu map = new Menu(jFrame);
                jFrame.add(map);
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
}

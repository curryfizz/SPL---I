package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameManager {

//    UI ui = new UI(this);
    public static void main(String[] args) throws InterruptedException{
        new GameManager();
    }

    public GameManager(){
//        GameTimer gameTimer = new GameTimer(ui);
//
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setUndecorated(true);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.setVisible(true);
        jFrame.setBackground(Color.white);
        DeviceScreenInformation deviceScreenInformation = new DeviceScreenInformation();
        FontInfo fontInfo = new FontInfo();


        StartGameButton startGameButton = new StartGameButton(deviceScreenInformation,fontInfo);
        StartMenuScreenT startMenu = new StartMenuScreenT(jFrame, deviceScreenInformation,fontInfo);
        Thread startScreen = new Thread(startMenu);

        MapT mapT = new MapT(jFrame,deviceScreenInformation,fontInfo);

        LoadingAnimationT loadingAnimationT = new LoadingAnimationT(jFrame,deviceScreenInformation,fontInfo,4,mapT);
        Thread loadScreen = new Thread(loadingAnimationT);
        Thread GameMap = new Thread(mapT);

        GameMap.start();
        loadScreen.start();
        startScreen.start();

        startMenu.add(startGameButton);
        jFrame.add(startMenu);
//        jFrame.add(loadingAnimationT);



        startGameButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jFrame.remove(startMenu);
                jFrame.add(loadingAnimationT);
                loadingAnimationT.initializeTimer();
                jFrame.revalidate();
                jFrame.repaint();

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



        StartGameButton startGameButton1 = new StartGameButton(deviceScreenInformation,fontInfo);
//        mapT.add(startGameButton1);

        LoadingAnimationT loadingAnimationT1 = new LoadingAnimationT(jFrame,deviceScreenInformation,fontInfo,4,startMenu);
        Thread loadingAnimation2 = new Thread(loadingAnimationT1);
        loadingAnimation2.start();
        startGameButton1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jFrame.remove(mapT);
                jFrame.add(loadingAnimationT1);
                loadingAnimationT1.initializeTimer();
                jFrame.revalidate();
                jFrame.repaint();
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

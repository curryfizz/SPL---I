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

        DormRoomSceneT dormRoomSceneT = new DormRoomSceneT(jFrame,deviceScreenInformation,fontInfo);
        Thread dormthread = new Thread(dormRoomSceneT);
        LoadingAnimationT loadingAnimationT = new LoadingAnimationT(jFrame,deviceScreenInformation,fontInfo,4,mapT);
        Thread loadScreen = new Thread(loadingAnimationT);
        Thread GameMap = new Thread(mapT);
        loadingAnimationT.getNextScene(mapT);
        GameMap.start();
        loadScreen.start();
        startScreen.start();
        dormthread.start();

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

        LoadingAnimationT loadingAnimationT1 = new LoadingAnimationT(jFrame,deviceScreenInformation,fontInfo,4,startMenu);
        Thread loadingAnimation2 = new Thread(loadingAnimationT1);
        loadingAnimation2.start();

        MapLevelButtons mapLevelButtonsAC2 = new MapLevelButtons(fontInfo,450,50, "Academic Building 2", mapT);
//        mapLevelButtonsAC2.setEnabled(false);
        mapT.add(mapLevelButtonsAC2);

        MapLevelButtons mapLevelButtonsDorm = new MapLevelButtons(fontInfo,699,145, "Dormitory", mapT);
        mapT.add(mapLevelButtonsDorm);


        MapLevelButtons mapLevelButtonsCDS = new MapLevelButtons(fontInfo,979,300, "CDS", mapT);
        mapT.add(mapLevelButtonsCDS);

        MapLevelButtons mapLevelButtonLibrary = new MapLevelButtons(fontInfo,580,300, "Library", mapT);
        mapT.add(mapLevelButtonLibrary);




    }
}

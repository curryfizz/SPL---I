package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameManager {


    public static void main(String[] args) throws InterruptedException{
        new GameManager();
    }

    public GameManager(){

        /* Set up the frame*/
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setUndecorated(true);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.setVisible(true);
        jFrame.setBackground(Color.white);
        DeviceScreenInformation deviceScreenInformation = new DeviceScreenInformation();
        FontInfo fontInfo = new FontInfo();


        /* Instantiate the jpanels and the jpanel's threads
         each jPanel's thread BUILDS (only) the jpanel, so we can save time on panel building */

        StartGameButton startGameButton = new StartGameButton(deviceScreenInformation,fontInfo);
        StartMenuScreenT startMenu = new StartMenuScreenT(jFrame, deviceScreenInformation,fontInfo);
        Thread startMenuThread = new Thread(startMenu);

        MapT mapT = new MapT(jFrame,deviceScreenInformation,fontInfo);
        Thread mapThread = new Thread(mapT);

        DormRoomSceneT dormRoomSceneT = new DormRoomSceneT(jFrame,deviceScreenInformation,fontInfo);
        Thread dormRoomThread = new Thread(dormRoomSceneT);

        LoadingAnimationT loadingAnimationT = new LoadingAnimationT(jFrame,deviceScreenInformation,fontInfo,1,mapT);
        Thread loadingThread_map = new Thread(loadingAnimationT);

        mapThread.start();
        loadingThread_map.start();
        startMenuThread.start();
        dormRoomThread.start();

        startMenu.add(startGameButton);
        jFrame.add(startMenu);

        startGameButton.addMouseListener(new MouseListener() {
            boolean isHovering = false;
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
                startGameButton.setBackground(new Color(100,70,120));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                startGameButton.setBackground(null);
                if(isHovering) {
                    jFrame.remove(startMenu);
                    jFrame.add(loadingAnimationT);
                    loadingAnimationT.initializeTimer();

                    jFrame.revalidate();
                    jFrame.repaint();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                isHovering = true;
                startGameButton.setBackground(new Color(0,40,80));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovering = false;
                startGameButton.setBackground(null);
            }
        });

//        LoadingAnimationT loadingAnimationT_dorm = new LoadingAnimationT(jFrame,deviceScreenInformation,fontInfo,4,dormRoomSceneT);
//        Thread loadingThread_dorm = new Thread(loadingAnimationT_dorm);
//        loadingThread_dorm.start();

        /* Add map buttons **/

        MapLevelButtons mapLevelButtonsAC2 = new MapLevelButtons(fontInfo,450,50, "Academic Building 2", mapT);
        mapT.add(mapLevelButtonsAC2);
        MapLevelButtons mapLevelButtonsDorm = new MapLevelButtons(fontInfo,699,145, "Dormitory", mapT);
        mapT.add(mapLevelButtonsDorm);
        MapLevelButtons mapLevelButtonsCDS = new MapLevelButtons(fontInfo,979,300, "CDS", mapT);
        mapT.add(mapLevelButtonsCDS);
        MapLevelButtons mapLevelButtonLibrary = new MapLevelButtons(fontInfo,580,300, "Library", mapT);
        mapT.add(mapLevelButtonLibrary);




        mapLevelButtonsDorm.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jFrame.remove(mapT);
                loadingAnimationT.changeNextScene(dormRoomSceneT);

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

    }
}

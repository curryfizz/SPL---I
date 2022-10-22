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
        LibrarySceneT l = new LibrarySceneT();
        DeviceInformation deviceInformation = new DeviceInformation();
        FontInfo fontInfo = new FontInfo();

        /* Set up the frame*/
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setUndecorated(true);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.setVisible(true);
        jFrame.setBackground(Color.decode("#14171C"));

//        ConfirmationDialog confirmationDialog = new ConfirmationDialog(jFrame,fontInfo);
//
//
//        /* Instantiate the jpanels and the jpanel's threads
//         each jPanel's thread BUILDS (only) the jpanel, so we can save time on panel building */
//

//        MessageFromMomT messageFromMomT = new MessageFromMomT(jFrame, deviceScreenInformation, fontInfo);
//        Thread messageFromMomThread = new Thread(messageFromMomT);
//
//        messageFromMomThread.start();
//
//        jFrame.add(messageFromMomT);
//        jFrame.repaint();
//        jFrame.revalidate();



        StartGameButton startGameButton = new StartGameButton();
        StartMenuScreenT startMenu = new StartMenuScreenT(jFrame);
        Thread startMenuThread = new Thread(startMenu);

        MapT mapT = new MapT(jFrame);
        Thread mapThread = new Thread(mapT);

        DormRoomSceneT dormRoomSceneT = new DormRoomSceneT(jFrame);
        Thread dormRoomThread = new Thread(dormRoomSceneT);

        MessageFromMomT messageFromMomT = new MessageFromMomT(jFrame);
        Thread messageMomThread = new Thread(messageFromMomT);

        LoadingAnimationT loadingAnimationT = new LoadingAnimationT(jFrame,1,messageFromMomT);
        Thread loadingThread = new Thread(loadingAnimationT);

        messageFromMomT.PrepareForTheEnd(loadingAnimationT, mapT);
        messageMomThread.start();
        mapThread.start();
        loadingThread.start();
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

        /* Add map buttons **/

        MapLevelButtons mapLevelButtonsAC2 = new MapLevelButtons(450,50, "Academic Building 2", mapT);
        mapT.add(mapLevelButtonsAC2);
        MapLevelButtons mapLevelButtonsDorm = new MapLevelButtons(699,145, "Dormitory", mapT);
        mapT.add(mapLevelButtonsDorm);
        MapLevelButtons mapLevelButtonsCDS = new MapLevelButtons(979,300, "CDS", mapT);
        mapT.add(mapLevelButtonsCDS);
        MapLevelButtons mapLevelButtonLibrary = new MapLevelButtons(580,300, "Library", mapT);
        mapT.add(mapLevelButtonLibrary);



        mapLevelButtonsDorm.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jFrame.remove(mapT);
                loadingAnimationT.changeNextScene(dormRoomSceneT);
                dormRoomSceneT.prepareEndOfLevel(loadingAnimationT,mapT);
                jFrame.add(loadingAnimationT);
                loadingAnimationT.initializeTimer();

                mapT.dormText.setVisible(false);
                mapLevelButtonsDorm.setBackground(Color.BLACK);
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
                mapLevelButtonsDorm.setBackground(Color.PINK);
                mapT.dormText.setVisible(true);

                mapT.revalidate();
                mapT.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mapLevelButtonsDorm.setBackground(Color.BLACK);
            }
        });





    }
}

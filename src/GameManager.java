package src;

import javax.swing.*;
import java.awt.*;

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

        startMenu.PrepareForSceneTransition(loadingAnimationT, mapT);
        messageFromMomT.PrepareForSceneTransition(loadingAnimationT, mapT);

        messageMomThread.start();
        mapThread.start();
        loadingThread.start();
        startMenuThread.start();
        dormRoomThread.start();


        jFrame.add(startMenu);



        /* Add map buttons **/







    }
}

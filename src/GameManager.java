package src;

import javax.swing.*;
import java.awt.*;

public class GameManager {


    public static void main(String[] args) throws InterruptedException{
        new GameManager();
    }

    public GameManager(){

        DeviceInformation deviceInformation = new DeviceInformation();
        FontInfo fontInfo = new FontInfo();
        System.out.println(deviceInformation.screenHeight + " " + deviceInformation.screenWidth);
        /* Set up the frame*/
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setUndecorated(true);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.setBackground(Color.decode("#14171C"));
        jFrame.pack();
        jFrame.setVisible(true);

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

        DormRoomLevelPanelT dormRoomSceneT = new DormRoomLevelPanelT(jFrame);
        Thread dormRoomThread = new Thread(dormRoomSceneT);

        LibrarySceneT librarySceneT= new LibrarySceneT(jFrame);
        Thread  librarySceneThread = new Thread(librarySceneT);


        MessageFromMomT messageFromMomT = new MessageFromMomT(jFrame);
        Thread messageMomThread = new Thread(messageFromMomT);

        LoadingAnimationT loadingAnimationT = new LoadingAnimationT(jFrame,2,messageFromMomT);
        Thread loadingThread = new Thread(loadingAnimationT);



        mapT.AddAllScenes(loadingAnimationT, dormRoomSceneT,librarySceneT);
        startMenu.PrepareForSceneTransition(loadingAnimationT, mapT);
        messageFromMomT.PrepareForSceneTransition(loadingAnimationT, mapT);

        messageMomThread.start();
        mapThread.start();
        loadingThread.start();
        startMenuThread.start();
        dormRoomThread.start();
        librarySceneThread.start();
        //librarySceneT.run();

        jFrame.add(startMenu); //should have been startmenu
        //librarySceneT.startScene();

        /*
        TODO:
        - Combo
        - fix hint
        - new levels
        - plot
        - fix lame text boxes
        * */



    }
}

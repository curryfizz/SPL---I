package src;

import src.levels.ClassRoomSceneT;
import src.levels.DormRoomLevelPanelT;
import src.levels.LibrarySceneT;
import src.setup.DeviceInformation;
import src.setup.FontInfo;
import src.transitionPanels.LoadingAnimationT;
import src.transitionPanels.MapT;
import src.transitionPanels.MessageFromMomT;
import src.transitionPanels.StartMenuScreenT;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameManager {


    public static void main(String[] args) throws InterruptedException{
        new GameManager();
//        test();
    }

    public static void test(){
        System.setProperty("sun.java2d.uiScale", "1.0");
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

        testAnimation testAnimation = new testAnimation();
        jFrame.add(testAnimation);
        testAnimation.initializeTimer();
    }
    public GameManager(){
        System.setProperty("sun.java2d.uiScale", "1.0");
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


        //thread pool
        ExecutorService pl = Executors.newFixedThreadPool(1);


       StartMenuScreenT startMenu = new StartMenuScreenT(jFrame);
//        Thread startMenuThread = new Thread(startMenu);

        MapT mapT = new MapT(jFrame);

//        Thread mapThread = new Thread(mapT);

        DormRoomLevelPanelT dormRoomSceneT = new DormRoomLevelPanelT(jFrame);

//        Thread dormRoomThread = new Thread(dormRoomSceneT);

        LibrarySceneT librarySceneT= new LibrarySceneT(jFrame);
//        Thread  librarySceneThread = new Thread(librarySceneT);

        ClassRoomSceneT classRoomSceneT = new ClassRoomSceneT(jFrame);
//        Thread classroomThread = new Thread(classRoomSceneT);

        MessageFromMomT messageFromMomT = new MessageFromMomT(jFrame);
//        Thread messageMomThread = new Thread(messageFromMomT);

        LoadingAnimationT loadingAnimationT = new LoadingAnimationT(jFrame,2,messageFromMomT);
//        Thread loadingThread = new Thread(loadingAnimationT);



        mapT.AddAllScenes(loadingAnimationT, dormRoomSceneT,librarySceneT,classRoomSceneT);
        startMenu.PrepareForSceneTransition(loadingAnimationT, mapT);
        messageFromMomT.PrepareForSceneTransition(loadingAnimationT, mapT);

        pl.execute(startMenu);
        pl.execute(mapT);
        pl.execute(loadingAnimationT);
        pl.execute(messageFromMomT);
        pl.execute(dormRoomSceneT);
        pl.execute(classRoomSceneT);
        pl.execute(librarySceneT);


//        ClassRoomSceneT classRoomSceneT = new ClassRoomSceneT(jFrame);
//        classRoomSceneT.buildScene();


//        messageMomThread.start();
//        mapThread.start();
//        loadingThread.start();
//        startMenuThread.start();
//        dormRoomThread.start();
//        classroomThread.start();
//        librarySceneThread.start();
        //librarySceneT.run();

        jFrame.add(startMenu); //should be startmenu during real play
        //classRoomSceneT.startScene();

        pl.shutdown();
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

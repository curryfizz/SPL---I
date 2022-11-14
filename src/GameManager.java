package src;

import src.levels.*;
import src.setup.DeviceInformation;
import src.setup.FontInfo;
import src.setup.PlayerInfo;
import src.transitionPanels.LoadingAnimationT;
import src.transitionPanels.MapT;
import src.transitionPanels.MessageFromMomT;
import src.transitionPanels.StartMenuScreenT;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameManager {

    public static DormRoomLevelPanelT dormRoomLevelPanelT;

    public static void main(String[] args) throws InterruptedException, UnsupportedAudioFileException, LineUnavailableException, IOException {
        new GameManager();

//        test();
//        Sound sound = new Sound();
//
//        JFrame jFrame = new JFrame();
//        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        jFrame.setLayout(new GridLayout(1,3));
//
//        JSlider jSlider = new JSlider(-40,6);
//
//
//
//
//
//        jFrame.add(jSlider);
//
//        jSlider.setUI(new GameAudioSlider(jSlider));
//        jSlider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//        jSlider.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                sound.currentVolume =jSlider.getValue();
//                if(sound.currentVolume==-40){
//                    sound.currentVolume = -80;
//                }
//                sound.fc.setValue(sound.currentVolume);
//                jSlider.repaint();
//            }
//        });
//
//
//        jFrame.pack();
//        jFrame.setVisible(true);
//
//        sound.setFile(0);
//
//        sound.play();
//
//        do {
//            try {
//                Thread.sleep(50);
//            } catch (InterruptedException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        } while (sound.clip.isActive());

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
    public GameManager() throws IOException {
        System.setProperty("sun.java2d.uiScale", "1.0");
        DeviceInformation deviceInformation = new DeviceInformation();
        FontInfo fontInfo = new FontInfo();
        PlayerInfo player = new PlayerInfo(1); // taken from database

        System.out.println(deviceInformation.screenHeight + " " + deviceInformation.screenWidth);

        /* Set up the frame*/
        JFrame jFrame = new JFrame();
        jFrame.setBackground(Color.black);
        jFrame.setForeground(Color.black);
        jFrame.setTitle("Lost Treasures");
        jFrame.setIconImage(new ImageIcon("images/Others/logo2.png").getImage());
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


        CDS_LevelPanelT cds_levelPanelT = new CDS_LevelPanelT(jFrame);



        MessageFromMomT messageFromMomT = new MessageFromMomT(jFrame);
//        Thread messageMomThread = new Thread(messageFromMomT);

        LoadingAnimationT loadingAnimationT = new LoadingAnimationT(jFrame,2,messageFromMomT);
//        Thread loadingThread = new Thread(loadingAnimationT);



        mapT.AddAllScenes(loadingAnimationT, dormRoomSceneT,librarySceneT,classRoomSceneT,cds_levelPanelT);
        startMenu.PrepareForSceneTransition(loadingAnimationT, mapT);
        messageFromMomT.PrepareForSceneTransition(loadingAnimationT, mapT);

        pl.execute(startMenu);
        pl.execute(mapT);
        pl.execute(loadingAnimationT);
        pl.execute(messageFromMomT);
        pl.execute(dormRoomSceneT);
        pl.execute(classRoomSceneT);
        pl.execute(librarySceneT);
        pl.execute(cds_levelPanelT);


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
//        jFrame.add(dormRoomSceneT); //should be startmenu during real play
//        dormRoomSceneT.startScene();
        //classRoomSceneT.startScene();

        pl.shutdown();
        //librarySceneT.startScene();



//        dormRoomLevelPanelT = new DormRoomLevelPanelT(jFrame);
//        dormRoomLevelPanelT.buildScene();
//        dormRoomLevelPanelT.startScene();
//
//        jFrame.add(dormRoomLevelPanelT);





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

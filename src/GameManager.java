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
import java.awt.*;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameManager {

    public static DormRoomLevelPanelT dormRoomLevelPanelT;

    public static void main(String[] args) throws InterruptedException, UnsupportedAudioFileException, LineUnavailableException, IOException {
        new GameManager();

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
        jFrame.setIconImage(new ImageIcon("images/icons/logo2.png").getImage());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setUndecorated(true);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.setBackground(Color.decode("#14171C"));
        jFrame.pack();
        jFrame.setVisible(true);


        //thread pool
        ExecutorService pl = Executors.newFixedThreadPool(1);


        StartMenuScreenT startMenu = new StartMenuScreenT(jFrame);

        MapT mapT = new MapT(jFrame);


        DormRoomLevelPanelT dormRoomSceneT = new DormRoomLevelPanelT(jFrame);


        LibrarySceneT librarySceneT= new LibrarySceneT(jFrame);

        ClassRoomSceneT classRoomSceneT = new ClassRoomSceneT(jFrame);

        CDS_LevelPanelT cds_levelPanelT = new CDS_LevelPanelT(jFrame);

        MessageFromMomT messageFromMomT = new MessageFromMomT(jFrame);

        LoadingAnimationT loadingAnimationT = new LoadingAnimationT(jFrame,2,messageFromMomT);



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


        jFrame.add(startMenu); //should be startmenu during real play

        pl.shutdown();

    }

    /*TODO:
    - add small icon in map to go to player stats;
    - try to make garbage collector work;
    - why does score Animation keep disappearing so quickly;
    - why does the given score go up to 200+ when you play dorm scene 2 times;
    - make sure music audio start's at 50%
    - tie all the music and sound effects together, or make them seperate, people may find it too loud;
    - add sound effects for other button clicks
    - add music for start menu and map
    - make sure unlocking animation doesn't appear unless level is actually unlocked
    - make sure map level buttons don't work unless gameProgress is satisfactory;
    - Just completing the level shouldn't be counted as completing, there should be a threshold score to achieve eg:500 points
    - change forest music for classroom
    - try re-rendering dormscene if there's time, it looks bad compared to it's fancy classroom and fancy library brothers
    - add transcucent background image for text Box in AlevelPanels, black box looks kinda bad;
    - add background image for timer label and score label;
    - the buttons in mapT looks kinda bad, I vote changing to transparent buttons with  text on them;
    */
}

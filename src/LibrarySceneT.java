package src;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class LibrarySceneT extends ALevelPanel implements Runnable{


    JFrame jFrame;
    Rectangle maxBounds;

    boolean isTimerOver;
    public int score = 0;
    int currentCombo = 0;
    int timeSinceLastFind = 0;
    CloseButton closeButton;
    JLabel backgroundLabel;
    JLabel BigItemListAtBottomOfScreen;
    LoadingAnimationT loadingAnimationT;
    MapT mapT;
    URL music;
    MusicPlayer musicPlayer;

//    JLabel HintAnimationGif;

    boolean levelFinished;
    int imagesFound;
    int textBox_height;


    JButton messNotification; // notification that someone messed up your dorm room
    RandomGenerator randomGenerator;
    boolean InnitiallyClicked = false;






    public LibrarySceneT(JFrame jFrame){
//        System.out.println(DeviceInformation.StaticTestVar);
        this.jFrame = jFrame;
        levelFinished = false;
        maxBounds = DeviceInformation.graphicsEnvironment.getMaximumWindowBounds();
        textBox_height = 50;
        this.setLayout(null);

    }

    @Override
    public void run() {
        buildScene();
    }


    public void buildScene(){
        MessNotification();
        setupShowGottenScore();
        setupHintAnimationGif();
        createBackground("images/dormImages/LevelOneMain.png");

        timerLabel = new TimerLabel(jFrame, this);
        timerLabel.setVisible(false);
        revalidate();
        repaint();

        scoreBoard = new ScoreBoard(jFrame, this);
        scoreBoard.setVisible(false);
        revalidate();
        repaint();

        addCustomWindowCloseButton();
        repaint();

        imagesFound=0;
        generateScreenWithAllObjectsAndButtons();

        repaint();
        music = getClass().getClassLoader().getResource("images/bgmusic.wav");
    }

    @Override
    public void startScene() {

    }

    @Override
    public void PrepareForSceneTransition(LoadingAnimationT loadingAnimationT, MapT mapT) {

    }

    @Override
    public void EndLevel() {

    }
}

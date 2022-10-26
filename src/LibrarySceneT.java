package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.Objects;

public class LibrarySceneT extends ALevelPanel implements Runnable{


    JFrame jFrame;
    Rectangle maxBounds;

    boolean isTimerOver;
    public int score = 0;
    int currentCombo = 0;
    int timeSinceLastFind = 0;
   // CloseButton closeButton;
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
        createBackground("images/libraryImages/libraryMain.png");

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
        addEntirePanelListener();
        repaint();
        music = getClass().getClassLoader().getResource("images/bgmusic2.wav");
    }

    private void setupHintAnimationGif() {
//        JLabel objectLabel = new JLabel();
//        objectLabel.setBounds(0,0,maxBounds.width,maxBounds.height-textBox_height);
//
//        ImageIcon  obj1icon= new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(image)));
//        Image image1 = obj1icon.getImage();
//        image1 = image1.getScaledInstance(maxBounds.width, maxBounds.height-textBox_height, Image.SCALE_DEFAULT);
//        obj1icon = new ImageIcon(image1);
//
//        objectLabel.setIcon(obj1icon);
//        imageList.add(objectLabel);

        int sizeX = 300;
        int sizeY = 100;

        ImageIcon gif = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("images/Gifs/black_walking_paws.gif")));
        gif.setImage(gif.getImage().getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT));
        HintAnimationGif = new JLabel();
        HintAnimationGif.setBounds(600,500, sizeX,sizeY);

        HintAnimationGif.setIcon(gif);
        HintAnimationGif.setVisible(true);
//        HintAnimationGif.setBackground(Color.BLACK);
//        HintAnimationGif.setOpaque(true);
        HintAnimationGif.setVisible(false);
        this.add(HintAnimationGif);
    }

    private void setupShowGottenScore() {
        ShowGottenScore = new JLabel("", SwingConstants.CENTER);
        ShowGottenScore.setBounds(500,400, 50, 30);
        ShowGottenScore.setBackground(null);
        ShowGottenScore.setFont(FontInfo.getResizedFont(29f));
//        ShowGottenScore.setForeground(new Color(30, 120, 20));
        ShowGottenScore.setForeground(Color.decode("#ffff00"));
        ShowGottenScore.setVisible(false);
        ShowGottenScore.setOpaque(false);
        this.add(ShowGottenScore);
    }

    public  void generateScreenWithAllObjectsAndButtons() {

        // hoise but just comment kore rakhsi

        createButton("images/libraryImages/levelTwoWithOutlines/item 0.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 1.PNG", DeviceInformation.screenWidth *840/1536, DeviceInformation.screenHeight *488/864,
                DeviceInformation.screenWidth *60/1536, DeviceInformation.screenHeight *5/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 2.PNG", DeviceInformation.screenWidth *820/1536, DeviceInformation.screenHeight *490/864,
                DeviceInformation.screenWidth *18/1536, DeviceInformation.screenHeight *12/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 3.PNG", DeviceInformation.screenWidth *528/1536, DeviceInformation.screenHeight *662/864,
                DeviceInformation.screenWidth *25/1536, DeviceInformation.screenHeight *8/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 4.PNG", DeviceInformation.screenWidth *10/1536, DeviceInformation.screenHeight *745/864,
                DeviceInformation.screenWidth *48/1536, DeviceInformation.screenHeight *22/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 5.PNG", DeviceInformation.screenWidth *590/1536, DeviceInformation.screenHeight *718/864,
                DeviceInformation.screenWidth *75/1536, DeviceInformation.screenHeight *60/864);
        createText("Cornflakes Box");
       createButton("images/libraryImages/levelTwoWithOutlines/item 6.PNG", DeviceInformation.screenWidth *200/1536, DeviceInformation.screenHeight *728/864,
                DeviceInformation.screenWidth *78/1536, DeviceInformation.screenHeight *25/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 7.PNG", DeviceInformation.screenWidth *228/1536, DeviceInformation.screenHeight *570/864,
                DeviceInformation.screenWidth *10/1536, DeviceInformation.screenHeight *10/864);
        createText("Cornflakes Box");





        //hoy nai



//        createButton("images/libraryImages/levelTwoWithOutlines/item 8.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
//                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
//        createText("Cornflakes Box");


        //hoise egula

//        createButton("images/libraryImages/levelTwoWithOutlines/item 9.PNG", DeviceInformation.screenWidth *970/1536, DeviceInformation.screenHeight *458/864,
//                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *35/864);
//        createText("Cornflakes Box");
//        createButton("images/libraryImages/levelTwoWithOutlines/item 10.PNG", DeviceInformation.screenWidth *108/1536, DeviceInformation.screenHeight *588/864,
//                DeviceInformation.screenWidth *88/1536, DeviceInformation.screenHeight *28/864);
//        createText("Cornflakes Box");
//        createButton("images/libraryImages/levelTwoWithOutlines/item 11.PNG", DeviceInformation.screenWidth *290/1536, DeviceInformation.screenHeight *678/864,
//                DeviceInformation.screenWidth *30/1536, DeviceInformation.screenHeight *20/864);
//        createText("Cornflakes Box");
//        createButton("images/libraryImages/levelTwoWithOutlines/item 12.PNG", DeviceInformation.screenWidth *795/1536, DeviceInformation.screenHeight *744/864,
//                DeviceInformation.screenWidth *20/1536, DeviceInformation.screenHeight *12/864);
//        createText("Cornflakes Box");
//        createButton("images/libraryImages/levelTwoWithOutlines/item 13.PNG", DeviceInformation.screenWidth *1118/1536, DeviceInformation.screenHeight *510/864,
//                DeviceInformation.screenWidth *25/1536, DeviceInformation.screenHeight *8/864);
//        createText("Cornflakes Box");
//        createButton("images/libraryImages/levelTwoWithOutlines/item 14.PNG", DeviceInformation.screenWidth *1078/1536, DeviceInformation.screenHeight *748/864,
//                DeviceInformation.screenWidth *80/1536, DeviceInformation.screenHeight *15/864);
//        createText("Cornflakes Box");
//        createButton("images/libraryImages/levelTwoWithOutlines/item 15.PNG", DeviceInformation.screenWidth *1068/1536, DeviceInformation.screenHeight *472/864,
//                DeviceInformation.screenWidth *20/1536, DeviceInformation.screenHeight *10/864);
//        createText("Cornflakes Box");


        createButton("images/libraryImages/levelTwoWithOutlines/item 16.PNG", DeviceInformation.screenWidth *282/1536, DeviceInformation.screenHeight *507/864,
                DeviceInformation.screenWidth *10/1536, DeviceInformation.screenHeight *10/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 17.PNG", DeviceInformation.screenWidth *578/1536, DeviceInformation.screenHeight *42/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *74/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 18.PNG", DeviceInformation.screenWidth *121/1536, DeviceInformation.screenHeight *182/864,
                DeviceInformation.screenWidth *16/1536, DeviceInformation.screenHeight *22/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 19.PNG", DeviceInformation.screenWidth *927/1536, DeviceInformation.screenHeight *228/864,
                DeviceInformation.screenWidth *26/1536, DeviceInformation.screenHeight *25/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 20.PNG", DeviceInformation.screenWidth *914/1536, DeviceInformation.screenHeight *434/864,
                DeviceInformation.screenWidth *40/1536, DeviceInformation.screenHeight *15/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 21.PNG", DeviceInformation.screenWidth *431/1536, DeviceInformation.screenHeight *486/864,
                DeviceInformation.screenWidth *50/1536, DeviceInformation.screenHeight *5/864);
        createText("Cornflakes Box");





        createButton("images/libraryImages/levelTwoWithOutlines/item 22.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 23.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 24.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");

        /*
        createButton("images/libraryImages/levelTwoWithOutlines/item 25.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");

        createButton("images/libraryImages/levelTwoWithOutlines/item 26.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 27.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 28.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 29.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");

        createButton("images/libraryImages/levelTwoWithOutlines/item 30.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 31.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 32.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 33.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 34.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 35.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");

        createButton("images/libraryImages/levelTwoWithOutlines/item 36.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 37.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 38.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 39.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");

        createButton("images/libraryImages/levelTwoWithOutlines/item 40.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 41.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 42.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 43.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 44.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 45.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");

        createButton("images/libraryImages/levelTwoWithOutlines/item 46.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 47.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 48.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 49.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 50.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 51.PNG", DeviceInformation.screenWidth *830/1536, DeviceInformation.screenHeight *488/864,
                DeviceInformation.screenWidth *60/1536, DeviceInformation.screenHeight *5/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 52.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 53.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 54.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 55.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");

        createButton("images/libraryImages/levelTwoWithOutlines/item 56.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 57.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 58.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 59.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 60.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 61.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 62.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 63.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 64.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 65.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");

        createButton("images/libraryImages/levelTwoWithOutlines/item 66.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 67.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 68.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 69.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");

        createButton("images/libraryImages/levelTwoWithOutlines/item 70.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 71.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 72.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 73.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 74.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 75.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");

        createButton("images/libraryImages/levelTwoWithOutlines/item 76.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 77.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 78.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 79.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");

        createButton("images/libraryImages/levelTwoWithOutlines/item 80.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 81.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 82.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 83.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 84.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 85.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
      */

        // amio kori (ayesha)


        //hoy

        createButton("images/libraryImages/levelTwoWithOutlines/item 86.PNG", DeviceInformation.screenWidth *954/1536, DeviceInformation.screenHeight *705/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *25/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 87.PNG", DeviceInformation.screenWidth *1448/1536, DeviceInformation.screenHeight *710/864,
                DeviceInformation.screenWidth *74/1536, DeviceInformation.screenHeight *18/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 88.PNG", DeviceInformation.screenWidth *1062/1536, DeviceInformation.screenHeight *711/864,
                DeviceInformation.screenWidth *60/1536, DeviceInformation.screenHeight *12/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 89.PNG", DeviceInformation.screenWidth *1235/1536, DeviceInformation.screenHeight *718/864,
                DeviceInformation.screenWidth *109/1536, DeviceInformation.screenHeight *48/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 90.PNG", DeviceInformation.screenWidth *1050/1536, DeviceInformation.screenHeight *606/864,
                DeviceInformation.screenWidth *64/1536, DeviceInformation.screenHeight *22/864);
        createText("Cornflakes Box");



        this.add(backgroundLabel);
    }
    public void MessNotification(){
        messNotification = new JButton("<html>Oh No, The room looks like it got ransacked?! Where is my present?<br/> Guess I'll have to tidy up (Tap to Search)</html>");

        messNotification.setFont(FontInfo.getResizedFont(34f));
        messNotification.setFocusPainted(false);
        messNotification.setEnabled(false);
        messNotification.setBounds(0, DeviceInformation.screenHeight -100, DeviceInformation.screenWidth, 100);
        messNotification.setBackground(Color.decode("#14171C"));
        messNotification.setForeground(Color.white);
        messNotification.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.decode("#14171C"),3), BorderFactory.createLineBorder(Color.white,3)));
        messNotification.setOpaque(true);
//        messNotification.setVisible(true);
//        addEntirePanelListener();
        this.add(messNotification);
        repaint();
        revalidate();
    }

    private void addEntirePanelListener() {
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!InnitiallyClicked) {
                    timerLabel.setVisible(true);
                    scoreBoard.setVisible(true);
                    revalidate();
                    repaint();
//                    enableObjectButtons();
                    messNotification.setVisible(false);
                    timerLabel.StartTimer();
                    InnitiallyClicked = true;
                }
                System.out.println(e.getX() + ", " + e.getY());

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


    public  void createBackground(String bgfilename) {
        this.setLayout(null);
        this.setBounds(0, 0, maxBounds.width, maxBounds.height);//size of the background image
        this.setBackground(Color.black);

        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(bgfilename)));
        Image image = imageIcon.getImage();
        image = image.getScaledInstance(maxBounds.width, maxBounds.height-textBox_height, Image.SCALE_DEFAULT);
        imageIcon = new ImageIcon(image);
        backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0,0, maxBounds.width,maxBounds.height-textBox_height);
        backgroundLabel.setIcon(imageIcon);
    }

    public void addCustomWindowCloseButton(){
        LevelCloseButton levelCloseButton = new LevelCloseButton("X",jFrame,this);
        this.add(levelCloseButton);
        this.repaint();
        this.revalidate();
    }


    private void CreateAListWithAllItemNamesAsLabels() {

        for(int i = 0; i < textList.size(); i++){
            JLabel temp = new JLabel(textList.get(i), SwingConstants.CENTER);
            temp.setForeground(Color.white);
            temp.setFont(FontInfo.getResizedFont(37f));
//            temp.setText(textList.get(i));
            ListOfAllItemNamesAsLabels.add(temp);
        }

    }

    public void CreateTheBigItemListTextBoxAtTheBottomOfScreen(){
        BigItemListAtBottomOfScreen = new JLabel();
        BigItemListAtBottomOfScreen.setLayout(new GridLayout(1,5));
        BigItemListAtBottomOfScreen.setBounds(0, DeviceInformation.screenHeight -100, DeviceInformation.screenWidth, 100);
        BigItemListAtBottomOfScreen.setBackground(Color.decode("#14171C"));
        BigItemListAtBottomOfScreen.setForeground(Color.white);
        BigItemListAtBottomOfScreen.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.decode("#14171C"),3), BorderFactory.createLineBorder(Color.white,3)));
        BigItemListAtBottomOfScreen.setFont(FontInfo.getResizedFont(29f));
        BigItemListAtBottomOfScreen.setOpaque(true);
    }

    public void enableObjectButtons(){
        for(int i=0; i<6; i++){
            buttonList.get(RandObjIndices.get(i)).setEnabled(true);
        }
    }



    public void resetItemNameLabelList() {

        for(int i = 0; i < textList.size(); i++){
            ListOfAllItemNamesAsLabels.get(i).setVisible(true);
            imageList.get(i).setVisible(true);

        }

    }

    public void  createButton(String image, int posx, int posy, int sizex,int sizey) {
        JLabel objectLabel = createObject1(image);

        ObjectHidingButton objectHidingButton = new ObjectHidingButton(posx,posy,sizex,sizey, imageList.get(imageList.size()-1), this, buttonList.size()){
            @Override
            public void addSceneEventsListener(ObjectHidingButton button){
                addMouseListener(new SceneObjectEvents(associatedLabel, scenePanel){
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if(isEnabled()) {
                            imageLabel.setVisible(false);
                            imagesFound+=1;

                            if((timerLabel.elapsedTime - timeSinceLastFind) < 10){
                                currentCombo++;
                            }else{
                                currentCombo = 0;
                            }

                            timeSinceLastFind = timerLabel.elapsedTime;

                            int gottenScore;
                            if(HintWasUsed){
                                gottenScore = scoreBoard.setScore(50, 0);
                                currentCombo = 0;
                                HintAnimationGif.setVisible(false);
                            }
                            else{
                                gottenScore = scoreBoard.setScore((int) (timerLabel.elapsedTime/2.0), currentCombo);
                            }
                            scoreBoard.refreshScore();

                            ShowGottenScore.setText("+" + Integer.toString(gottenScore));
                            ShowGottenScore.setLocation(button.getLocation());
                            ShowGottenScore.setVisible(true);
                            repaint();

//                            //hehe
//
//                            HintAnimationGif.setLocation(button.getLocation());
//                            HintAnimationGif.setVisible(true);
//
//                            //hehe

                            timerLabel.AnimateScore();

                            setEnabled(false);
                            ListOfAllItemNamesAsLabels.get(myIndex).setVisible(false);
                            if(imagesFound == 6){
                                if(scenePanel instanceof ALevelPanel){
                                    ((DormRoomLevelPanelT)scenePanel).timerLabel.isTimeOver = true;

                                }
                                imagesFound=0;
                                LevelFinishDialog levelFinishDialog = new LevelFinishDialog(jFrame,scenePanel);
                                scenePanel.revalidate();
                                scenePanel.repaint();
                                jFrame.revalidate();
                                jFrame.repaint();

                            }
                        }
                    }
                });
            }
        };
        this.add(objectHidingButton);
        this.add(objectLabel);
        buttonList.add(objectHidingButton);
    }
    public void createText (String text) {
        textList.add(text);
    }


    public JLabel createObject1(String image){
        JLabel objectLabel = new JLabel();
        objectLabel.setBounds(0,0,maxBounds.width,maxBounds.height-textBox_height);

        ImageIcon  obj1icon= new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(image)));
        Image image1 = obj1icon.getImage();
        image1 = image1.getScaledInstance(maxBounds.width, maxBounds.height-textBox_height, Image.SCALE_DEFAULT);
        obj1icon = new ImageIcon(image1);

        objectLabel.setIcon(obj1icon);
        imageList.add(objectLabel);
        return objectLabel;

    }



    @Override
    public void startScene() {
        messNotification.setVisible(true);
        timerLabel.setVisible(false);
        scoreBoard.setVisible(false);
//        MakeRandomItemListAtBottomOfScreen();
        InnitiallyClicked = false;
        resetVariables();
        ResetTimerAndScore();
    }

    public void MakeRandomItemListAtBottomOfScreen(){
        CreateAListWithAllItemNamesAsLabels();
        CreateTheBigItemListTextBoxAtTheBottomOfScreen();
        randomGenerator = new RandomGenerator(buttonList.size());
        randomGenerator.createUnique();
        this.RandObjIndices = randomGenerator.RandObjIndices;
        this.add(BigItemListAtBottomOfScreen);
        int index;
        for(int i=0; i<RandObjIndices.size(); i++){
            index = RandObjIndices.get(i);
            ListOfAllItemNamesAsLabels.get(index).addMouseListener(new LabelListener(jFrame, this,
                    ListOfAllItemNamesAsLabels.get(index), index));
            BigItemListAtBottomOfScreen.add(ListOfAllItemNamesAsLabels.get(index));
        }

    }


    @Override
    public void PrepareForSceneTransition(LoadingAnimationT loadingAnimationT, MapT mapT) {
        this.loadingAnimationT = loadingAnimationT;
        this.mapT = mapT;
    }

    @Override
    public void EndLevel() {
        resetItemNameLabelList();
        remove(BigItemListAtBottomOfScreen);
        ShowGottenScore.setVisible(false);
        timerLabel.st_alpha=255;
//        HintAnimationGif.setVisible();
        revalidate();
        repaint();

        jFrame.remove(this);

        loadingAnimationT.changeNextScene(mapT);
        ((MapT) loadingAnimationT.nextScene).score += scoreBoard.score; // won't give compile time casting error bc I JUST CHANGED IT TO MAPT
        ((MapT) loadingAnimationT.nextScene).updateScore();

        jFrame.add(loadingAnimationT);
        loadingAnimationT.initializeTimer();
        jFrame.revalidate();
        jFrame.repaint();
        musicPlayer.stop(music);
    }

    public void ResetTimerAndScore(){
        timerLabel.isTimeOver = false;
        timerLabel.second = 30;
        timerLabel.minute = 2;
        timerLabel.elapsedTime = 0;
        scoreBoard.score=0;
        scoreBoard.setText("0000");
        imagesFound = 0;
        score=0;

    }

    public void resetVariables(){

        timerLabel.isTimeOver = false;
        scoreBoard.score=0;

        timerLabel.setVisible(false);
        revalidate();
        repaint();
        scoreBoard.setVisible(false);
        revalidate();
        repaint();

        musicPlayer = new MusicPlayer();
        musicPlayer.playMusic(music);
    }

}

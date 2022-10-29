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
        this.jFrame = jFrame;
        levelFinished = false;
        maxBounds = DeviceInformation.graphicsEnvironment.getMaximumWindowBounds();
        textBox_height = DeviceInformation.screenHeight*50/864;
       // this.setLayout(new GridLayout());
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
        repaint();
        music = getClass().getClassLoader().getResource("images/library.wav");
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


    public void MessNotification(){
        messNotification = new JButton("<html>Oh No, The Library is very messy! Where is my things?<br/> Guess I'll have to look for my present (Tap to Search)</html>");

        messNotification.setFont(FontInfo.getResizedFont(34f));
        messNotification.setFocusPainted(false);
        messNotification.setEnabled(false);
        messNotification.setBounds(0, DeviceInformation.screenHeight -100, DeviceInformation.screenWidth, 100);
        messNotification.setBackground(Color.decode("#14171C"));
        messNotification.setForeground(Color.white);
        messNotification.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.decode("#14171C"),3), BorderFactory.createLineBorder(Color.white,3)));
        messNotification.setOpaque(true);

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!InnitiallyClicked) {
                    timerLabel.setVisible(true);
                    scoreBoard.setVisible(true);
                    revalidate();
                    repaint();
                    enableObjectButtons();
                    messNotification.setVisible(false);
                    timerLabel.StartTimer();
                    InnitiallyClicked = true;
                }
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
        this.add(messNotification);
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



    public  void generateScreenWithAllObjectsAndButtons() {



        createButton("images/libraryImages/levelTwoWithOutlines/item 0.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Paper with black drawing");
        createButton("images/libraryImages/levelTwoWithOutlines/item 1.PNG", DeviceInformation.screenWidth *840/1536, DeviceInformation.screenHeight *484/864,
                DeviceInformation.screenWidth *60/1536, DeviceInformation.screenHeight *9/864);
        createText("A piece of paper");
        createButton("images/libraryImages/levelTwoWithOutlines/item 2.PNG", DeviceInformation.screenWidth *820/1536, DeviceInformation.screenHeight *490/864,
                DeviceInformation.screenWidth *18/1536, DeviceInformation.screenHeight *12/864);
        createText("Mouse");
        createButton("images/libraryImages/levelTwoWithOutlines/item 3.PNG", DeviceInformation.screenWidth *528/1536, DeviceInformation.screenHeight *662/864,
                DeviceInformation.screenWidth *25/1536, DeviceInformation.screenHeight *8/864);
        createText("keys");
        createButton("images/libraryImages/levelTwoWithOutlines/item 4.PNG", DeviceInformation.screenWidth *10/1536, DeviceInformation.screenHeight *745/864,
                DeviceInformation.screenWidth *48/1536, DeviceInformation.screenHeight *22/864);
        createText("bundle");
        createButton("images/libraryImages/levelTwoWithOutlines/item 5.PNG", DeviceInformation.screenWidth *590/1536, DeviceInformation.screenHeight *718/864,
                DeviceInformation.screenWidth *75/1536, DeviceInformation.screenHeight *60/864);
        createText("Apple Juice");
       createButton("images/libraryImages/levelTwoWithOutlines/item 6.PNG", DeviceInformation.screenWidth *200/1536, DeviceInformation.screenHeight *728/864,
                DeviceInformation.screenWidth *78/1536, DeviceInformation.screenHeight *25/864);
        createText("open Book");
        createButton("images/libraryImages/levelTwoWithOutlines/item 7.PNG", DeviceInformation.screenWidth *228/1536, DeviceInformation.screenHeight *570/864,
                DeviceInformation.screenWidth *10/1536, DeviceInformation.screenHeight *10/864);
        createText("Wrist Watch");
        createButton("images/libraryImages/levelTwoWithOutlines/item 8.PNG", DeviceInformation.screenWidth *790/1536, DeviceInformation.screenHeight *517/864,
                DeviceInformation.screenWidth *26/1536, DeviceInformation.screenHeight *20/864);
       createText("key Ring");
        createButton("images/libraryImages/levelTwoWithOutlines/item 9.PNG", DeviceInformation.screenWidth *970/1536, DeviceInformation.screenHeight *458/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *35/864);
        createText("Stack of books");
        createButton("images/libraryImages/levelTwoWithOutlines/item 10.PNG", DeviceInformation.screenWidth *108/1536, DeviceInformation.screenHeight *588/864,
                DeviceInformation.screenWidth *88/1536, DeviceInformation.screenHeight *28/864);
        createText("Soft Drinks");



        createButton("images/libraryImages/levelTwoWithOutlines/item 11.PNG", DeviceInformation.screenWidth *290/1536, DeviceInformation.screenHeight *678/864,
                DeviceInformation.screenWidth *30/1536, DeviceInformation.screenHeight *20/864);
        createText("Cornflakes Box");//no idea
        createButton("images/libraryImages/levelTwoWithOutlines/item 12.PNG", DeviceInformation.screenWidth *795/1536, DeviceInformation.screenHeight *744/864,
                DeviceInformation.screenWidth *20/1536, DeviceInformation.screenHeight *12/864);
        createText("key");
        createButton("images/libraryImages/levelTwoWithOutlines/item 13.PNG", DeviceInformation.screenWidth *1118/1536, DeviceInformation.screenHeight *510/864,
                DeviceInformation.screenWidth *25/1536, DeviceInformation.screenHeight *8/864);
        createText("Cornflakes Box");//no idea
        createButton("images/libraryImages/levelTwoWithOutlines/item 14.PNG", DeviceInformation.screenWidth *1078/1536, DeviceInformation.screenHeight *748/864,
                DeviceInformation.screenWidth *80/1536, DeviceInformation.screenHeight *15/864);
        createText("Pad");
        createButton("images/libraryImages/levelTwoWithOutlines/item 15.PNG", DeviceInformation.screenWidth *1068/1536, DeviceInformation.screenHeight *472/864,
                DeviceInformation.screenWidth *20/1536, DeviceInformation.screenHeight *10/864);
        createText("wallet");
        createButton("images/libraryImages/levelTwoWithOutlines/item 16.PNG", DeviceInformation.screenWidth *282/1536, DeviceInformation.screenHeight *507/864,
                DeviceInformation.screenWidth *10/1536, DeviceInformation.screenHeight *10/864);
        createText("juice");
        createButton("images/libraryImages/levelTwoWithOutlines/item 17.PNG", DeviceInformation.screenWidth *578/1536, DeviceInformation.screenHeight *42/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *74/864);
        createText("Clock");
        createButton("images/libraryImages/levelTwoWithOutlines/item 18.PNG", DeviceInformation.screenWidth *121/1536, DeviceInformation.screenHeight *182/864,
                DeviceInformation.screenWidth *16/1536, DeviceInformation.screenHeight *22/864);
        createText("Clock");
        createButton("images/libraryImages/levelTwoWithOutlines/item 19.PNG", DeviceInformation.screenWidth *927/1536, DeviceInformation.screenHeight *228/864,
                DeviceInformation.screenWidth *26/1536, DeviceInformation.screenHeight *25/864);
        createText("Database System book");


        createButton("images/libraryImages/levelTwoWithOutlines/item 20.PNG", DeviceInformation.screenWidth *914/1536, DeviceInformation.screenHeight *434/864,
                DeviceInformation.screenWidth *40/1536, DeviceInformation.screenHeight *15/864);
        createText("black Diary");
        createButton("images/libraryImages/levelTwoWithOutlines/item 21.PNG", DeviceInformation.screenWidth *431/1536, DeviceInformation.screenHeight *486/864,
                DeviceInformation.screenWidth *50/1536, DeviceInformation.screenHeight *5/864);
        createText("white Paper");
        createButton("images/libraryImages/levelTwoWithOutlines/item 22.PNG", DeviceInformation.screenWidth *955/1536, DeviceInformation.screenHeight *608/864,
                DeviceInformation.screenWidth *70/1536, DeviceInformation.screenHeight *35/864);
        createText("Board");
        createButton("images/libraryImages/levelTwoWithOutlines/item 23.PNG", DeviceInformation.screenWidth *934/1536, DeviceInformation.screenHeight *528/864,
                DeviceInformation.screenWidth *48/1536, DeviceInformation.screenHeight *25/864);
        createText("White Black Paper");
        createButton("images/libraryImages/levelTwoWithOutlines/item 24.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *437/864,
                DeviceInformation.screenWidth *120/1536, DeviceInformation.screenHeight *95/864);
        createText("Laptop");
        createButton("images/libraryImages/levelTwoWithOutlines/item 25.PNG", DeviceInformation.screenWidth *817/1536, DeviceInformation.screenHeight *381/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *70/864);
        createText("Laptop Screen");
        createButton("images/libraryImages/levelTwoWithOutlines/item 26.PNG", DeviceInformation.screenWidth *977/1536, DeviceInformation.screenHeight *491/864,
                DeviceInformation.screenWidth *20/1536, DeviceInformation.screenHeight *16/864);
        createText("Cornflakes Box");//no idea
        createButton("images/libraryImages/levelTwoWithOutlines/item 27.PNG", DeviceInformation.screenWidth *888/1536, DeviceInformation.screenHeight *410/864,
                DeviceInformation.screenWidth *20/1536, DeviceInformation.screenHeight *40/864);
        createText("Coffee Cup");
        createButton("images/libraryImages/levelTwoWithOutlines/item 28.PNG", DeviceInformation.screenWidth *818/1536, DeviceInformation.screenHeight *500/864,
                DeviceInformation.screenWidth *85/1536, DeviceInformation.screenHeight *45/864);
        createText("Stack of paper");
        createButton("images/libraryImages/levelTwoWithOutlines/item 29.PNG", DeviceInformation.screenWidth *752/1536, DeviceInformation.screenHeight *455/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *32/864);
        createText("3 books");




        createButton("images/libraryImages/levelTwoWithOutlines/item 30.PNG", DeviceInformation.screenWidth *1064/1536, DeviceInformation.screenHeight *505/864,
                DeviceInformation.screenWidth *78/1536, DeviceInformation.screenHeight *18/864);
        createText("Cable");
        createButton("images/libraryImages/levelTwoWithOutlines/item 31.PNG", DeviceInformation.screenWidth *978/1536, DeviceInformation.screenHeight *510/864,
                DeviceInformation.screenWidth *18/1536, DeviceInformation.screenHeight *15/864);
        createText("Black mouse");
        // hoy nai
        createButton("images/libraryImages/levelTwoWithOutlines/item 32.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cans of coke");
        createButton("images/libraryImages/levelTwoWithOutlines/item 33.PNG", DeviceInformation.screenWidth *1085/1536, DeviceInformation.screenHeight *443/864,
                DeviceInformation.screenWidth *58/1536, DeviceInformation.screenHeight *65/864);
        createText("Bag");
        createButton("images/libraryImages/levelTwoWithOutlines/item 34.PNG", DeviceInformation.screenWidth *1146/1536, DeviceInformation.screenHeight *541/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *35/864);
        createText("treasure Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 35.PNG", DeviceInformation.screenWidth *1023/1536, DeviceInformation.screenHeight *642/864,
                DeviceInformation.screenWidth *128/1536, DeviceInformation.screenHeight *62/864);
        createText("letters");
        createButton("images/libraryImages/levelTwoWithOutlines/item 36.PNG", DeviceInformation.screenWidth *1158/1536, DeviceInformation.screenHeight *600/864,
                DeviceInformation.screenWidth *55/1536, DeviceInformation.screenHeight *30/864);
        createText("Invitation Card");
        createButton("images/libraryImages/levelTwoWithOutlines/item 37.PNG", DeviceInformation.screenWidth *1276/1536, DeviceInformation.screenHeight *644/864,
                DeviceInformation.screenWidth *38/1536, DeviceInformation.screenHeight *55/864);
        createText("Watch");
        createButton("images/libraryImages/levelTwoWithOutlines/item 38.PNG", DeviceInformation.screenWidth *1158/1536, DeviceInformation.screenHeight *492/864,
                DeviceInformation.screenWidth *20/1536, DeviceInformation.screenHeight *62/864);
        createText("Apple Juice");
        createButton("images/libraryImages/levelTwoWithOutlines/item 39.PNG", DeviceInformation.screenWidth *950/1536, DeviceInformation.screenHeight *669/864,
                DeviceInformation.screenWidth *48/1536, DeviceInformation.screenHeight *28/864);
        createText("PowerBank");


        createButton("images/libraryImages/levelTwoWithOutlines/item 40.PNG", DeviceInformation.screenWidth *1025/1536, DeviceInformation.screenHeight *495/864,
                DeviceInformation.screenWidth *38/1536, DeviceInformation.screenHeight *15/864);
        createText("Cornflakes Box");//no idea
        createButton("images/libraryImages/levelTwoWithOutlines/item 41.PNG", DeviceInformation.screenWidth *993/1536, DeviceInformation.screenHeight *633/864,
                DeviceInformation.screenWidth *44/1536, DeviceInformation.screenHeight *42/864);
        createText("Earbud case");
        createButton("images/libraryImages/levelTwoWithOutlines/item 42.PNG", DeviceInformation.screenWidth *1158/1536, DeviceInformation.screenHeight *728/864,
                DeviceInformation.screenWidth *53/1536, DeviceInformation.screenHeight *47/864);
        createText("Biscuits");
        createButton("images/libraryImages/levelTwoWithOutlines/item 43.PNG", DeviceInformation.screenWidth *1494/1536, DeviceInformation.screenHeight *135/864,
                DeviceInformation.screenWidth *48/1536, DeviceInformation.screenHeight *35/864);
        createText("Automata Theory BOOK");
        createButton("images/libraryImages/levelTwoWithOutlines/item 44.PNG", DeviceInformation.screenWidth *886/1536, DeviceInformation.screenHeight *392/864,
                DeviceInformation.screenWidth *30/1536, DeviceInformation.screenHeight *20/864);
        createText("Clean Code Book");
        createButton("images/libraryImages/levelTwoWithOutlines/item 45.PNG", DeviceInformation.screenWidth *186/1536, DeviceInformation.screenHeight *219/864,
                DeviceInformation.screenWidth *28/1536, DeviceInformation.screenHeight *40/864);
        createText("Clean code BOOK");
        createButton("images/libraryImages/levelTwoWithOutlines/item 46.PNG", DeviceInformation.screenWidth *1455/1536, DeviceInformation.screenHeight *366/864,
                DeviceInformation.screenWidth *72/1536, DeviceInformation.screenHeight *55/864);
        createText("Linear Algebra Book");
        createButton("images/libraryImages/levelTwoWithOutlines/item 47.PNG", DeviceInformation.screenWidth *1250/1536, DeviceInformation.screenHeight *172/864,
                DeviceInformation.screenWidth *28/1536, DeviceInformation.screenHeight *65/864);
        createText("Database System Book");
        createButton("images/libraryImages/levelTwoWithOutlines/item 48.PNG", DeviceInformation.screenWidth *934/1536, DeviceInformation.screenHeight *472/864,
                DeviceInformation.screenWidth *28/1536, DeviceInformation.screenHeight *30/864);
        createText("Camera");
        createButton("images/libraryImages/levelTwoWithOutlines/item 49.PNG", DeviceInformation.screenWidth *161/1536, DeviceInformation.screenHeight *218/864,
                DeviceInformation.screenWidth *28/1536, DeviceInformation.screenHeight *35/864);
        createText("Linear Algebra Book");


        createButton("images/libraryImages/levelTwoWithOutlines/item 50.PNG", DeviceInformation.screenWidth *167/1536, DeviceInformation.screenHeight *618/864,
                DeviceInformation.screenWidth *48/1536, DeviceInformation.screenHeight *25/864);
        createText("Open Diary");
        createButton("images/libraryImages/levelTwoWithOutlines/item 51.PNG", DeviceInformation.screenWidth *122/1536, DeviceInformation.screenHeight *650/864,
                DeviceInformation.screenWidth *70/1536, DeviceInformation.screenHeight *25/864);
        createText("Pen with Paper");
        createButton("images/libraryImages/levelTwoWithOutlines/item 52.PNG", DeviceInformation.screenWidth *294/1536, DeviceInformation.screenHeight *618/864,
                DeviceInformation.screenWidth *58/1536, DeviceInformation.screenHeight *25/864);
        createText("Cornflakes Box");//janina vai
        createButton("images/libraryImages/levelTwoWithOutlines/item 53.PNG", DeviceInformation.screenWidth *818/1536, DeviceInformation.screenHeight *213/864,
                DeviceInformation.screenWidth *34/1536, DeviceInformation.screenHeight *45/864);
        createText("Java Book");
        createButton("images/libraryImages/levelTwoWithOutlines/item 54.PNG", DeviceInformation.screenWidth *86/1536, DeviceInformation.screenHeight *704/864,
                DeviceInformation.screenWidth *28/1536, DeviceInformation.screenHeight *60/864);
        createText("Orange Cup");
        createButton("images/libraryImages/levelTwoWithOutlines/item 55.PNG", DeviceInformation.screenWidth *01/1536, DeviceInformation.screenHeight *680/864,
                DeviceInformation.screenWidth *78/1536, DeviceInformation.screenHeight *50/864);
        createText("Chips Packet");
        createButton("images/libraryImages/levelTwoWithOutlines/item 56.PNG", DeviceInformation.screenWidth *82/1536, DeviceInformation.screenHeight *570/864,
                DeviceInformation.screenWidth *24/1536, DeviceInformation.screenHeight *25/864);
        createText("Glass");
        createButton("images/libraryImages/levelTwoWithOutlines/item 57.PNG", DeviceInformation.screenWidth *30/1536, DeviceInformation.screenHeight *615/864,
                DeviceInformation.screenWidth *35/1536, DeviceInformation.screenHeight *25/864);
        createText("Spectacles");
        createButton("images/libraryImages/levelTwoWithOutlines/item 58.PNG", DeviceInformation.screenWidth *26/1536, DeviceInformation.screenHeight *564/864,
                DeviceInformation.screenWidth *27/1536, DeviceInformation.screenHeight *32/864);
        createText("Destroyed Can");
        createButton("images/libraryImages/levelTwoWithOutlines/item 59.PNG", DeviceInformation.screenWidth *226/1536, DeviceInformation.screenHeight *382/864,
                DeviceInformation.screenWidth *18/1536, DeviceInformation.screenHeight *45/864);
        createText("Coke Bottle");
        createButton("images/libraryImages/levelTwoWithOutlines/item 60.PNG", DeviceInformation.screenWidth *0/1536, DeviceInformation.screenHeight *419/864,
                DeviceInformation.screenWidth *48/1536, DeviceInformation.screenHeight *25/864);
        createText("Database Management Book");



        createButton("images/libraryImages/levelTwoWithOutlines/item 61.PNG", DeviceInformation.screenWidth *132/1536, DeviceInformation.screenHeight *419/864,
                DeviceInformation.screenWidth *55/1536, DeviceInformation.screenHeight *33/864);
        createText("HeadPhone");
        createButton("images/libraryImages/levelTwoWithOutlines/item 62.PNG", DeviceInformation.screenWidth *197/1536, DeviceInformation.screenHeight *409/864,
                DeviceInformation.screenWidth *28/1536, DeviceInformation.screenHeight *25/864);
        createText("Charger");
        createButton("images/libraryImages/levelTwoWithOutlines/item 64.PNG", DeviceInformation.screenWidth *382/1536, DeviceInformation.screenHeight *393/864,
                DeviceInformation.screenWidth *55/1536, DeviceInformation.screenHeight *65/864);
        createText("Board");
        createButton("images/libraryImages/levelTwoWithOutlines/item 65.PNG", DeviceInformation.screenWidth *388/1536, DeviceInformation.screenHeight *222/864,
                DeviceInformation.screenWidth *26/1536, DeviceInformation.screenHeight *29/864);
        createText("Automata Theory Book");
        createButton("images/libraryImages/levelTwoWithOutlines/item 66.PNG", DeviceInformation.screenWidth *322/1536, DeviceInformation.screenHeight *365/864,
                DeviceInformation.screenWidth *42/1536, DeviceInformation.screenHeight *45/864);
        createText("Tab");
        createButton("images/libraryImages/levelTwoWithOutlines/item 68.PNG", DeviceInformation.screenWidth *474/1536, DeviceInformation.screenHeight *218/864,
                DeviceInformation.screenWidth *28/1536, DeviceInformation.screenHeight *40/864);
        createText("Clean Code Book");
        createButton("images/libraryImages/levelTwoWithOutlines/item 69.PNG", DeviceInformation.screenWidth *1233/1536, DeviceInformation.screenHeight *688/864,
                DeviceInformation.screenWidth *28/1536, DeviceInformation.screenHeight *19/864);
        createText("StopWatch");
        createButton("images/libraryImages/levelTwoWithOutlines/item 70.PNG", DeviceInformation.screenWidth *874/1536, DeviceInformation.screenHeight *370/864,
                DeviceInformation.screenWidth *20/1536, DeviceInformation.screenHeight *29/864);
        createText("Coffee cup");
        createButton("images/libraryImages/levelTwoWithOutlines/item 71.PNG", DeviceInformation.screenWidth *913/1536, DeviceInformation.screenHeight *218/864,
                DeviceInformation.screenWidth *18/1536, DeviceInformation.screenHeight *40/864);
        createText("Automata Theory");
        createButton("images/libraryImages/levelTwoWithOutlines/item 72.PNG", DeviceInformation.screenWidth *965/1536, DeviceInformation.screenHeight *235/864,
                DeviceInformation.screenWidth *48/1536, DeviceInformation.screenHeight *75/864);
        createText("Calendar");
        createButton("images/libraryImages/levelTwoWithOutlines/item 74.PNG", DeviceInformation.screenWidth *1035/1536, DeviceInformation.screenHeight *213/864,
                DeviceInformation.screenWidth *35/1536, DeviceInformation.screenHeight *42/864);
        createText("Linear Algebra");
        createButton("images/libraryImages/levelTwoWithOutlines/item 75.PNG", DeviceInformation.screenWidth *854/1536, DeviceInformation.screenHeight *710/864,
                DeviceInformation.screenWidth *38/1536, DeviceInformation.screenHeight *65/864);
        createText("Noodles");
        createButton("images/libraryImages/levelTwoWithOutlines/item 77.PNG", DeviceInformation.screenWidth *1165/1536, DeviceInformation.screenHeight *586/864,
                DeviceInformation.screenWidth *28/1536, DeviceInformation.screenHeight *25/864);
        createText("Mug");
        createButton("images/libraryImages/levelTwoWithOutlines/item 78.PNG", DeviceInformation.screenWidth *1350/1536, DeviceInformation.screenHeight *682/864,
                DeviceInformation.screenWidth *58/1536, DeviceInformation.screenHeight *75/864);
        createText(" Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 79.PNG", DeviceInformation.screenWidth *1422/1536, DeviceInformation.screenHeight *733/864,
                DeviceInformation.screenWidth *78/1536, DeviceInformation.screenHeight *35/864);
        createText("Cornflakes Box"); //dont know
        createButton("images/libraryImages/levelTwoWithOutlines/item 80.PNG", DeviceInformation.screenWidth *925/1536, DeviceInformation.screenHeight *574/864,
                DeviceInformation.screenWidth *35/1536, DeviceInformation.screenHeight *23/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 81.PNG", DeviceInformation.screenWidth *929/1536, DeviceInformation.screenHeight *746/864,
                DeviceInformation.screenWidth *78/1536, DeviceInformation.screenHeight *20/864);
        createText("Cornflakes Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 82.PNG", DeviceInformation.screenWidth *958/1536, DeviceInformation.screenHeight *588/864,
                DeviceInformation.screenWidth *48/1536, DeviceInformation.screenHeight *25/864);
        createText("Diary");
        createButton("images/libraryImages/levelTwoWithOutlines/item 83.PNG", DeviceInformation.screenWidth *1044/1536, DeviceInformation.screenHeight *557/864,
                DeviceInformation.screenWidth *78/1536, DeviceInformation.screenHeight *35/864);
        createText("Crayons Box");
        createButton("images/libraryImages/levelTwoWithOutlines/item 84.PNG", DeviceInformation.screenWidth *1041/1536, DeviceInformation.screenHeight *515/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *25/864);
        createText("Closed Diary");
        createButton("images/libraryImages/levelTwoWithOutlines/item 85.PNG", DeviceInformation.screenWidth *1126/1536, DeviceInformation.screenHeight *612/864,
                DeviceInformation.screenWidth *28/1536, DeviceInformation.screenHeight *35/864);
        createText("Coffee Cup");
        createButton("images/libraryImages/levelTwoWithOutlines/item 86.PNG", DeviceInformation.screenWidth *954/1536, DeviceInformation.screenHeight *705/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *25/864);
        createText("Script");
        createButton("images/libraryImages/levelTwoWithOutlines/item 87.PNG", DeviceInformation.screenWidth *1448/1536, DeviceInformation.screenHeight *710/864,
                DeviceInformation.screenWidth *74/1536, DeviceInformation.screenHeight *18/864);
        createText("Diary");
        createButton("images/libraryImages/levelTwoWithOutlines/item 88.PNG", DeviceInformation.screenWidth *1062/1536, DeviceInformation.screenHeight *711/864,
                DeviceInformation.screenWidth *60/1536, DeviceInformation.screenHeight *12/864);
        createText("Paper PLane");
        createButton("images/libraryImages/levelTwoWithOutlines/item 89.PNG", DeviceInformation.screenWidth *1235/1536, DeviceInformation.screenHeight *718/864,
                DeviceInformation.screenWidth *109/1536, DeviceInformation.screenHeight *48/864);
        createText("Jurassic PArk Book");
        createButton("images/libraryImages/levelTwoWithOutlines/item 90.PNG", DeviceInformation.screenWidth *1050/1536, DeviceInformation.screenHeight *606/864,
                DeviceInformation.screenWidth *64/1536, DeviceInformation.screenHeight *22/864);
        createText("A pair of glass");



        this.add(backgroundLabel);
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
        BigItemListAtBottomOfScreen.setBounds(0, DeviceInformation.screenHeight -(textBox_height*2), DeviceInformation.screenWidth, textBox_height*2);
        BigItemListAtBottomOfScreen.setBackground(Color.decode("#14171C"));
        BigItemListAtBottomOfScreen.setForeground(Color.white);
        BigItemListAtBottomOfScreen.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.decode("#14171C"),3), BorderFactory.createLineBorder(Color.white,3)));
        BigItemListAtBottomOfScreen.setFont(FontInfo.getResizedFont(29f));
        BigItemListAtBottomOfScreen.setOpaque(true);
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
                                    ((LibrarySceneT)scenePanel).timerLabel.isTimeOver = true;

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




    @Override
    public void startScene() {
        messNotification.setVisible(true);
        timerLabel.setVisible(false);
        scoreBoard.setVisible(false);
        MakeRandomItemListAtBottomOfScreen();
        InnitiallyClicked = false;
        resetVariables();
        ResetTimerAndScore();
    }



    @Override
    public void PrepareForSceneTransition(LoadingAnimationT loadingAnimationT, MapT mapT) {
        this.loadingAnimationT = loadingAnimationT;
        this.mapT = mapT;
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
     //   revalidate();
     //   repaint();
        scoreBoard.setVisible(false);
    //    revalidate();
    //    repaint();

        musicPlayer = new MusicPlayer();
      //  musicPlayer.playMusic(music);
    }

}

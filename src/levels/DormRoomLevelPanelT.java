package src.levels;

import src.*;
import src.buttons.*;
import src.events.SceneObjectEvents;
import src.levelObjects.ScoreBoard;
import src.levelObjects.TimerLabel;
import src.popups.LevelFinishDialog;
import src.setup.DeviceInformation;
import src.setup.FontInfo;
import src.setup.RandomGenerator;
import src.transitionPanels.LoadingAnimationT;
import src.transitionPanels.MapT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.*;


public class DormRoomLevelPanelT extends ALevelPanel implements Runnable{
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

//    public ArrayList<JLabel> imageList = new ArrayList<>(); // array of images of items we may need to find
//    public ArrayList<ObjectHidingButton> buttonList = new ArrayList<>(); //all the buttons for the objects are in this
//    public ArrayList<String> textList = new ArrayList<>(); //all the names of the objects are in this\
//    ArrayList<JLabel> ListOfAllItemNamesAsLabels = new ArrayList<>(); //the labels containing Strings of 'item names' that were randomly chosen
//    public ArrayList<Integer> RandObjIndices; //an array of 6 integers, instantiated randomly.
                                            // Each integer indicates the index of the Object in the imageList
                                            // that The player has to find in this round.
    JButton messNotification; // notification that someone messed up your dorm room
    RandomGenerator randomGenerator;
    boolean InnitiallyClicked = false;
    public DormRoomLevelPanelT(JFrame jFrame){
        this.jFrame = jFrame;
        levelFinished = false;
        maxBounds = DeviceInformation.graphicsEnvironment.getMaximumWindowBounds();
        textBox_height = DeviceInformation.screenHeight*50/864;
        this.setLayout(null);

    }

    @Override
    public void run() {
        buildScene();
    }

    public void buildScene(){
        createConfettiScreen();
        MessNotification();
        setupShowGottenScore();
        setupHintAnimationGif();
        createBackground("images/dormImages/LevelOneMain.png");

        addCustomWindowCloseButton();
        timerLabel = new TimerLabel(jFrame, this);
        timerLabel.setVisible(false);
        revalidate();
        repaint();

        scoreBoard = new ScoreBoard(jFrame, this);
        scoreBoard.setVisible(false);
        revalidate();
        repaint();

        repaint();

        imagesFound=0;
        generateScreenWithAllObjectsAndButtons();

        repaint();



        music = getClass().getResource("/background_music/bgmusic.wav");
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

        int sizeX = 200;
        int sizeY = 200;

        ImageIcon gif = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("images/Gifs/playing_brown_cat.gif")));
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
        messNotification = new JButton("<html>Oh No, The room looks like it got ransacked?! Where is my present?<br/> Guess I'll have to tidy up (Tap to Search)</html>");

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

    public  void createBackground(String bgfilename){
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
        createButton("images/dormImages/01.png", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *428/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *85/864);
        createText("Cornflakes Box");
        createButton("images/dormImages/02.png", DeviceInformation.screenWidth *1188/1536, DeviceInformation.screenHeight *493/864,
                DeviceInformation.screenWidth *18/1536, DeviceInformation.screenHeight *37/864);
        createText("CocaCola Can");
        createButton("images/dormImages/03.png", DeviceInformation.screenWidth *1235/1536, DeviceInformation.screenHeight *400/864,
                DeviceInformation.screenWidth *86/1536, DeviceInformation.screenHeight *65/864);
        createText("Shoulder Bag");
        createButton("images/dormImages/04.png", DeviceInformation.screenWidth *1130/1536, DeviceInformation.screenHeight *730/864,
                DeviceInformation.screenWidth *67/1536, DeviceInformation.screenHeight *26/864);
        createText("HeadPhone");
        createButton("images/dormImages/05.png", DeviceInformation.screenWidth *325/1536, DeviceInformation.screenHeight *728/864,
                DeviceInformation.screenWidth *43/1536, DeviceInformation.screenHeight *32/864);
        createText("Phone");
        createButton("images/dormImages/06.png", DeviceInformation.screenWidth *419/1536, DeviceInformation.screenHeight *672/864,
                DeviceInformation.screenWidth *60/1536, DeviceInformation.screenHeight *19/864);
        createText("Calculator");
        createButton("images/dormImages/07.png", DeviceInformation.screenWidth *1102/1536, DeviceInformation.screenHeight *703/864,
                DeviceInformation.screenWidth *70/1536, DeviceInformation.screenHeight *25/864);
        createText("Sunglasses");
        createButton("images/dormImages/08.png", DeviceInformation.screenWidth *850/1536, DeviceInformation.screenHeight *384/864,
                DeviceInformation.screenWidth *40/1536, DeviceInformation.screenHeight *15/864);
        createText("Garbage");
        createButton("images/dormImages/09.png", DeviceInformation.screenWidth *482/1536, DeviceInformation.screenHeight *348/864,
                DeviceInformation.screenWidth *40/1536, DeviceInformation.screenHeight *48/864);
        createText("Toilet Paper");
        createButton("images/dormImages/10.png", DeviceInformation.screenWidth *542/1536, DeviceInformation.screenHeight *410/864,
                DeviceInformation.screenWidth *72/1536, DeviceInformation.screenHeight *26/864);
        createText("Food");
        createButton("images/dormImages/11.png", DeviceInformation.screenWidth *95/1536, DeviceInformation.screenHeight *737/864,
                DeviceInformation.screenWidth *168/1536, DeviceInformation.screenHeight *27/864);
        createText("Blanket");
        createButton("images/dormImages/12.png", DeviceInformation.screenWidth *740/1536, DeviceInformation.screenHeight *520/864,
                DeviceInformation.screenWidth *90/1536, DeviceInformation.screenHeight *90/864);
        createText("BackPack");
        createButton("images/dormImages/13.png", DeviceInformation.screenWidth *515/1536, DeviceInformation.screenHeight *595/864,
                DeviceInformation.screenWidth *42/1536, DeviceInformation.screenHeight *80/864);
        createText("Cloth Pile");
        createButton("images/dormImages/14.png", DeviceInformation.screenWidth *190/1536, DeviceInformation.screenHeight *425/864,
                DeviceInformation.screenWidth *85/1536, DeviceInformation.screenHeight *103/864);
        createText("Pizza Box");
        createButton("images/dormImages/15.png", DeviceInformation.screenWidth *1033/1536, DeviceInformation.screenHeight *417/864,
                DeviceInformation.screenWidth *32/1536, DeviceInformation.screenHeight *17/864);
        createText("Folded Clothes");
        createButton("images/dormImages/16.png", DeviceInformation.screenWidth *894/1536, DeviceInformation.screenHeight *505/864,
                DeviceInformation.screenWidth *28/1536, DeviceInformation.screenHeight *28/864);
        createText("Chips Packet");
        createButton("images/dormImages/17.png", DeviceInformation.screenWidth *1204/1536, DeviceInformation.screenHeight *725/864,
                DeviceInformation.screenWidth *110/1536, DeviceInformation.screenHeight *43/864);
        createText("Unfolded Cloth");
        createButton("images/dormImages/18.png", DeviceInformation.screenWidth *722/1536, 0 /864,
                DeviceInformation.screenWidth *165/1536, DeviceInformation.screenHeight *23/864);
        createText("Quilt");
        createButton("images/dormImages/19.png", DeviceInformation.screenWidth *924/1536, DeviceInformation.screenHeight *500/864,
                DeviceInformation.screenWidth *43/1536, DeviceInformation.screenHeight *20/864);
        createText("Book");
        createButton("images/dormImages/20.png", DeviceInformation.screenWidth *1365/1536, DeviceInformation.screenHeight *751/864,
                DeviceInformation.screenWidth *70/1536, DeviceInformation.screenHeight *19/864);
        createText("FoodPlate");
        createButton("images/dormImages/21.png", DeviceInformation.screenWidth *934/1536, DeviceInformation.screenHeight *456/864,
                DeviceInformation.screenWidth *44/1536, DeviceInformation.screenHeight *33/864);
        createText("Shoes");
        createButton("images/dormImages/22.png", DeviceInformation.screenWidth *489/1536, DeviceInformation.screenHeight *400/864,
                DeviceInformation.screenWidth *26/1536, DeviceInformation.screenHeight *20/864);
        createText("TeaCup");

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
        mapT.MaxDormScore = Math.max(scoreBoard.score, mapT.MaxDormScore);
        mapT.updateScore();

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

    public void  createButton(String image,int posx, int posy, int sizex,int sizey) {
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
                                HintWasUsed = false;
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

                            timerLabel.AnimateScore(e.getPoint());

                            setEnabled(false);
                            ListOfAllItemNamesAsLabels.get(myIndex).setVisible(false);
                            if(imagesFound == 6){
                                if(scenePanel instanceof ALevelPanel){
                                    ((DormRoomLevelPanelT)scenePanel).timerLabel.isTimeOver = true;

                                }
                                imagesFound=0;
                                congratulationsConfetti.setVisible(true);
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
//        revalidate();
//        repaint();
        scoreBoard.setVisible(false);
//        revalidate();
//        repaint();

        Sound sound = new Sound();







            musicPlayer = new MusicPlayer();
            musicPlayer.playMusic(music);
    }

    /**TODO:
    disable buttons while pop up is there
     add Combo textBox
     */














    /** unneeded functions */


//    private void DormThings() {
//        addCustomWindowCloseButton();
//        TimerLabel timerLabel = new TimerLabel(jFrame, this, deviceInfo, fontInfo);
//        TextBox textBox = new TextBox(jFrame, this, deviceInfo, fontInfo);
//        ScoreBoard scoreBoard = new ScoreBoard(jFrame, this, deviceInfo, fontInfo);
//
//    }
//    public void  createObject( String image ) {
//        JLabel objectLabel = new JLabel();
//        objectLabel.setBounds(0,-textBox_height,maxBounds.width,maxBounds.height);
//
//        ImageIcon  obj1icon= new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(image)));
//        Image image1 = obj1icon.getImage();
//        image1 = image1.getScaledInstance(maxBounds.width, maxBounds.height-100, Image.SCALE_DEFAULT);
//        obj1icon = new ImageIcon(image1);
//
//        objectLabel.setIcon(obj1icon);
//        imageList.add(objectLabel);
//        this.add(objectLabel);
//
//    }
//    public void prepareEndOfLevel(LoadingAnimationT loadingAnimationT, JPanel nextScene){
//        timerLabel.loadingAnimationT = loadingAnimationT;
//        timerLabel.nextScene = nextScene;
//    }




}

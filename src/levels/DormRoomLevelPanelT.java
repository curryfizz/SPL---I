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

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;


public class DormRoomLevelPanelT extends ALevelPanel implements Runnable{
    JFrame jFrame;
    Rectangle maxBounds;
    public int score = 0;
    int currentCombo = 0;

    int timeSinceLastFind = 0;
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

        try {
            buildScene();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void buildScene() throws IOException {
        createConfettiScreen();
        MessNotification();
        setupShowGottenScore();
        setupHintAnimationGif();
        createBackground("images/dormImages/LevelOneMain.png");
        createSound("levelOneBackground_v2");
//        sound = new Sound();
//        sound.setFile("levelOneBackground_v2");
        addCustomWindowCloseButton();
        addAudioButton();
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



    }


    private void setupHintAnimationGif() {

        int sizeX = 200;
        int sizeY = 200;

        ImageIcon gif = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("images/Gifs/playing_brown_cat.gif")));
        gif.setImage(gif.getImage().getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT));
        HintAnimationGif = new JLabel();
        HintAnimationGif.setBounds(600,500, sizeX,sizeY);

        HintAnimationGif.setIcon(gif);
        HintAnimationGif.setVisible(true);
        HintAnimationGif.setVisible(false);
        this.add(HintAnimationGif);
    }

    private void setupShowGottenScore() {
        ShowGottenScore = new JLabel("", SwingConstants.CENTER);
        ShowGottenScore.setBounds(500,400, 50, 30);
        ShowGottenScore.setBackground(null);
        ShowGottenScore.setFont(FontInfo.getResizedFont(29f));
//        ShowGottenScore.setForeground(new Color(30, 120, 20));
        ShowGottenScore.setForeground(new Color(255,0,0));
        ShowGottenScore.setVisible(false);
        ShowGottenScore.setOpaque(false);
        this.add(ShowGottenScore);
    }

    public void MessNotification(){
        messNotification = new JButton("<html>Oh No, The room looks like it got ransacked?! Where is my present?<br/> Guess I'll have to tidy up (Tap to Search)</html>");

        messNotification.setFont(FontInfo.getResizedFont(34f));
        messNotification.setFocusPainted(false);
        messNotification.setEnabled(false);
        messNotification.setBounds(0, DeviceInformation.screenHeight -120, DeviceInformation.screenWidth, 120);
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

    public  void createBackground(String bgfilename) throws IOException {
        this.setLayout(null);
        this.setBounds(0, 0, maxBounds.width, maxBounds.height);//size of the background image
        this.setBackground(Color.black);

        BufferedImage bufferedImage = ImageIO.read(new File(bgfilename));
        Image image = bufferedImage.getScaledInstance(maxBounds.width, maxBounds.height-textBox_height, Image.SCALE_DEFAULT);//        imageIcon = new ImageIcon(image);
        ImageIcon imageIcon = new ImageIcon(image);
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


    public void addAudioButton(){
        AudioChangeButton audioChangeButton = new AudioChangeButton(jFrame,sound);
        this.add(audioChangeButton);
        this.repaint();
        this.revalidate();
    }



    public int getX(int x){
        return DeviceInformation.screenWidth*x/1536;
    }
    
    public int getY(int y){
        return  DeviceInformation.screenHeight*y/864;
    }

    public  void generateScreenWithAllObjectsAndButtons() throws IOException {
        createButton("images/dormImages/01.png", getX(308), getY(428), getX(68), getY(85));
        createText("Cornflakes Box");
        createButton("images/dormImages/02.png", getX(1188), getY(493), getX(18), getY(37));
        createText("CocaCola Can");
        createButton("images/dormImages/03.png", getX(1235), getY(400), getX(86), getY(65));
        createText("Shoulder Bag");
        createButton("images/dormImages/04.png", getX(1130), getY(730), getX(67), getY(26));
        createText("HeadPhone");
        createButton("images/dormImages/05.png", getX(325), getY(728), getX(43), getY(32));
        createText("Phone");
        createButton("images/dormImages/06.png", getX(419), getY(672), getX(60), getY(19));
        createText("Calculator");
        createButton("images/dormImages/07.png", getX(1102), getY(703), getX(70), getY(25));
        createText("Sunglasses");
        createButton("images/dormImages/08.png", getX(850), getY(384), getX(40), getY(15));
        createText("Garbage");
        createButton("images/dormImages/09.png", getX(482), getY(348), getX(40), getY(48));
        createText("Toilet Paper");
        createButton("images/dormImages/10.png", getX(542), getY(410), getX(72), getY(26));
        createText("Food");
        createButton("images/dormImages/11.png", getX(95), getY(737), getX(168), getY(27));
        createText("Blanket");
        createButton("images/dormImages/12.png", getX(740), getY(520), getX(90), getY(90));
        createText("BackPack");
        createButton("images/dormImages/13.png", getX(515), getY(595), getX(42), getY(80));
        createText("Cloth Pile");
        createButton("images/dormImages/14.png", getX(190), getY(425), getX(85), getY(103));
        createText("Pizza Box");
        createButton("images/dormImages/15.png", getX(1033), getY(417), getX(32), getY(17));
        createText("Folded Clothes");
        createButton("images/dormImages/16.png", getX(894), getY(505), getX(28), getY(28));
        createText("Chips Packet");
        createButton("images/dormImages/17.png", getX(1204), getY(725), getX(110), getY(43));
        createText("Unfolded Cloth");
        createButton("images/dormImages/18.png", getX(722), getY(0) , getX(165), getY(23));
        createText("Quilt");
        createButton("images/dormImages/19.png", getX(924), getY(500), getX(43), getY(20));
        createText("Book");
        createButton("images/dormImages/20.png", getX(1365), getY(751), getX(70), getY(19));
        createText("FoodPlate");
        createButton("images/dormImages/21.png", getX(934), getY(456), getX(44), getY(33));
        createText("Shoes");
        createButton("images/dormImages/22.png", getX(489), getY(400), getX(26), getY(20));
        createText("TeaCup");

        this.add(backgroundLabel);
    }
    private void CreateAListWithAllItemNamesAsLabels() {

        for (String s : textList) {
            JLabel temp = new JLabel(s, SwingConstants.CENTER);
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
        for (Integer randObjIndex : RandObjIndices) {
            index = randObjIndex;
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
        timerLabel.st_alpha=255;
        sound.stop();
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
    }


    public JLabel createObject1(String image) throws IOException{
        JLabel objectLabel = new JLabel();
        objectLabel.setBounds(0,0,maxBounds.width,maxBounds.height-textBox_height);

        BufferedImage bufferedImage = ImageIO.read(new File(image));
//        ImageIcon  obj1icon= new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(image)));
//        Image image1 = .getImage();

        Image image1 = bufferedImage.getScaledInstance(maxBounds.width, maxBounds.height-textBox_height, Image.SCALE_DEFAULT);
//        obj1icon = new ImageIcon(image1);
//
        ImageIcon obj1icon = new ImageIcon(image1);

        objectLabel.setIcon(obj1icon);
        imageList.add(objectLabel);
        return objectLabel;

    }

    public void  createButton(String image,int posx, int posy, int sizex,int sizey) throws IOException {
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

                            ShowGottenScore.setText("+" + gottenScore);
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
        sound.play();
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
        ShowGottenScore.setVisible(false);

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

//
//        musicPlayer = new MusicPlayer();
//            musicPlayer.playMusic(music);
    }




}

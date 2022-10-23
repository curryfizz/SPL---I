package src;

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
    CloseButton closeButton;
    JLabel backgroundLabel;
    JLabel BigItemListAtBottomOfScreen;
    LoadingAnimationT loadingAnimationT;
    MapT mapT;
    URL music;
    MusicPlayer musicPlayer;

    boolean levelFinished;
    int imagesFound;
    int textBox_height;
    public ArrayList<JLabel> imageList = new ArrayList<>(); // array of images of items we may need to find
    public ArrayList<ObjectHidingButton> buttonList = new ArrayList<>(); //all the buttons for the objects are in this
    public ArrayList<String> textList = new ArrayList<>(); //all the names of the objects are in this\
    ArrayList<JLabel> ListOfAllItemNamesAsLabels = new ArrayList<>(); //the labels containing Strings of 'item names' that were randomly chosen
    public ArrayList<Integer> RandObjIndices; //an array of 6 integers, instantiated randomly.
                                            // Each integer indicates the index of the Object in the imageList
                                            // that The player has to find in this round.
    JButton messNotification; // notification that someone messed up your dorm room
    RandomGenerator randomGenerator;
    boolean tapped = false;
    public DormRoomLevelPanelT(JFrame jFrame){
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
        createBackground("images/LevelOneMain.png");

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
                if(!tapped) {
                    timerLabel.setVisible(true);
                    scoreBoard.setVisible(true);
                    revalidate();
                    repaint();
                    enableObjectButtons();
                    messNotification.setVisible(false);
                    timerLabel.StartTimer();
                    tapped = true;
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
        this.setBounds(0, 0, maxBounds.width, maxBounds.height);//size of the background image
        this.setBackground(Color.black);
        this.setLayout(null);

        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(bgfilename)));
        Image image = imageIcon.getImage();
        image = image.getScaledInstance(maxBounds.width, maxBounds.height, Image.SCALE_DEFAULT);
        imageIcon = new ImageIcon(image);
        backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0,-textBox_height, maxBounds.width,maxBounds.height);
        backgroundLabel.setIcon(imageIcon);
    }

    public void addCustomWindowCloseButton(){
        LevelCloseButton levelCloseButton = new LevelCloseButton("X",jFrame,this);
        this.add(levelCloseButton);
        this.repaint();
        this.revalidate();
    }

    public  void generateScreenWithAllObjectsAndButtons() {
        createButton("images/01.png", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *410/864,
                DeviceInformation.screenWidth *68/1536, DeviceInformation.screenHeight *90/864);
        createText("Cornflakes Box");
        createButton("images/02.png", DeviceInformation.screenWidth *1188/1536, DeviceInformation.screenHeight *473/864,
                DeviceInformation.screenWidth *18/1536, DeviceInformation.screenHeight *37/864);
        createText("CocaCola Can");
        createButton("images/03.png", DeviceInformation.screenWidth *1235/1536, DeviceInformation.screenHeight *385/864,
                DeviceInformation.screenWidth *86/1536, DeviceInformation.screenHeight *65/864);
        createText("Shoulder Bag");
        createButton("images/04.png", DeviceInformation.screenWidth *1130/1536, DeviceInformation.screenHeight *740/864,
                DeviceInformation.screenWidth *67/1536, DeviceInformation.screenHeight *26/864);
        createText("HeadPhone");
        createButton("images/05.png", DeviceInformation.screenWidth *325/1536, DeviceInformation.screenHeight *728/864,
                DeviceInformation.screenWidth *43/1536, DeviceInformation.screenHeight *32/864);
        createText("Phone");
        createButton("images/06.png", DeviceInformation.screenWidth *419/1536, DeviceInformation.screenHeight *667/864,
                DeviceInformation.screenWidth *60/1536, DeviceInformation.screenHeight *16/864);
        createText("Calculator");
        createButton("images/07.png", DeviceInformation.screenWidth *1102/1536, DeviceInformation.screenHeight *703/864,
                DeviceInformation.screenWidth *70/1536, DeviceInformation.screenHeight *22/864);
        createText("Sunglasses");
        createButton("images/08.png", DeviceInformation.screenWidth *850/1536, DeviceInformation.screenHeight *352/864,
                DeviceInformation.screenWidth *40/1536, DeviceInformation.screenHeight *15/864);
        createText("Garbage");
        createButton("images/09.png", DeviceInformation.screenWidth *482/1536, DeviceInformation.screenHeight *328/864,
                DeviceInformation.screenWidth *40/1536, DeviceInformation.screenHeight *48/864);
        createText("Toilet Paper");
        createButton("images/10.png", DeviceInformation.screenWidth *542/1536, DeviceInformation.screenHeight *390/864,
                DeviceInformation.screenWidth *72/1536, DeviceInformation.screenHeight *26/864);
        createText("Food");
        createButton("images/11.png", DeviceInformation.screenWidth *95/1536, DeviceInformation.screenHeight *737/864,
                DeviceInformation.screenWidth *168/1536, DeviceInformation.screenHeight *27/864);
        createText("Blanket");
        createButton("images/12.png", DeviceInformation.screenWidth *740/1536, DeviceInformation.screenHeight *510/864,
                DeviceInformation.screenWidth *90/1536, DeviceInformation.screenHeight *90/864);
        createText("BackPack");
        createButton("images/13.png", DeviceInformation.screenWidth *515/1536, DeviceInformation.screenHeight *585/864,
                DeviceInformation.screenWidth *42/1536, DeviceInformation.screenHeight *80/864);
        createText("Cloth Pile");
        createButton("images/14.png", DeviceInformation.screenWidth *190/1536, DeviceInformation.screenHeight *410/864,
                DeviceInformation.screenWidth *85/1536, DeviceInformation.screenHeight *103/864);
        createText("Pizza Box");
        createButton("images/15.png", DeviceInformation.screenWidth *1033/1536, DeviceInformation.screenHeight *393/864,
                DeviceInformation.screenWidth *32/1536, DeviceInformation.screenHeight *17/864);
        createText("Folded Clothes");
        createButton("images/16.png", DeviceInformation.screenWidth *894/1536, DeviceInformation.screenHeight *482/864,
                DeviceInformation.screenWidth *28/1536, DeviceInformation.screenHeight *23/864);
        createText("Chips Packet");
        createButton("images/17.png", DeviceInformation.screenWidth *1204/1536, DeviceInformation.screenHeight *725/864,
                DeviceInformation.screenWidth *110/1536, DeviceInformation.screenHeight *43/864);
        createText("Unfolded Cloth");
        createButton("images/18.png", DeviceInformation.screenWidth *722/1536, 0 /864,
                DeviceInformation.screenWidth *165/1536, DeviceInformation.screenHeight *23/864);
        createText("Quilt");
        createButton("images/19.png", DeviceInformation.screenWidth *924/1536, DeviceInformation.screenHeight *480/864,
                DeviceInformation.screenWidth *43/1536, DeviceInformation.screenHeight *20/864);
        createText("Book");
        createButton("images/20.png", DeviceInformation.screenWidth *1365/1536, DeviceInformation.screenHeight *746/864,
                DeviceInformation.screenWidth *70/1536, DeviceInformation.screenHeight *19/864);
        createText("FoodPlate");
        createButton("images/21.png", DeviceInformation.screenWidth *934/1536, DeviceInformation.screenHeight *436/864,
                DeviceInformation.screenWidth *44/1536, DeviceInformation.screenHeight *33/864);
        createText("Shoes");
        createButton("images/22.png", DeviceInformation.screenWidth *489/1536, DeviceInformation.screenHeight *380/864,
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
        BigItemListAtBottomOfScreen.setBounds(0, DeviceInformation.screenHeight -100, DeviceInformation.screenWidth, 100);
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
//        bigItemListLabel.add(new JLabel());
        for(int i=0; i<RandObjIndices.size(); i++){
            index = RandObjIndices.get(i);
            BigItemListAtBottomOfScreen.add(ListOfAllItemNamesAsLabels.get(index));
//            buttonList.get(index).setEnabled(true);
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
        revalidate();
        repaint();

        jFrame.remove(this);

        loadingAnimationT.changeNextScene(mapT);
        ((MapT) loadingAnimationT.nextScene).score += score; // won't give compile time casting error bc I JUST CHANGED IT TO MAPT
        ((MapT) loadingAnimationT.nextScene).updateScore();

        jFrame.add(loadingAnimationT);
        loadingAnimationT.initializeTimer();
        jFrame.revalidate();
        jFrame.repaint();
        musicPlayer.stop(music);
    }


    public JLabel createObject1(String image){
        JLabel objectLabel = new JLabel();
        objectLabel.setBounds(0,-textBox_height,maxBounds.width,maxBounds.height);

        ImageIcon  obj1icon= new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(image)));
        Image image1 = obj1icon.getImage();
        image1 = image1.getScaledInstance(maxBounds.width, maxBounds.height, Image.SCALE_DEFAULT);
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
                            score += 100;
                            imagesFound+=1;
                            scoreBoard.setText(" 0" + score);
                            scoreBoard.setHorizontalTextPosition(SwingConstants.CENTER);
                            scoreBoard.repaint();
                            repaint();
                            setEnabled(false);
                            ListOfAllItemNamesAsLabels.get(myIndex).setVisible(false);
                            if(imagesFound == 6){
                                if(scenePanel instanceof DormRoomLevelPanelT){
                                    ((DormRoomLevelPanelT)scenePanel).timerLabel.isTimeOver = true;
                                    ((DormRoomLevelPanelT)scenePanel).timerLabel.score = score;


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
        tapped = false;
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
        timerLabel.score=0;
        scoreBoard.setText("0000");
        imagesFound = 0;
        score=0;

    }

    public void resetVariables(){

        timerLabel.isTimeOver = false;
        timerLabel.score=0;

        timerLabel.setVisible(false);
//        revalidate();
//        repaint();
        scoreBoard.setVisible(false);
//        revalidate();
//        repaint();

        musicPlayer = new MusicPlayer();
        musicPlayer.playMusic(music);
    }

    /**TODO:
    make text box, score, timer appear after pop up is gone
    make timer start after pop up is gone
    disable buttons while pop up is there
    make popup more noticible
    make sure popup is gone properly and buttons behind can be accessed (otherwise instead of set visible, try remove)
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

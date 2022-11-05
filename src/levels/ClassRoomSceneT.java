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
import java.util.Objects;

public class ClassRoomSceneT extends ALevelPanel implements Runnable{

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
    URL music = getClass().getResource("/background_music/library.wav");
    MusicPlayer musicPlayer;
    boolean levelFinished;
    int imagesFound;
    int textBox_height;


    JButton messNotification; // notification that someone messed up your dorm room
    RandomGenerator randomGenerator;
    boolean InnitiallyClicked = false;


    public ClassRoomSceneT(JFrame jFrame){
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

    public void buildScene() {
        MessNotification();
        setupShowGottenScore();
        setupHintAnimationGif();
        createBackground("images/Classroom images/classroomMain.PNG");
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

        imagesFound = 0;
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
        HintAnimationGif.setOpaque(true);
        HintAnimationGif.setVisible(false);
        this.add(HintAnimationGif);
    }

    private void setupShowGottenScore() {
        ShowGottenScore = new JLabel("", SwingConstants.CENTER);
        ShowGottenScore.setBounds(500,400, 50, 30);
        ShowGottenScore.setBackground(null);
        ShowGottenScore.setFont(FontInfo.getResizedFont(29f));
        ShowGottenScore.setForeground(Color.decode("#ffff00"));
        ShowGottenScore.setVisible(false);
        ShowGottenScore.setOpaque(false);
        this.add(ShowGottenScore);
    }

    public void MessNotification(){
        messNotification = new JButton("<html>Oh No, The Classroom is very difficult to find  my things.<br/> Guess I'll have to look for my present (Tap to Search)</html>");
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
                System.out.println(Integer.toString(e.getX()) + " " + Integer.toString(e.getY()));
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

        createButton("images/Classroom images/classroom objects/item 0.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("<html>Brown Coffee<br/> cup </html>");
        createButton("images/Classroom images/classroom objects/item 1.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Pencil");
        createButton("images/Classroom images/classroom objects/item 2.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Chips Packet");
        createButton("images/Classroom images/classroom objects/item 3.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Eraser");
        createButton("images/Classroom images/classroom objects/item 4.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Pokemon Ball");
        createButton("images/Classroom images/classroom objects/item 5.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Orange");
        createButton("images/Classroom images/classroom objects/item 6.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Lamp");
        createButton("images/Classroom images/classroom objects/item 7.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Tissue Box");
        createButton("images/Classroom images/classroom objects/item 8.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Milk Bottle");
        createButton("images/Classroom images/classroom objects/item 9.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Brown Coffee cup");
        createButton("images/Classroom images/classroom objects/item 10.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Water Bottle");



        createButton("images/Classroom images/classroom objects/item 11.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Red Mug");
        createButton("images/Classroom images/classroom objects/item 12.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("keyBoard");
        createButton("images/Classroom images/classroom objects/item 13.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Kitkat");
        createButton("images/Classroom images/classroom objects/item 14.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Diary");
        createButton("images/Classroom images/classroom objects/item 15.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Banana");
        createButton("images/Classroom images/classroom objects/item 16.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Among us");
        createButton("images/Classroom images/classroom objects/item 17.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Paper Packet");
        createButton("images/Classroom images/classroom objects/item 18.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Coca Cola bottle");
        createButton("images/Classroom images/classroom objects/item 19.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Side Bag");
        createButton("images/Classroom images/classroom objects/item 20.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Painting");



        createButton("images/Classroom images/classroom objects/item 21.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Clock");
        createButton("images/Classroom images/classroom objects/item 22.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Calendar");
        createButton("images/Classroom images/classroom objects/item 23.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Wallet");
        createButton("images/Classroom images/classroom objects/item 24.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Nike Bag");
        createButton("images/Classroom images/classroom objects/item 25.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Cap");
        createButton("images/Classroom images/classroom objects/item 26.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Hersheys");
        createButton("images/Classroom images/classroom objects/item 27.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Neck Pillow");
        createButton("images/Classroom images/classroom objects/item 28.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Duck");
        //need to check
        createButton("images/Classroom images/classroom objects/item 29.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("<html>Black and white <br/> Photo </html>");
        createButton("images/Classroom images/classroom objects/item 30.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Mango Juice");



        createButton("images/Classroom images/classroom objects/item 31.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Cards");
        createButton("images/Classroom images/classroom objects/item 32.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Donuts");
        createButton("images/Classroom images/classroom objects/item 33.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Open Diary");
        //need to know
        createButton("images/Classroom images/classroom objects/item 34.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("<html>Black and white <br/> Photo </html>");
        createButton("images/Classroom images/classroom objects/item 35.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Pokemon");
        createButton("images/Classroom images/classroom objects/item 36.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Pink Book");
        createButton("images/Classroom images/classroom objects/item 37.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Dice");
        createButton("images/Classroom images/classroom objects/item 38.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Sock");
        createButton("images/Classroom images/classroom objects/item 39.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Timer");
        //need to know
        createButton("images/Classroom images/classroom objects/item 40.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("<html>Black and white <br/> Photo </html>");




        createButton("images/Classroom images/classroom objects/item 41.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Closed Paper Bag");
        createButton("images/Classroom images/classroom objects/item 42.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Mug");
        //need to know
        createButton("images/Classroom images/classroom objects/item 43.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("<html>Black and white <br/> Photo </html>");


        createButton("images/Classroom images/classroom objects/item 45.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Puzzle");
        createButton("images/Classroom images/classroom objects/item 46.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Stack of Books");
        createButton("images/Classroom images/classroom objects/item 47.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Yellow Box");
        createButton("images/Classroom images/classroom objects/item 48.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Uno Blue Card");
        createButton("images/Classroom images/classroom objects/item 49.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Chess Board");
        createButton("images/Classroom images/classroom objects/item 50.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("FootBall");




        createButton("images/Classroom images/classroom objects/item 51.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Red Bull Can ");
        createButton("images/Classroom images/classroom objects/item 52.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Pizza Box");
        createButton("images/Classroom images/classroom objects/item 53.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Shopping Bag");
        createButton("images/Classroom images/classroom objects/item 54.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Scissors");
        createButton("images/Classroom images/classroom objects/item 55.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("<html>Stack of books <br/> On the table </html>");
        createButton("images/Classroom images/classroom objects/item 56.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Tab");
        createButton("images/Classroom images/classroom objects/item 57.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("File");
        createButton("images/Classroom images/classroom objects/item 58.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("<html>Black and white <br/> Photo </html>");
        createButton("images/Classroom images/classroom objects/item 59.PNG", DeviceInformation.screenWidth *308/1536, DeviceInformation.screenHeight *741/864,
                DeviceInformation.screenWidth *45/1536, DeviceInformation.screenHeight *10/864);
        createText("Uno Reverse");








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
        mapT.MaxClassroomScore= Math.max(scoreBoard.score, mapT.MaxClassroomScore);
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
        objectLabel.setVisible(true);
        return objectLabel;

    }

    public void createObject2(String image){
        JLabel objectLabel = new JLabel();
        objectLabel.setBounds(0,0,maxBounds.width,maxBounds.height-textBox_height);

        ImageIcon  obj1icon= new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(image)));
        Image image1 = obj1icon.getImage();
        image1 = image1.getScaledInstance(maxBounds.width, maxBounds.height-textBox_height, Image.SCALE_DEFAULT);
        obj1icon = new ImageIcon(image1);

        objectLabel.setIcon(obj1icon);
        objectLabel.setVisible(true);
        this.add(objectLabel);

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

                            timerLabel.AnimateScore(e.getPoint());

                            setEnabled(false);
                            ListOfAllItemNamesAsLabels.get(myIndex).setVisible(false);
                            if(imagesFound == 6){
                                if(scenePanel instanceof ALevelPanel){
                                    ((ClassRoomSceneT)scenePanel).timerLabel.isTimeOver = true;

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
        musicPlayer.playMusic(music);

    }


}


package src;

import shelved_classes.IScene;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class DormRoomSceneT extends JPanel implements Runnable, IScene {

    JFrame jFrame;
    DeviceScreenInformation deviceInfo;
    FontInfo fontInfo;
    Rectangle maxBounds;

    boolean isTimerOver;
    JLabel bigItemListLabel;
    public int score = 0;
    CloseButton closeButton;
    JLabel backgroundLabel;
    TimerLabel timerLabel;
    TextBox textBox;
    ScoreBoard scoreBoard;
    URL music;

    boolean levelFinished;
    int imagesFound;
    int offset;
    int textBox_height;
    public ArrayList<JLabel> imageList = new ArrayList<>();
    public ArrayList<ObjectHidingButton> buttonList = new ArrayList<>(); //all the buttons for the objects are in this
    public ArrayList<String> textList = new ArrayList<>(); //all the names of the objects are in this\
    ArrayList<JLabel> itemNameLabelList = new ArrayList<>(); //the labels containing item names that were randomly chosen
    public ArrayList<Integer> RandObjIndices;
    JButton messNotification;
    RandomGenerator randomGenerator;
    public  DormRoomSceneT(JFrame jFrame, DeviceScreenInformation deviceInfo, FontInfo fontInfo){
        this.jFrame = jFrame;
        this.deviceInfo = deviceInfo;
        this.fontInfo = fontInfo;
        levelFinished = false;
        maxBounds = deviceInfo.graphicsEnvironment.getMaximumWindowBounds();
        textBox_height = 50;

    }

    public void prepareEndOfLevel(LoadingAnimationT loadingAnimationT, JPanel nextScene){
        timerLabel.loadingAnimationT = loadingAnimationT;
        timerLabel.nextScene = nextScene;
    }

    public void CreateItemLabels(){
        bigItemListLabel = new JLabel();
        bigItemListLabel.setLayout(new GridLayout(1,5));
        bigItemListLabel.setBounds(0,deviceInfo.screenHeight-100, deviceInfo.screenWidth, 100);
        bigItemListLabel.setBackground(Color.decode("#14171C"));
        bigItemListLabel.setForeground(Color.white);
        bigItemListLabel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.decode("#14171C"),3), BorderFactory.createLineBorder(Color.white,3)));
        bigItemListLabel.setFont(fontInfo.getResizedFont(29f));
        bigItemListLabel.setOpaque(true);
    }

//    private void DormThings() {
//        addCustomWindowCloseButton();
//        TimerLabel timerLabel = new TimerLabel(jFrame, this, deviceInfo, fontInfo);
//        TextBox textBox = new TextBox(jFrame, this, deviceInfo, fontInfo);
//        ScoreBoard scoreBoard = new ScoreBoard(jFrame, this, deviceInfo, fontInfo);
//
//    }


    public  void createBackground(String bgfilename) {
        this.setBounds(0, 0, maxBounds.width, maxBounds.height);//size of the background image
        this.setBackground(Color.black);
        this.setLayout(null);

        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(bgfilename)));
        Image image = imageIcon.getImage();
        image = image.getScaledInstance(maxBounds.width, maxBounds.height-100, Image.SCALE_DEFAULT);
        imageIcon = new ImageIcon(image);
        backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0,-textBox_height, maxBounds.width,maxBounds.height);
        backgroundLabel.setIcon(imageIcon);
    }
    public  void generateScreen() {
        offset = -25;
        System.out.println(deviceInfo.screenWidth + " " + deviceInfo.screenHeight);

        createButton("images/01.png",deviceInfo.screenWidth*308/1536,deviceInfo.screenHeight*425/864,
                deviceInfo.screenWidth*68/1536,deviceInfo.screenHeight*88/864);
        createText("Cornflakes Box");
        createButton("images/02.png",deviceInfo.screenWidth*1188/1536,deviceInfo.screenHeight*492/864,
                deviceInfo.screenWidth*18/1536,deviceInfo.screenHeight*33/864);
        createText("CocaCola Can");
        createButton("images/03.png",deviceInfo.screenWidth*1235/1536,deviceInfo.screenHeight*407/864,
                deviceInfo.screenWidth*86/1536,deviceInfo.screenHeight*55/864);
        createText("Shoulder Bag");
        createButton("images/04.png",deviceInfo.screenWidth*1130/1536,deviceInfo.screenHeight*740/864,
                deviceInfo.screenWidth*67/1536,deviceInfo.screenHeight*26/864);
        createText("HeadPhone");
        createButton("images/05.png",deviceInfo.screenWidth*325/1536,deviceInfo.screenHeight*728/864,
                deviceInfo.screenWidth*43/1536,deviceInfo.screenHeight*32/864);
        createText("Phone");
        createButton("images/06.png",deviceInfo.screenWidth*419/1536,deviceInfo.screenHeight*671/864,
                deviceInfo.screenWidth*60/1536,deviceInfo.screenHeight*16/864);
        createText("Calculator");
        createButton("images/07.png",deviceInfo.screenWidth*1102/1536,deviceInfo.screenHeight*703/864,
                deviceInfo.screenWidth*70/1536,deviceInfo.screenHeight*22/864);
        createText("Sunglasses");
        createButton("images/08.png",deviceInfo.screenWidth*855/1536,deviceInfo.screenHeight*380/864,
                deviceInfo.screenWidth*30/1536,deviceInfo.screenHeight*12/864);
        createText("Garbage");
        createButton("images/09.png",deviceInfo.screenWidth*484/1536,deviceInfo.screenHeight*365/864,
                deviceInfo.screenWidth*50/1536,deviceInfo.screenHeight*32/864);
        createText("Toilet Paper");
        createButton("images/10.png",deviceInfo.screenWidth*544/1536,deviceInfo.screenHeight*415/864,
                deviceInfo.screenWidth*68/1536,deviceInfo.screenHeight*24/864);
        createText("Food");
        createButton("images/11.png",deviceInfo.screenWidth*95/1536,deviceInfo.screenHeight*737/864,
                deviceInfo.screenWidth*168/1536,deviceInfo.screenHeight*27/864);
        createText("Blanket");
        createButton("images/12.png",deviceInfo.screenWidth*740/1536,deviceInfo.screenHeight*535/864,
                deviceInfo.screenWidth*110/1536,deviceInfo.screenHeight*75/864);
        createText("BackPack");
        createButton("images/13.png",deviceInfo.screenWidth*515/1536,deviceInfo.screenHeight*600/864,
                deviceInfo.screenWidth*42/1536,deviceInfo.screenHeight*70/864);
        createText("Cloth Pile");
        createButton("images/14.png",deviceInfo.screenWidth*190/1536,deviceInfo.screenHeight*430/864,
                deviceInfo.screenWidth*85/1536,deviceInfo.screenHeight*95/864);
        createText("Pizza Box");
        createButton("images/15.png",deviceInfo.screenWidth*1033/1536,deviceInfo.screenHeight*412/864,
                deviceInfo.screenWidth*32/1536,deviceInfo.screenHeight*18/864);
        createText("Folded Clothes");
        createButton("images/16.png",deviceInfo.screenWidth*894/1536,deviceInfo.screenHeight*495/864,
                deviceInfo.screenWidth*28/1536,deviceInfo.screenHeight*19/864);
        createText("Chips Packet");
        createButton("images/17.png",deviceInfo.screenWidth*1204/1536,deviceInfo.screenHeight*725/864,
                deviceInfo.screenWidth*110/1536,deviceInfo.screenHeight*43/864);
        createText("Unfolded Cloth");
        createButton("images/18.png",deviceInfo.screenWidth*722/1536,deviceInfo.screenHeight*0/864,
                deviceInfo.screenWidth*165/1536,deviceInfo.screenHeight*23/864);
        createText("Quilt");
        createButton("images/19.png",deviceInfo.screenWidth*924/1536,deviceInfo.screenHeight*500/864,
                deviceInfo.screenWidth*43/1536,deviceInfo.screenHeight*20/864);
        createText("Book");
        createButton("images/20.png",deviceInfo.screenWidth*1365/1536,deviceInfo.screenHeight*752/864,
                deviceInfo.screenWidth*70/1536,deviceInfo.screenHeight*15/864);
        createText("FoodPlate");
        createButton("images/21.png",deviceInfo.screenWidth*934/1536,deviceInfo.screenHeight*455/864,
                deviceInfo.screenWidth*40/1536,deviceInfo.screenHeight*24/864);
        createText("Shoes");
        createButton("images/22.png",deviceInfo.screenWidth*489/1536,deviceInfo.screenHeight*405/864,
                deviceInfo.screenWidth*26/1536,deviceInfo.screenHeight*14/864);
        createText("TeaCup");

        this.add(backgroundLabel);
    }

    public void showItemNamesInTextBox(){
        CreateItemLabels();
        randomGenerator = new RandomGenerator(buttonList.size());
        randomGenerator.createUnique();
        this.RandObjIndices = randomGenerator.RandObjIndices;
        this.add(bigItemListLabel);
        int index;
//        bigItemListLabel.add(new JLabel());
        for(int i=0; i<RandObjIndices.size(); i++){
            index = RandObjIndices.get(i);
            bigItemListLabel.add(itemNameLabelList.get(index));
            buttonList.get(index).setEnabled(true);
        }

    }

    private void instantiateItemNameLabelList() {

        for(int i = 0; i < textList.size(); i++){
            JLabel temp = new JLabel(textList.get(i), SwingConstants.CENTER);
            temp.setForeground(Color.white);
            temp.setFont(fontInfo.getResizedFont(37f));
//            temp.setText(textList.get(i));
            itemNameLabelList.add(temp);
        }

    }


    public void resetItemNameLabelList() {

        for(int i = 0; i < textList.size(); i++){
            itemNameLabelList.get(i).setVisible(true);
            imageList.get(i).setVisible(true);

        }

    }



    public void  createObject( String image ) {
        JLabel objectLabel = new JLabel();
        objectLabel.setBounds(0,-textBox_height,maxBounds.width,maxBounds.height);

        ImageIcon  obj1icon= new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(image)));
        Image image1 = obj1icon.getImage();
        image1 = image1.getScaledInstance(maxBounds.width, maxBounds.height-100, Image.SCALE_DEFAULT);
        obj1icon = new ImageIcon(image1);

        objectLabel.setIcon(obj1icon);
        imageList.add(objectLabel);
        this.add(objectLabel);

    }

    public JLabel createObject1(String image){
        JLabel objectLabel = new JLabel();
        objectLabel.setBounds(0,-textBox_height,maxBounds.width,maxBounds.height);

        ImageIcon  obj1icon= new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(image)));
        Image image1 = obj1icon.getImage();
        image1 = image1.getScaledInstance(maxBounds.width, maxBounds.height-100, Image.SCALE_DEFAULT);
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
                            scoreBoard.setText(" 0" + Integer.toString(score));
                            scoreBoard.setHorizontalTextPosition(SwingConstants.CENTER);
                            scoreBoard.repaint();
                            repaint();
                            setEnabled(false);
                            itemNameLabelList.get(myIndex).setVisible(false);
                            if(imagesFound == 6){
                                if(scenePanel instanceof DormRoomSceneT){
                                    ((DormRoomSceneT)scenePanel).timerLabel.isTimeOver = true;
                                    ((DormRoomSceneT)scenePanel).timerLabel.score = score;


                                }
                                imagesFound=0;
                                LevelFinishDialog levelFinishDialog = new LevelFinishDialog(jFrame,fontInfo,scenePanel);
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
    public void addCustomWindowCloseButton(){
        LevelCloseButton levelCloseButton = new LevelCloseButton(deviceInfo,"X",jFrame,fontInfo,this);
//        closeButton = new CloseButton(deviceInfo,"X",jFrame, fontInfo);

//        this.add(closeButton);
        this.add(levelCloseButton);
        this.repaint();
        this.revalidate();
    }


    public void buildScene(){
        createBackground("images/LevelOneMain.png");
        timerLabel = new TimerLabel(jFrame, this, deviceInfo, fontInfo);
        scoreBoard = new ScoreBoard(jFrame, this, deviceInfo, fontInfo);
        addCustomWindowCloseButton();
        this.repaint();
        imagesFound=0;
        generateScreen();
        instantiateItemNameLabelList();
        showItemNamesInTextBox();
        this.repaint();
        music = getClass().getClassLoader().getResource("images/bgmusic.wav");
    }

    public void MessNotification(){
        messNotification = new JButton("<html>Oh No, The room looks like it got ransaked?! Where is my present?</html>");
        messNotification.setBounds(500,100,500,200);
        messNotification.setBackground(Color.gray);
        messNotification.setForeground(Color.white);
        messNotification.setFont(fontInfo.getResizedFont(42f));
        messNotification.setFocusPainted(false);
        messNotification.setBorderPainted(false);
        messNotification.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messNotification.setVisible(false);
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

    @Override
    public void run() {
        MessNotification();
        buildScene();

    }

    @Override
    public void callSelf() {

    }

    @Override
    public void startScene() {
        timerLabel.isTimeOver = false;
        timerLabel.second = 60;
        timerLabel.minute = 0;
        timerLabel.score=0;
        scoreBoard.setText("0000");
        imagesFound = 0;
        score=0;
        timerLabel.StartTimer();
        MusicPlayer musicPlayer = new MusicPlayer();
        musicPlayer.playMusic(music);
    }

    /*TODO:
    make text box, score, timer appear after pop up is gone
    make timer start after pop up is gone
    disable buttons while pop up is there
    make popup more noticible
    make sure popup is gone properly and buttons behind can be accessed (otherwise instead of set visible, try remove)

     */
}

package src;

import shelved_classes.IScene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class DormRoomSceneT extends JPanel implements Runnable, IScene {

    JFrame jFrame;
    DeviceScreenInformation deviceInfo;
    FontInfo fontInfo;
    Rectangle maxBounds;

    JLabel bigItemListLabel;
    public int score = 0;
    CloseButton closeButton;
    JLabel backgroundLabel;
    TimerLabel timerLabel;
    TextBox textBox;
    ScoreBoard scoreBoard;

    int textBox_height;
    public ArrayList<JLabel> imageList = new ArrayList<>();
    public ArrayList<ObjectHidingButton> buttonList = new ArrayList<>(); //all the buttons for the objects are in this
    public ArrayList<String> textList = new ArrayList<>(); //all the names of the objects are in this\
    ArrayList<JLabel> itemNameLabelList = new ArrayList<>(); //the labels containing item names that were randomly chosen
    public ArrayList<Integer> RandObjIndices;

    public  DormRoomSceneT(JFrame jFrame, DeviceScreenInformation deviceInfo, FontInfo fontInfo){
        this.jFrame = jFrame;
        this.deviceInfo = deviceInfo;
        this.fontInfo = fontInfo;
        maxBounds = deviceInfo.graphicsEnvironment.getMaximumWindowBounds();
        textBox_height = 50;

    }

    public void CreateItemLabels(){
//        ItemLabel Item1 = new ItemLabel(this, fontInfo,deviceInfo.screenWidth/10, deviceInfo.screenHeight-25, textList.get(randObjIndices.get(0)));
        bigItemListLabel = new JLabel();
        bigItemListLabel.setLayout(new GridLayout(1,5));
        bigItemListLabel.setBounds(0,deviceInfo.screenHeight-100, deviceInfo.screenWidth, 100);
        bigItemListLabel.setBackground(Color.darkGray);
        bigItemListLabel.setForeground(Color.white);
        bigItemListLabel.setFont(fontInfo.getResizedFont(24f));
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
        createButton("images/01.png",308,450 - 50,65,84);
        createText("Cornflakes Box");
        createButton("images/02.png",1190,520 - 50,18,30);
        createText("CocaCola Can");
        createButton("images/03.png",1242,440 - 50,78,54);
        createText("ShoulderBag");
        createButton("images/04.png",1130,750 - 50,60,26);
        createText("HeadPhone");
        createButton("images/05.png",330,740 - 50,43,32);
        createText("Phone");
        createButton("images/06.png",410,689 - 50,69,13);
        createText("Phone");
        createButton("images/07.png",1104,712 - 50,68,22);
        createText("Sunglasses");
        createButton("images/08.png",860,410 - 50,25,12);
        createText("Garbage");
        createButton("images/09.png",484,390 - 50,50,32);
        createText("Toilet Paper");
        createButton("images/10.png",544,440 - 50,68,24);
        createText("Food");
        createButton("images/11.png",94,750 - 50,158,24);
        createText("Cloth");
        createButton("images/12.png",744,555 - 50,98,60);
        createText("BagPack");
        createButton("images/13.png",523,610 - 50,34,68);
        createText("Scarf");
        createButton("images/14.png",184,470 - 50,80,80);
        createText("Pizza Box");
        createButton("images/15.png",1034,440 - 50,28,20);
        createText("Folded Clothes");
        createButton("images/16.png",894,524 - 50,28,19);
        createText("Chips Packet");
        createButton("images/17.png",1204,730 - 50,98,43);
        createText("Unfolded Cloth");
        createButton("images/18.png",724,50 - 50,160,20);
        createText("Quilt");
        createButton("images/19.png",924,524 - 50,30,19);
        createText("Book");
        createButton("images/20.png",1368,760 - 50,48,14);
        createText("FoodPlate");
        createButton("images/21.png",934,485 - 50,40,24);
        createText("Shoes");
        createButton("images/22.png",494,435 - 50,18,14);
        createText("TeaCup");

        this.add(backgroundLabel);
    }

    public void showItemNamesInTextBox(){
        RandomGenerator randomGenerator = new RandomGenerator(buttonList.size());
        randomGenerator.createUnique();
        this.RandObjIndices = randomGenerator.RandObjIndices;

        this.add(bigItemListLabel);
        int index;
        for(int i=0; i<RandObjIndices.size(); i++){
            index = RandObjIndices.get(i);
            bigItemListLabel.add(itemNameLabelList.get(index));
            buttonList.get(index).setEnabled(true);
        }

    }

    private void instantiateItemNameLabelList() {
//        for(int i = 0; i < (RandObjIndices.size()); i++){
//            JLabel temp = new JLabel();
//            temp.setForeground(Color.white);
//            temp.setText(textList.get(RandObjIndices.get(i)));
//            itemNameLabelList.add(temp);
//        }

        for(int i = 0; i < textList.size(); i++){
            JLabel temp = new JLabel();
            temp.setForeground(Color.white);
            temp.setText(textList.get(i));
            itemNameLabelList.add(temp);
        }

    }


    public void  createButton(String image,int posx, int posy, int sizex,int sizey) {
        createObject(image);
        ObjectHidingButton objectHidingButton = new ObjectHidingButton(posx,posy,sizex,sizey, imageList.get(imageList.size()-1), this, buttonList.size()){
            @Override
            public void addSceneEventsListener(ObjectHidingButton button){
                addMouseListener(new SceneObjectEvents(associatedLabel, scenePanel){
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if(isEnabled()) {
                            imageLabel.setVisible(false);
                            score += 100;
                            scoreBoard.setText(" 0" + Integer.toString(score));
                            scoreBoard.repaint();
                            repaint();
                            setEnabled(false);
                            itemNameLabelList.get(myIndex).setVisible(false);
                        }
                    }
                });
            }
        };
//        objectHidingButton.setBackground(Color.orange);
        this.add(objectHidingButton);
        buttonList.add(objectHidingButton);
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
    public void createText (String text) {
        textList.add(text);
    }
    public void addCustomWindowCloseButton(){
        closeButton = new CloseButton(deviceInfo,"X",jFrame, fontInfo);
        this.add(closeButton);
        this.repaint();
        this.revalidate();
    }


    public void buildScene(){
        createBackground("images/LevelOneMain.png");
        addCustomWindowCloseButton();
        timerLabel = new TimerLabel(jFrame, this, deviceInfo, fontInfo);
        scoreBoard = new ScoreBoard(jFrame, this, deviceInfo, fontInfo);
        this.repaint();
        generateScreen();
        instantiateItemNameLabelList();
        CreateItemLabels();
        showItemNamesInTextBox();
        bigItemListLabel.add(new JLabel("Dekhi"));
        this.repaint();
        URL music = getClass().getClassLoader().getResource("images/bgmusic.wav");
        MusicPlayer MusicPlayer = new MusicPlayer();
        MusicPlayer.playMusic(music);
    }

    @Override
    public void run() {
        buildScene();

    }

    @Override
    public void callSelf() {

    }

    @Override
    public void startTimer() {
        timerLabel.StartTimer();
    }
}

package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class DormRoomScene extends JPanel{
    JFrame window;
    DeviceScreenInformation deviceInfo;
    FontInfo fontInfo;
    Rectangle maxBounds;
    int score = 0;
    CloseButton closeButton;
    JLabel backgroundLabel;
    public ArrayList<JLabel> imagelist = new ArrayList<>();
    public ArrayList<ObjectHidingButton> buttonlist = new ArrayList<>(); //all the buttons for the objects are in this
    public ArrayList<String> Textlist = new ArrayList<>(); //all the names of the objects are in this

    public  DormRoomScene(JFrame jFrame, DeviceScreenInformation deviceInfo, FontInfo fontInfo){
        this.window = jFrame;
        this.deviceInfo = deviceInfo;
        this.fontInfo = fontInfo;
        maxBounds = deviceInfo.graphicsEnvironment.getMaximumWindowBounds();

//        createMainField();
        createBackground("images/LevelOneMain.png");
        TimerLabel timerLabel = new TimerLabel(window, this, deviceInfo, fontInfo);
        TextBox textBox = new TextBox(window, this, deviceInfo, fontInfo);
        ScoreBoard scoreBoard = new ScoreBoard(window, this, deviceInfo, fontInfo);
        generateScreen();


        window.add(this);
        timerLabel.StartTimer();
        window.repaint();
        window.revalidate();



        addCustomWindowCloseButton(window);
        URL music = getClass().getClassLoader().getResource("images/bgmusic.wav");
        MusicPlayer MusicPlayer = new MusicPlayer();
        MusicPlayer.playMusic(music);
    }

    private void DormThings() {

//        TimerLabel timerLabel = new TimerLabel(window, this, deviceInfo, fontInfo);
//        TextBox textBox = new TextBox(window, this, deviceInfo, fontInfo);
//        ScoreBoard scoreBoard = new ScoreBoard(window, this, deviceInfo, fontInfo);

        JButton DummyButton = new JButton("Object");
        DummyButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                score += 100;
//                scoreBoard.setText(" 0"+Integer.toString(score));
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
        this.add(DummyButton);
        DummyButton.setBackground(Color.pink);
        DummyButton.setOpaque(true);
        DummyButton.setVisible(true);
        DummyButton.setBounds(900,400, 100, 100);
        addCustomWindowCloseButton(window);
    }

    //    public void  createMainField(){
//        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        window.setUndecorated(true);
//        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        window.setVisible(true);
//    }
    public  void createBackground(String bgfilename) {
        this.setBounds(0, 0, maxBounds.width, maxBounds.height);//size of the background image
        this.setBackground(Color.black);
        this.setLayout(null);

        ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource(bgfilename));
        Image image = imageIcon.getImage();
        image = image.getScaledInstance(maxBounds.width, maxBounds.height-100, Image.SCALE_DEFAULT);
        imageIcon = new ImageIcon(image);
        backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0,0, maxBounds.width,maxBounds.height);
        backgroundLabel.setIcon(imageIcon);
    }
    public  void generateScreen() {
        createButton("images/01.png",308,450,65,84);
        createText("Cornflakes Box");
        createButton("images/02.png",1190,520,18,30);
        createText("CocaCola Can");
        createButton("images/03.png",1242,440,78,54);
        createText("ShoulderBag");
        createButton("images/04.png",1130,750,60,26);
        createText("HeadPhone");
        createButton("images/05.png",330,740,43,32);
        createText("Phone");
        createButton("images/06.png",410,689,69,13);
        createText("Phone");
        createButton("images/07.png",1104,712,68,22);
        createText("Sunglasses");
        createButton("images/08.png",860,410,25,12);
        createText("Garbage");
        createButton("images/09.png",484,390,50,32);
        createText("Toilet Paper");
        createButton("images/10.png",544,440,68,24);
        createText("Food");
        createButton("images/11.png",94,750,158,24);
        createText("Cloth");
        createButton("images/12.png",744,555,98,60);
        createText("BagPack");
        createButton("images/13.png",523,610,34,68);
        createText("Scarf");
        createButton("images/14.png",184,470,80,80);
        createText("Pizza Box");
        createButton("images/15.png",1034,440,28,20);
        createText("Folded Clothes");
        createButton("images/16.png",894,524,28,19);
        createText("Chips Packet");
        createButton("images/17.png",1204,730,98,43);
        createText("Unfolded Cloth");
        createButton("images/18.png",724,50,160,20);
        createText("Quilt");
        createButton("images/19.png",924,524,30,19);
        createText("Book");
        createButton("images/20.png",1368,760,48,14);
        createText("FoodPlate");
        createButton("images/21.png",934,485,40,24);
        createText("Shoes");
        createButton("images/22.png",494,435,18,14);
        createText("TeaCup");

        this.add(backgroundLabel);
    }
    public void  createButton(String image,int posx, int posy, int sizex,int sizey) {
        createObject(image);
        ObjectHidingButton objectHidingButton = new ObjectHidingButton(posx,posy,sizex,sizey,imagelist.get(imagelist.size()-1), this);
        this.add(objectHidingButton);
        buttonlist.add(objectHidingButton);
    }
    public void  createObject( String image ) {
        JLabel objectLabel = new JLabel();
        objectLabel.setBounds(0,0,maxBounds.width,maxBounds.height);

        ImageIcon  obj1icon= new ImageIcon(getClass().getClassLoader().getResource(image));
        Image image1 = obj1icon.getImage();
        image1 = image1.getScaledInstance(maxBounds.width, maxBounds.height-100, Image.SCALE_DEFAULT);
        obj1icon = new ImageIcon(image1);

        objectLabel.setIcon(obj1icon);
        imagelist.add(objectLabel);
        this.add(objectLabel);

    }
    public void createText (String text) {
        Textlist.add(text);
    }
    public void addCustomWindowCloseButton(JFrame jFrame){
        closeButton = new CloseButton(deviceInfo,"X",jFrame, fontInfo);
        this.add(closeButton);
        this.repaint();
        this.revalidate();
    }
}

package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class DormRoomLevelPanel extends ALevelPanel {
    JFrame window;
    DeviceInformation deviceInfo;
    FontInfo fontInfo;
    Rectangle maxBounds;
    int score = 0;
    CloseButton closeButton;
    JLabel backgroundLabel;
    public ArrayList<JLabel> imagelist = new ArrayList<>();
    public ArrayList<ObjectHidingButton> buttonlist = new ArrayList<>(); //all the buttons for the objects are in this
    public ArrayList<String> Textlist = new ArrayList<>(); //all the names of the objects are in this

    public DormRoomLevelPanel(JFrame jFrame, DeviceInformation deviceInfo, FontInfo fontInfo){
        this.window = jFrame;
        this.deviceInfo = deviceInfo;
        this.fontInfo = fontInfo;
        maxBounds = deviceInfo.graphicsEnvironment.getMaximumWindowBounds();

//        createMainField();
        createBackground("images/dormImages/LevelOneMain.png");
        TimerLabel timerLabel = new TimerLabel(window, this);
        TextBox textBox = new TextBox(window, this);
        ScoreBoard scoreBoard = new ScoreBoard(window, this);
        generateScreen();


        window.add(this);
        timerLabel.StartTimer();
        window.repaint();
        window.revalidate();



        addCustomWindowCloseButton(window);
       // URL music = getClass().getClassLoader().getResource("images/bgmusic.wav");
        MusicPlayer MusicPlayer = new MusicPlayer();
     //   MusicPlayer.playMusic(music);
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
        createButton("images/dormImages/01.png",308,450,65,84);
        createText("Cornflakes Box");
        createButton("images/dormImages/02.png",1190,520,18,30);
        createText("CocaCola Can");
        createButton("images/dormImages/03.png",1242,440,78,54);
        createText("ShoulderBag");
        createButton("images/dormImages/04.png",1130,750,60,26);
        createText("HeadPhone");
        createButton("images/dormImages/05.png",330,740,43,32);
        createText("Phone");
        createButton("images/dormImages/06.png",410,689,69,13);
        createText("Phone");
        createButton("images/dormImages/07.png",1104,712,68,22);
        createText("Sunglasses");
        createButton("images/dormImages/08.png",860,410,25,12);
        createText("Garbage");
        createButton("images/dormImages/09.png",484,390,50,32);
        createText("Toilet Paper");
        createButton("images/dormImages/10.png",544,440,68,24);
        createText("Food");
        createButton("images/dormImages/11.png",94,750,158,24);
        createText("Cloth");
        createButton("images/dormImages/12.png",744,555,98,60);
        createText("BagPack");
        createButton("images/dormImages/13.png",523,610,34,68);
        createText("Scarf");
        createButton("images/dormImages/14.png",184,470,80,80);
        createText("Pizza Box");
        createButton("images/dormImages/15.png",1034,440,28,20);
        createText("Folded Clothes");
        createButton("images/dormImages/16.png",894,524,28,19);
        createText("Chips Packet");
        createButton("images/dormImages/17.png",1204,730,98,43);
        createText("Unfolded Cloth");
        createButton("images/dormImages/18.png",724,50,160,20);
        createText("Quilt");
        createButton("images/dormImages/19.png",924,524,30,19);
        createText("Book");
        createButton("images/dormImages/20.png",1368,760,48,14);
        createText("FoodPlate");
        createButton("images/dormImages/21.png",934,485,40,24);
        createText("Shoes");
        createButton("images/dormImages/22.png",494,435,18,14);
        createText("TeaCup");

        this.add(backgroundLabel);
    }
    public void  createButton(String image,int posx, int posy, int sizex,int sizey) {
        createObject(image);
        ObjectHidingButton objectHidingButton = new ObjectHidingButton(posx,posy,sizex,sizey,imagelist.get(imagelist.size()-1), this, buttonlist.size());
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
        closeButton = new CloseButton("X",jFrame);
        this.add(closeButton);
        this.repaint();
        this.revalidate();
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

package src;

import javax.swing.*;
import java.awt.*;

public class MapT extends JPanel implements Runnable{

    JFrame jFrame;
    JLabel gameTitle;
    StartGameButton startGameButton;
    DeviceInformation deviceInfo;
    FontInfo fontInfo;

    ImageIcon inFocus;
    
    ImageIcon outOfFocus;
    JLabel backgroundLabel;

    PlayerScoreBoard playerScoreBoard;

    int score;

    int dormSceneScore;
    JLabel padLockAC2;
    JLabel padLockDorm;
    JLabel padLockLibrary;
    JLabel padLockCDS;
    ImageIcon padLock;
    CloseButton closeButton;
    JLabel dormText;

    public MapT(JFrame jFrame, DeviceInformation deviceInformation, FontInfo fontInfo){

        this.deviceInfo = deviceInformation;
        this.fontInfo = fontInfo;
        this.jFrame = jFrame;
        addDormText();


    }
    public void addDormText(){
        dormText = new JLabel(
                "<html>Mom packed a special Surprise for me?<br/>Let's go back to my room and see what it is.</html>",
                SwingConstants.CENTER);
        dormText.setLayout(null);
        dormText.setBounds(1300, 200, deviceInfo.screenWidth/5 -100, 500);
        dormText.setBackground(Color.decode("#14171C"));
        dormText.setForeground(Color.white);
//                text.setOpaque(true);
        dormText.setFont(fontInfo.getResizedFont(32f));

        dormText.setVisible(false);
        add(dormText);
        repaint();
    }

    public JLabel addPadLock(JLabel label, int posX, int posY, int side){
        label =  new JLabel();
        label.setLocation(posX,posY);
        label.setBounds(posX,posY,side,side);
        label.setIcon(padLock);
        return label;
    }

    public ImageIcon getScaledImage(String text, int width, int height){
        ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource(text));
        Image image = imageIcon.getImage();
        image = image.getScaledInstance(width,height,Image.SCALE_AREA_AVERAGING);
        imageIcon = new ImageIcon(image);
        return imageIcon;

    }

    public void updateScore(){
        playerScoreBoard.setText("Current Score: " + score);
    }
    public void buildScene(){
        createBackgroundPanel();
        addCustomWindowCloseButton(jFrame);
        padLock = getScaledImage("images/Level images/level_locked.png", 50,50);
        playerScoreBoard = new PlayerScoreBoard(jFrame, this,deviceInfo,fontInfo);
//        padLockDorm=addPadLock(padLockDorm, 770,195, 50);
//        this.add(padLockDorm);
        padLockAC2=addPadLock(padLockAC2, 530,120, 50);
        this.add(padLockAC2);
        padLockLibrary=addPadLock(padLockLibrary, 650,350, 50);
        this.add(padLockLibrary);
        padLockCDS=addPadLock(padLockCDS, 1050,350, 50);
        this.add(padLockCDS);
        this.add(createTranslucentSideBar((int)deviceInfo.screenWidth/5));
        createMapBackground();
    }




//    public ImageIcon generateImage(String link){
//        ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource(link));
//        Image image = imageIcon.getImage();
//        image = image.getScaledInstance(deviceInfo.screenWidth, deviceInfo.screenHeight, Image.SCALE_SMOOTH);
//        imageIcon = new ImageIcon(image);
//        return imageIcon;
//
//    }

//    public ImageIcon menuImages(String imageLocation){
//        ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource(imageLocation));
//        Image image = imageIcon.getImage();
//        image = image.getScaledInstance(deviceInfo.screenWidth, deviceInfo.screenHeight, Image.SCALE_DEFAULT);
//        imageIcon = new ImageIcon(image);
//        return imageIcon;
//    }

//    public JLabel createCustomLabel(ImageIcon image){
//        JLabel label = new JLabel();
//        label.setBounds(0,0,deviceInfo.screenWidth,deviceInfo.screenHeight);
//        label.setIcon(image);
//        return label;
//    }

//    public JButton createLocationButton(String locationName, int posx, int posy, String imageLocation, JLabel label, JPanel panel){
//        panel.add(createCustomLabel(menuImages(imageLocation)));
//        JButton locationButton = new JButton(locationName);
//        locationButton.setBackground(Color.decode("#14171C"));
//        locationButton.setForeground(Color.white);
//        locationButton.setFont(fontInfo.getResizedFont(29f));
//        locationButton.setBounds(posx,posy,200,36);
//        locationButton.setFocusPainted(false);
//        locationButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.decode("#14171C"),3), BorderFactory.createLineBorder(Color.decode("#55a38b"),2)));
//        ImageIcon outOfFocus = menuImages("images/Level images/Levelbackground.png");
////        locationButton.addMouseListener(new MenuMouseEvents(label,getDefaultImage(), outOfFocus));
//        return locationButton;
//    }

//    public void createMenuButtons(){
//        add(createLocationButton("Academic Building",450,50, "images/Level images/AcademicBuildingCutOut.png", backgroundLabel,this));
//        add(createLocationButton("Dormitory",699,145,"images/Level images/DormCutOut.png", backgroundLabel,this));
//        add(createLocationButton("Library",580,475,"images/Level images/LibraryCutOut.png",backgroundLabel,this));
//        add(createLocationButton("CDS",979,300,"images/Level images/CDSCutOut.png", backgroundLabel,this));
//
//        //hibijini
//
//    }
    public void createMapBackground(){
        backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0,0, deviceInfo.screenWidth, deviceInfo.screenHeight);
        backgroundLabel.setIcon(getDefaultImage());
        this.add(backgroundLabel);
    }

    public ImageIcon getDefaultImage(){
        ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource("images/Level images/LevelbackgroundColored.png"));
        Image image = imageIcon.getImage();
        image = image.getScaledInstance(deviceInfo.screenWidth, deviceInfo.screenHeight, Image.SCALE_DEFAULT);
        imageIcon = new ImageIcon(image);
        return imageIcon;
    }

    public void createBackgroundPanel(){
        this.setLayout(null);
        this.setPreferredSize(new Dimension(deviceInfo.screenWidth, deviceInfo.screenHeight));
        this.setBackground(Color.black);
        this.setForeground(Color.decode("#C64C1D"));
    }


//    public void createGameTitleLabel(){
//        gameTitle = new JLabel();
//        gameTitle.setBounds(0,deviceInfo.screenHeight/2,deviceInfo.screenWidth,100);
//        gameTitle.setFont(fontInfo.getResizedFont(100f));
//        gameTitle.setText("LOST TREASURES");
//        gameTitle.setHorizontalAlignment(JLabel.CENTER);
//        gameTitle.setForeground(Color.white);
//        gameTitle.setBackground(new Color(0,0,0,0));
//    }
    public void addCustomWindowCloseButton(JFrame jFrame){
        closeButton = new CloseButton(deviceInfo,"X",jFrame, fontInfo);

        this.add(closeButton);
    }

    public JLabel createTranslucentSideBar(int widthOfSideBar){
        JLabel jLabel = new JLabel();
        jLabel.setBounds(deviceInfo.screenWidth-widthOfSideBar, 0, widthOfSideBar, deviceInfo.screenHeight);
        jLabel.setBackground(new Color(0,0,0,130));
        jLabel.setOpaque(true);
        return jLabel;
    }

    @Override
    public void run() {
        buildScene();
    }

    /*TODO:
    Fix score bullshit
     */
}

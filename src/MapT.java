package src;

import javax.swing.*;
import java.awt.*;

public class MapT extends JPanel implements Runnable{

    JFrame jFrame;
    JLabel gameTitle;
    StartGameButton startGameButton;
    DeviceScreenInformation deviceInfo;
    FontInfo fontInfo;

    JLabel backgroundLabel;
    CloseButton closeButton;

    public MapT(JFrame jFrame, DeviceScreenInformation deviceScreenInformation, FontInfo fontInfo){
        this.deviceInfo = deviceScreenInformation;
        this.fontInfo = fontInfo;
        this.jFrame = jFrame;


    }

    public void buildScene(){
        createBackgroundPanel();
        addCustomWindowCloseButton(jFrame);
        createMenuButtons();
        createMapBackground();
        createTranslucentSideBar((int)deviceInfo.screenWidth/5);
    }

    public ImageIcon menuImages(String imageLocation){
        ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource(imageLocation));
        Image image = imageIcon.getImage();
        image = image.getScaledInstance(deviceInfo.screenWidth, deviceInfo.screenHeight, Image.SCALE_DEFAULT);
        imageIcon = new ImageIcon(image);
        return imageIcon;
    }

    public JLabel createCustomLabel(ImageIcon image){
        JLabel label = new JLabel();
        label.setBounds(0,0,deviceInfo.screenWidth,deviceInfo.screenHeight);
        label.setIcon(image);
        return label;
    }

    public JButton createLocationButton(String locationName, int posx, int posy, String imageLocation, JLabel label, JPanel panel){
        panel.add(createCustomLabel(menuImages(imageLocation)));
        JButton locationButton = new JButton(locationName);
        locationButton.setBackground(Color.decode("#14171C"));
        locationButton.setForeground(Color.white);
        locationButton.setFont(fontInfo.getResizedFont(29f));
        locationButton.setBounds(posx,posy,200,36);
        locationButton.setFocusPainted(false);
        locationButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.decode("#14171C"),3), BorderFactory.createLineBorder(Color.decode("#55a38b"),2)));
        ImageIcon outOfFocus = menuImages("images/Level images/Levelbackground.png");
        locationButton.addMouseListener(new MenuMouseEvents(label,getDefaultImage(), outOfFocus));
        return locationButton;
    }
    public void createMenuButtons(){
        add(createLocationButton("Academic Building",450,50, "images/Level images/AcademicBuildingCutOut.png", backgroundLabel,this));
        add(createLocationButton("Dormitory",699,145,"images/Level images/DormCutOut.png", backgroundLabel,this));
        add(createLocationButton("Library",580,475,"images/Level images/LibraryCutOut.png",backgroundLabel,this));
        add(createLocationButton("CDS",979,300,"images/Level images/CDSCutOut.png", backgroundLabel,this));

        //hibijini

    }
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
        this.setBackground(Color.CYAN);
        this.setForeground(Color.decode("#C64C1D"));
    }

    public void addStartGameButton(){
//        startGameButton = new StartGameButton(jdeviceInfo,fontInfo);
//        this.add(startGameButton);
    }
    public void createGameTitleLabel(){
        gameTitle = new JLabel();
        gameTitle.setBounds(0,deviceInfo.screenHeight/2,deviceInfo.screenWidth,100);
        gameTitle.setFont(fontInfo.getResizedFont(100f));
        gameTitle.setText("LOST TREASURES");
        gameTitle.setHorizontalAlignment(JLabel.CENTER);
        gameTitle.setForeground(Color.white);
        gameTitle.setBackground(new Color(0,0,0,0));
    }
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
}

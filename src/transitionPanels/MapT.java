package src.transitionPanels;

import src.buttons.MapLevelButton;
import src.levelObjects.PlayerScoreBoard;
import src.buttons.CloseButton;
import src.levelObjects.Sound;
import src.levels.ALevelPanel;
import src.levels.ClassRoomSceneT;
import src.popups.LevelUnlockPopUp;
import src.popups.UserStatsPopup;
import src.setup.DeviceInformation;
import src.setup.FontInfo;
import src.DatabaseConnection.PlayerInfo;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class MapT extends JPanel implements Runnable{

    public Sound mapMusic;
    Sound level2;
    public  Sound unlockMusic;
    public ArrayList<MapLevelButton> mapButtonList = new ArrayList<>();
    public ArrayList<JLabel> CutOutList = new ArrayList<>();
    public ArrayList<JLabel> SidePanelTextList = new ArrayList<>();
    public ArrayList<JLabel> PadLockList = new ArrayList<>();

    public Color hoveringActiveButtonColor = Color.decode("#75afff");
    public Color hoveringInactiveButtonColor = Color.decode("#4f4f4f");
    JLabel HugeUnLock;
    public JLabel ArrowGif;
    MapLevelButton DormButton;
    MapLevelButton ClassroomButton;
    MapLevelButton LibraryButton;
    MapLevelButton CDSButton;
    public static int gameProgress;
    public JFrame jFrame;
    JLabel DormColourCut;
    JLabel ClassroomColourCut;
    JLabel LibraryColourCut;
    JLabel CDSColourCut;
    JLabel backgroundLabel;
    PlayerScoreBoard playerScoreBoard;
    JButton ProfileButton;
    int score;
    public int MaxDormScore =0;
    public int MaxLibraryScore =0;
    public int MaxClassroomScore =0;

    public int MaxCDS_Score =0;
    JLabel padLockClassroom;
    JLabel padLockDorm;
    JLabel padLockLibrary;
    JLabel padLockCDS;
    ImageIcon padLock;
    CloseButton closeButton;
    JLabel dormText;
    JLabel libraryText;
    JLabel classroomText;
    JLabel defaultText;
    JLabel CDSText;

    Timer timer;

    public LoadingAnimationT loadingAnimationT;

    public MapT(JFrame jFrame, LoadingAnimationT loadingAnimationT){
        gameProgress = PlayerInfo.gameProgress;

        this.jFrame = jFrame;
        this.loadingAnimationT = loadingAnimationT;
        mapMusic = new Sound();
        mapMusic.setFile("audio/background_music/map.wav");
        unlockMusic = new Sound();
        unlockMusic.setFile("audio/soundeffects/podong click sound.wav");
    }

    public void ShowUnlockAnimation(int level_number){
        for(int i =0; i<=gameProgress-2; i++){
            CutOutList.get(i).setVisible(true);
        }
        HugeUnLock.setVisible(true);
        timer = new Timer(2800, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                unlockMusic.play();
                HugeUnLock.setVisible(false);
                if(level_number==2) {
                    level2 = new Sound();
                    level2.setFile("audio/soundeffects/lvl2unlock.wav");
                    level2.play();
                }
                else  if(level_number==3) {
                    level2 = new Sound();
                    level2.setFile("audio/soundeffects/lvl3unlock.wav");
                    level2.play();
                }
                else  if(level_number==4) {
                    level2 = new Sound();
                    level2.setFile("audio/soundeffects/lvl5unlock.wav");
                    level2.play();
                }

                else  if(level_number==5) {
                    level2 = new Sound();
                    level2.setFile("audio/soundeffects/lvl3unlock.wav");
                    level2.play();
                }


                stopTimer();
                LevelUnlockPopUp levelUnlockPopUp = new LevelUnlockPopUp(jFrame,level_number);
                fixArrowPosition();
            }
        });

        timer.start();

    }
    public void stopTimer(){
        timer.stop();
    }
    public void AddUnlockAnimation(){
        HugeUnLock = new JLabel();
        int size = 800;
        HugeUnLock.setBounds(getX(1920/2 - size/2),getY(1080/2 - size/2),size,size);

        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("images/Map_images/lockgif.gif")));
        Image image = imageIcon.getImage();
        image = image.getScaledInstance(size,size,Image.SCALE_DEFAULT);
        imageIcon = new ImageIcon(image);
        HugeUnLock.setIcon(imageIcon);
        HugeUnLock.setVisible(false);
        this.add(HugeUnLock);

    }


    private JLabel makeSidePanelTextLabels(String Text){
        JLabel SidePanelText = new JLabel(Text, SwingConstants.CENTER);
        SidePanelText.setLayout(null);
        SidePanelText.setBounds(DeviceInformation.screenWidth*1300/1536, DeviceInformation.screenHeight*200/864, DeviceInformation.screenWidth /5 -100, 500);
        SidePanelText.setBackground(Color.decode("#14171C"));
        SidePanelText.setForeground(Color.white);
        SidePanelText.setFont(FontInfo.getResizedFont(45f));
        SidePanelText.setVisible(false);
        add(SidePanelText);
        SidePanelTextList.add(SidePanelText);
        repaint();
        return SidePanelText;
    }
    private void AssignSideLabels() {
        defaultText = makeSidePanelTextLabels("<html>Level Locked<br/>Please complete previous levels to access.</html>");
        dormText = makeSidePanelTextLabels("<html>Mom packed a special Surprise for me?<br/>Let's go back to my room and see what it is.</html>");
        classroomText = makeSidePanelTextLabels("<html>I should go back to my classroom <br/> If i can found some of my things<br/>Let's go  to my Classroom.</html>");
        libraryText = makeSidePanelTextLabels("<html>I should check out the library ?<br/>Let's go  to my library and see if i can find this or not.</html>");
        CDSText = makeSidePanelTextLabels("<html>I should go to Our CDS <br/> If i can found some of my things<br/>Here is the CDS.</html>");
    }


    public JLabel addPadLock( int posX, int posY, int side){
        JLabel padLabel =  new JLabel();
        padLabel.setLocation(posX,posY);
        padLabel.setBounds(posX,posY,side,side);
        padLabel.setIcon(padLock);
        padLabel.setVisible(false);
        this.add(padLabel);
        PadLockList.add(padLabel);
        return padLabel;
    }

    public ImageIcon getScaledImage(String text, int width, int height){
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(text)));
        Image image = imageIcon.getImage();
        image = image.getScaledInstance(width,height,Image.SCALE_DEFAULT);
        imageIcon = new ImageIcon(image);
        return imageIcon;

    }
    private int getX(int x){
        return DeviceInformation.screenWidth* x/1920;
    }

    private int getY(int y){
        return DeviceInformation.screenHeight*y/1080;
    }


    public void updateScore(){

        score = MaxDormScore + MaxLibraryScore +MaxClassroomScore +MaxCDS_Score;
        playerScoreBoard.setText("Current Score: " + score);
    }


    public void buildScene(){
        AddUnlockAnimation();
        AddArrowGif();
        ProfileButton();
        AssignSideLabels();
        AssignCutOutLabels();
        addLockLabels();
        createMapButtons();
        addCutOuts();
        createBackgroundPanel();
        addCustomWindowCloseButton(jFrame);
        playerScoreBoard = new PlayerScoreBoard(jFrame, this);
        this.add(createTranslucentSideBar((int) DeviceInformation.screenWidth /5));
        createMapBackground();
        refreshButtonGrayness();
        fixArrowPosition();

//        mapMusic.currentVolume = -5;
//        mapMusic.fc.setValue(mapMusic.currentVolume);
    }

    public void fixArrowPosition() {
        Point p = new Point(0,0);
        switch (gameProgress) {
            case 1, 5 -> {
                p = DormButton.getLocation();
            }
            case 2 -> {
                p = ClassroomButton.getLocation();
            }
            case 3 -> {
                p = LibraryButton.getLocation();
            }
            case 4 -> {
                p = CDSButton.getLocation();
            }
        }
        p.x = p.x-(150)*1980/DeviceInformation.screenWidth;
//        p.y = p.y-(35)*1080/DeviceInformation.screenHeight;
        ArrowGif.setLocation(p);
        ArrowGif.setVisible(true);
    }

    public void AddArrowGif(){
        ArrowGif = new JLabel();
        int len = 120;
        int height = 65;
        ArrowGif.setBounds(getX(0),getY(0),len,height);

        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("images/Gifs/arrow6.gif")));
        Image image = imageIcon.getImage();
        image = image.getScaledInstance(len, height,Image.SCALE_DEFAULT);
        imageIcon = new ImageIcon(image);
        ArrowGif.setIcon(imageIcon);
        ArrowGif.repaint();
//        ArrowGif.setVisible(false);
        this.add(ArrowGif);
        repaint();
        revalidate();
    }

    private void addLockLabels() {
        int size = 70;
        padLock = getScaledImage("images/Map_images/lock unhappy.png", size,size);

        padLockDorm = addPadLock(getX(790),getY(290), 50);
        padLockClassroom = addPadLock( getX(325),getY(285),size);
        padLockLibrary = addPadLock(getX(630),getY(444), size);
        padLockCDS = addPadLock(getX(1225),getY(485), size);

    }

    public void AddAllScenes(LoadingAnimationT loadingAnimationT, ALevelPanel dormSceneT,ALevelPanel librarySceneT,ALevelPanel ClassroomSceneT,ALevelPanel CDSSceneT) {
        this.loadingAnimationT = loadingAnimationT;

//        ScenesToLoadList.add(dormSceneT);
//        ScenesToLoadList.add(ClassroomSceneT);
//        ScenesToLoadList.add(librarySceneT);
//        ScenesToLoadList.add(CDSSceneT);
    }

    private void AssignCutOutLabels(){
        DormColourCut = createCutOutLabels("images/Map_images/mapbg Dorm.png");
        ClassroomColourCut = createCutOutLabels("images/Map_images/mapbg Classroom.png");
        LibraryColourCut = createCutOutLabels("images/Map_images/mapbg Library.png");
        CDSColourCut = createCutOutLabels("images/Map_images/mapbg CDS.png");
    }

    private JLabel createCutOutLabels(String Path) {
        JLabel CutOutLabel = new JLabel();
        CutOutLabel.setLayout(null);
        CutOutLabel.setBounds(0,0, DeviceInformation.screenWidth, DeviceInformation.screenHeight);
        CutOutLabel.setVisible(false);

        ImageIcon  cutoutIcon= new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(Path)));

        Image image1 = cutoutIcon.getImage();
        image1 = image1.getScaledInstance(DeviceInformation.screenWidth, DeviceInformation.screenHeight, Image.SCALE_DEFAULT);

        ImageIcon scaledCutout = new ImageIcon(image1);

        CutOutLabel.setIcon(scaledCutout);
        CutOutList.add(CutOutLabel);
        return CutOutLabel;
    }

    private void addCutOuts(){
        for(JLabel cutout : CutOutList){
            this.add(cutout);
        }
    }

    private void createMapButtons() {
        int height = getY(70);
        int width = getX(200);
        DormButton = new MapLevelButton(getX(733), getY(170),width, height,  "Dormitory",0, this);
        ClassroomButton = new MapLevelButton(getX(255), getY(160),width, height,  "Classroom", 1,this);
        LibraryButton =  new MapLevelButton(getX(525), getY(600) ,width, height,  "Library",2, this);
        CDSButton = new MapLevelButton(getX(1200), getY(590),width, height,  "CDS", 3, this);

        mapButtonList.add(DormButton);
        mapButtonList.add(ClassroomButton);
        mapButtonList.add(LibraryButton);
        mapButtonList.add(CDSButton);

    }


    public void createMapBackground(){
        backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0,0, DeviceInformation.screenWidth, DeviceInformation.screenHeight);
        backgroundLabel.setIcon(getDefaultImage());
        this.add(backgroundLabel);
    }

    public ImageIcon getDefaultImage(){
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("images/Map_images/mapbg desaturated.png")));
        Image image = imageIcon.getImage();
        image = image.getScaledInstance(DeviceInformation.screenWidth, DeviceInformation.screenHeight, Image.SCALE_DEFAULT);
        imageIcon = new ImageIcon(image);
        return imageIcon;
    }

    public void createBackgroundPanel(){
        this.setLayout(null);
        this.setPreferredSize(new Dimension(DeviceInformation.screenWidth, DeviceInformation.screenHeight));
        this.setBackground(Color.black);
        this.setForeground(Color.decode("#C64C1D"));
    }

    public void addCustomWindowCloseButton(JFrame jFrame){
        closeButton = new CloseButton("X",jFrame);
        this.add(closeButton);
    }

    public JLabel createTranslucentSideBar(int widthOfSideBar){
        JLabel jLabel = new JLabel();
        jLabel.setBounds(DeviceInformation.screenWidth -widthOfSideBar, 0, widthOfSideBar, DeviceInformation.screenHeight);
        jLabel.setBackground(new Color(0,0,0,130));
        jLabel.setOpaque(true);
        return jLabel;
    }

    @Override
    public void run() {
        buildScene();
    }

    public void refreshButtonHover(){
        Color hoverColour = Color.decode("#487844");
        switch (gameProgress) {
            case 1, 5, 6, 7 -> {
                DormButton.setBackground(hoverColour);
                ClassroomButton.setBackground(Color.darkGray);
                LibraryButton.setBackground(Color.darkGray);
                CDSButton.setBackground(Color.darkGray);
            }
            case 2 -> {
                DormButton.setBackground(hoverColour);
                ClassroomButton.setBackground(hoverColour);
                LibraryButton.setBackground(Color.darkGray);
                CDSButton.setBackground(Color.darkGray);
            }
            case 3 -> {
                DormButton.setBackground(hoverColour);
                ClassroomButton.setBackground(hoverColour);
                LibraryButton.setBackground(hoverColour);
                CDSButton.setBackground(Color.darkGray);
            }
            case 4 -> {
                DormButton.setBackground(hoverColour);
                ClassroomButton.setBackground(hoverColour);
                LibraryButton.setBackground(hoverColour);
                CDSButton.setBackground(hoverColour);
            }
        }
    }

    public void refreshButtonGrayness() {
        switch (gameProgress) {
            case 1, 5, 6, 7 -> {
                DormButton.setBackground(Color.decode("#14171C"));
                ClassroomButton.setBackground(Color.darkGray);
                LibraryButton.setBackground(Color.darkGray);
                CDSButton.setBackground(Color.darkGray);
            }
            case 2 -> {
                DormButton.setBackground(Color.decode("#14171C"));
                ClassroomButton.setBackground(Color.decode("#14171C"));
                LibraryButton.setBackground(Color.darkGray);
                CDSButton.setBackground(Color.darkGray);
            }
            case 3 -> {
                DormButton.setBackground(Color.decode("#14171C"));
                ClassroomButton.setBackground(Color.decode("#14171C"));
                LibraryButton.setBackground(Color.decode("#14171C"));
                CDSButton.setBackground(Color.darkGray);
            }
            case 4 -> {
                DormButton.setBackground(Color.decode("#14171C"));
                ClassroomButton.setBackground(Color.decode("#14171C"));
                LibraryButton.setBackground(Color.decode("#14171C"));
                CDSButton.setBackground(Color.decode("#14171C"));
            }
        }
    }

    void ProfileButton(){
        ProfileButton = new JButton("+");
        ProfileButton.setBounds(500, 0, 30, 25);
        ProfileButton.setBorder(new RoundedBorder(10)); //10 is the radius
        ProfileButton.setForeground(Color.BLUE);
        ProfileButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserStatsPopup(jFrame);
            }
        });
        this.add(ProfileButton);
    }

    private static class RoundedBorder implements Border {

        private int radius;


        RoundedBorder(int radius) {
            this.radius = radius;
        }


        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }


        public boolean isBorderOpaque() {
            return true;
        }


        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width-1, height-1, radius, radius);
        }
    }



}

package src.transitionPanels;

import src.buttons.MapLevelButton;
import src.levelObjects.PlayerScoreBoard;
import src.buttons.CloseButton;
import src.levelObjects.Sound;
import src.levels.ALevelPanel;
import src.setup.DeviceInformation;
import src.setup.FontInfo;
import src.DatabaseConnection.PlayerInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class MapT extends JPanel implements Runnable{

    public Sound mapMusic;

//    public ArrayList<ALevelPanel> ScenesToLoadList = new ArrayList<>();
    public ArrayList<MapLevelButton> mapButtonList = new ArrayList<>();
    public ArrayList<JLabel> CutOutList = new ArrayList<>();
    public ArrayList<JLabel> SidePanelTextList = new ArrayList<>();
    public ArrayList<JLabel> PadLockList = new ArrayList<>();

    public Color hoveringActiveButtonColor = Color.decode("#75afff");
    public Color hoveringInactiveButtonColor = Color.decode("#4f4f4f");
    JLabel HugeUnLock;

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

    }

    public void ShowUnlockAnimation(){
        HugeUnLock.setVisible(true);
        timer = new Timer(2800, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HugeUnLock.setVisible(false);
                stopTimer();
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

        mapMusic = new Sound();
        mapMusic.setFile("audio/background_music/Chau Sara - Mramor(for Map).wav");

        mapMusic.currentVolume = -5;
        mapMusic.fc.setValue(mapMusic.currentVolume);
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
            case 1, 5 -> {
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
                LibraryColourCut.setBackground(hoverColour);
                CDSButton.setBackground(Color.darkGray);
            }
            case 4 -> {
                DormButton.setBackground(hoverColour);
                ClassroomButton.setBackground(hoverColour);
                LibraryColourCut.setBackground(hoverColour);
                CDSButton.setBackground(hoverColour);
            }
        }
    }

    public void refreshButtonGrayness() {
        switch (gameProgress) {
            case 1, 5 -> {
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
                LibraryColourCut.setBackground(Color.decode("#14171C"));
                CDSButton.setBackground(Color.darkGray);
            }
            case 4 -> {
                DormButton.setBackground(Color.decode("#14171C"));
                ClassroomButton.setBackground(Color.decode("#14171C"));
                LibraryColourCut.setBackground(Color.decode("#14171C"));
                CDSButton.setBackground(Color.decode("#14171C"));
            }
        }
    }


}

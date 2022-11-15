package src.transitionPanels;

import src.buttons.MapLevelButtons;
import src.levelObjects.PlayerScoreBoard;
import src.buttons.CloseButton;
import src.levels.ALevelPanel;
import src.setup.DeviceInformation;
import src.setup.FontInfo;
import src.DatabaseConnection.PlayerInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

public class MapT extends JPanel implements Runnable{
    Color hoveringActiveButtonColor = Color.decode("#75afff");
    Color hoveringInactiveButtonColor = Color.decode("#4f4f4f");
    MapLevelButtons DormButton;
    MapLevelButtons ClassroomButton;
    MapLevelButtons LibraryButton;
    MapLevelButtons CDSButton;
    public static int gameProgress;
    JFrame jFrame;
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
    JLabel padLockAC2;
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

    private LoadingAnimationT loadingAnimationT;
    private ALevelPanel dormRoomSceneT;
    private ALevelPanel librarySceneT;
    private ALevelPanel classroomSceneT;

    private ALevelPanel CDS_LevelPanelT;

    public MapT(JFrame jFrame){
        gameProgress = PlayerInfo.gameProgress;

        this.jFrame = jFrame;
        addDormText();
        addLibraryText();
        addClassroomText();
        addCDSText();
        makeDefaultText();
    }
    public void makeDefaultText(){
        defaultText = new JLabel(
                "<html>Level Locked<br/>Please complete previous levels to access.</html>",
                SwingConstants.CENTER);
        defaultText.setLayout(null);
        defaultText.setBounds(DeviceInformation.screenWidth*1300/1536, DeviceInformation.screenHeight*200/864, DeviceInformation.screenWidth /5 -100, 500);
        defaultText.setBackground(Color.decode("#14171C"));
        defaultText.setForeground(Color.white);
//                text.setOpaque(true);
        defaultText.setFont(FontInfo.getResizedFont(45f));
        defaultText.setVisible(false);
        add(defaultText);
        repaint();
    }
    public void addDormText(){
        dormText = new JLabel(
                "<html>Mom packed a special Surprise for me?<br/>Let's go back to my room and see what it is.</html>",
                SwingConstants.CENTER);
        dormText.setLayout(null);
        dormText.setBounds(DeviceInformation.screenWidth*1300/1536, DeviceInformation.screenHeight*200/864, DeviceInformation.screenWidth /5 -100, 500);
        dormText.setBackground(Color.decode("#14171C"));
        dormText.setForeground(Color.white);
//                text.setOpaque(true);
        dormText.setFont(FontInfo.getResizedFont(45f));
        dormText.setVisible(false);
        add(dormText);
        repaint();
    }

    public void addLibraryText(){
        libraryText = new JLabel(
                "<html>I should check out the library ?<br/>Let's go  to my library and see if i can find this or not.</html>",
                SwingConstants.CENTER);
        libraryText.setLayout(null);
        libraryText.setBounds(DeviceInformation.screenWidth*1300/1536, DeviceInformation.screenHeight*200/864, DeviceInformation.screenWidth /5 -100, 500);
        libraryText.setBackground(Color.decode("#14171C"));
        libraryText.setForeground(Color.white);
//                text.setOpaque(true);
        libraryText.setFont(FontInfo.getResizedFont(45f));
        libraryText.setVisible(false);
        add( libraryText);
        repaint();
    }

    public void addCDSText(){
        CDSText = new JLabel(
                "<html>I should go to Our CDS <br/> If i can found some of my things<br/>Here is the CDS.</html>",
                SwingConstants.CENTER);

        CDSText.setLayout(null);
        CDSText.setBounds(DeviceInformation.screenWidth*1300/1536, DeviceInformation.screenHeight*200/864, DeviceInformation.screenWidth /5 -100, 500);
        CDSText.setBackground(Color.decode("#14171C"));
        CDSText.setForeground(Color.white);
//                text.setOpaque(true);
        CDSText.setFont(FontInfo.getResizedFont(45f));
        CDSText.setVisible(false);
        add(  CDSText);
        repaint();
    }


    public void addClassroomText(){
        classroomText = new JLabel(
                "<html>I should go back to my classroom <br/> If i can found some of my things<br/>Let's go  to my Classroom.</html>",
                SwingConstants.CENTER);
        classroomText.setLayout(null);
        classroomText.setBounds(DeviceInformation.screenWidth*1300/1536, DeviceInformation.screenHeight*200/864, DeviceInformation.screenWidth /5 -100, 500);
        classroomText.setBackground(Color.decode("#14171C"));
        classroomText.setForeground(Color.white);
//                text.setOpaque(true);
        classroomText.setFont(FontInfo.getResizedFont(45f));
        classroomText.setVisible(false);
        add( classroomText);
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
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(text)));
        Image image = imageIcon.getImage();
        image = image.getScaledInstance(width,height,Image.SCALE_AREA_AVERAGING);
        imageIcon = new ImageIcon(image);
        return imageIcon;

    }

    public void updateScore(){

        score = MaxDormScore + MaxLibraryScore +MaxClassroomScore +MaxCDS_Score;
        playerScoreBoard.setText("Current Score: " + score);
    }


    public void buildScene(){
        doMapButtonThings();
        createCutOutLabels();
        createBackgroundPanel();
        addCustomWindowCloseButton(jFrame);
        padLock = getScaledImage("images/Map_images/level_locked.png", 50,50);
        playerScoreBoard = new PlayerScoreBoard(jFrame, this);
//        padLockDorm=addPadLock(padLockDorm, 770,195, 50);
//        this.add(padLockDorm);
        padLockAC2=addPadLock(padLockAC2, DeviceInformation.screenWidth* 500/1536,DeviceInformation.screenHeight*25/864,50);
        this.add(padLockAC2);
        padLockLibrary=addPadLock(padLockLibrary,DeviceInformation.screenWidth* 600/1536,DeviceInformation.screenHeight*250/864, 50);
        this.add(padLockLibrary);
        padLockCDS=addPadLock(padLockCDS,DeviceInformation.screenWidth* 1000/1536,DeviceInformation.screenHeight*250/864, 50);
        this.add(padLockCDS);
        this.add(createTranslucentSideBar((int) DeviceInformation.screenWidth /5));
        createMapBackground();
        refreshButtonGrayness();
    }


    public void AddAllScenes(LoadingAnimationT loadingAnimationT, ALevelPanel dormSceneT,ALevelPanel librarySceneT,ALevelPanel ClassroomSceneT,ALevelPanel CDS_LevelPanelT) {
        this.loadingAnimationT = loadingAnimationT;
        this.dormRoomSceneT = dormSceneT;
        this.librarySceneT = librarySceneT;
        this.classroomSceneT = ClassroomSceneT;
        this.CDS_LevelPanelT = CDS_LevelPanelT;
    }

    private void createCutOutLabels() {
        DormColourCut = new JLabel();
        DormColourCut.setLayout(null);
        DormColourCut.setBounds(0,0, DeviceInformation.screenWidth, DeviceInformation.screenHeight);
        DormColourCut.setVisible(false);
        ImageIcon  cutoutIcon= new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("images/Map_images/DormCutOut.png")));
        Image image1 = cutoutIcon.getImage();
        image1 = image1.getScaledInstance(DeviceInformation.screenWidth, DeviceInformation.screenHeight, Image.SCALE_DEFAULT);
        ImageIcon scaledCutout = new ImageIcon(image1);
        DormColourCut.setIcon(scaledCutout);
        add(DormColourCut);

        ClassroomColourCut = new JLabel();
        ClassroomColourCut.setLayout(null);
        ClassroomColourCut.setBounds(0,0, DeviceInformation.screenWidth, DeviceInformation.screenHeight);
        ClassroomColourCut.setVisible(false);
        cutoutIcon= new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("images/Map_images/AcademicBuildingCutOut.png")));
        image1 = cutoutIcon.getImage();
        image1 = image1.getScaledInstance(DeviceInformation.screenWidth, DeviceInformation.screenHeight, Image.SCALE_DEFAULT);
        scaledCutout = new ImageIcon(image1);
        ClassroomColourCut.setIcon(scaledCutout);
        add(ClassroomColourCut);

        LibraryColourCut = new JLabel();
        LibraryColourCut.setLayout(null);
        LibraryColourCut.setBounds(0,0, DeviceInformation.screenWidth, DeviceInformation.screenHeight);
        LibraryColourCut.setVisible(false);
        cutoutIcon= new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("images/Map_images/LibraryCutOut.png")));
        image1 = cutoutIcon.getImage();
        image1 = image1.getScaledInstance(DeviceInformation.screenWidth, DeviceInformation.screenHeight, Image.SCALE_DEFAULT);
        scaledCutout = new ImageIcon(image1);
        LibraryColourCut.setIcon(scaledCutout);
        add(LibraryColourCut);


        CDSColourCut = new JLabel();
        CDSColourCut.setLayout(null);
        CDSColourCut.setBounds(0,0, DeviceInformation.screenWidth, DeviceInformation.screenHeight);
        CDSColourCut.setVisible(false);
        cutoutIcon= new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("images/Map_images/CDSCutOut.png")));
        image1 = cutoutIcon.getImage();
        image1 = image1.getScaledInstance(DeviceInformation.screenWidth, DeviceInformation.screenHeight, Image.SCALE_DEFAULT);
        scaledCutout = new ImageIcon(image1);
        CDSColourCut.setIcon(scaledCutout);
        add(CDSColourCut);
    }

    private void makeLabel(JLabel jLabel, String path){
        jLabel = new JLabel();
    }

    private void doMapButtonThings() {
        ClassroomButton = new MapLevelButtons(DeviceInformation.screenWidth* 450/1536,DeviceInformation.screenHeight*80/864, DeviceInformation.screenWidth *200/1536, DeviceInformation.screenHeight *52/864,  "Classroom", this);
        DormButton = new MapLevelButtons(DeviceInformation.screenWidth* 699/1536,DeviceInformation.screenHeight*145/864, DeviceInformation.screenWidth *200/1536, DeviceInformation.screenHeight *52/864,  "Dormitory", this);
        CDSButton = new MapLevelButtons(DeviceInformation.screenWidth* 979/1536,DeviceInformation.screenHeight*300/864, DeviceInformation.screenWidth *200/1536, DeviceInformation.screenHeight *52/864,  "CDS", this);
        LibraryButton =  new MapLevelButtons(DeviceInformation.screenWidth* 580/1536,DeviceInformation.screenHeight*300/864, DeviceInformation.screenWidth *200/1536, DeviceInformation.screenHeight *52/864,  "Library", this);
             //   this.add(mapLevelButtonLibrary);



        DormButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jFrame.remove(DormButton.mapT);
                loadingAnimationT.changeNextScene(dormRoomSceneT);
                dormRoomSceneT.PrepareForSceneTransition(loadingAnimationT, ClassroomButton.mapT);
                jFrame.add(loadingAnimationT);
                loadingAnimationT.initializeTimer();

                DormButton.mapT.dormText.setVisible(false);
                CDSButton.mapT.CDSText.setVisible(false);
                LibraryButton.mapT.libraryText.setVisible(false);
                ClassroomButton.mapT.classroomText.setVisible(false);

                jFrame.revalidate();
                jFrame.repaint();

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                DormColourCut.setVisible(true);

                defaultText.setVisible(false);
                dormText.setVisible(false);
                CDSText.setVisible(false);
                libraryText.setVisible(false);
                classroomText.setVisible(false);

                if(gameProgress >= 1) {
                    DormButton.setBackground(hoveringActiveButtonColor);
                    dormText.setVisible(true);
                }else{
                    DormButton.setBackground(hoveringInactiveButtonColor);
                    defaultText.setVisible(true);
                }


                DormButton.mapT.revalidate();
                DormButton.mapT.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                DormColourCut.setVisible(false);
                refreshButtonGrayness();
                defaultText.setVisible(false);
            }
        });


        ClassroomButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jFrame.remove(ClassroomButton.mapT);
                loadingAnimationT.changeNextScene(classroomSceneT);
                classroomSceneT.PrepareForSceneTransition(loadingAnimationT, CDSButton.mapT);
                jFrame.add(loadingAnimationT);
                loadingAnimationT.initializeTimer();

                DormButton.mapT.dormText.setVisible(false);
                CDSButton.mapT.CDSText.setVisible(false);
                LibraryButton.mapT.libraryText.setVisible(false);
                ClassroomButton.mapT.classroomText.setVisible(false);

                jFrame.revalidate();
                jFrame.repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                ClassroomColourCut.setVisible(true);


                defaultText.setVisible(false);
                dormText.setVisible(false);
                CDSText.setVisible(false);
                libraryText.setVisible(false);
                classroomText.setVisible(false);

                if(gameProgress >= 2) {
                    ClassroomButton.setBackground(hoveringActiveButtonColor);
                    classroomText.setVisible(true);
                }else{
                    ClassroomButton.setBackground(hoveringInactiveButtonColor);
                    defaultText.setVisible(true);
                }


                DormButton.mapT.revalidate();
                DormButton.mapT.repaint();

            }

            @Override
            public void mouseExited(MouseEvent e) {
                ClassroomColourCut.setVisible(false);
                refreshButtonGrayness();
                defaultText.setVisible(false);
            }
        });





        LibraryButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jFrame.remove(LibraryButton.mapT);
                loadingAnimationT.changeNextScene(librarySceneT);
                librarySceneT.PrepareForSceneTransition(loadingAnimationT, CDSButton.mapT);
                jFrame.add(loadingAnimationT);
                loadingAnimationT.initializeTimer();

                DormButton.mapT.dormText.setVisible(false);
                CDSButton.mapT.CDSText.setVisible(false);
                ClassroomButton.mapT.classroomText.setVisible(false);
                LibraryButton.mapT.libraryText.setVisible(false);

                LibraryButton.setBackground(Color.BLACK);
                jFrame.revalidate();
                jFrame.repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                LibraryColourCut.setVisible(true);


                defaultText.setVisible(false);
                dormText.setVisible(false);
                CDSText.setVisible(false);
                libraryText.setVisible(false);
                classroomText.setVisible(false);

                if(gameProgress >= 3) {
                    LibraryButton.setBackground(hoveringActiveButtonColor);
                    libraryText.setVisible(true);
                }else{
                    LibraryButton.setBackground(hoveringInactiveButtonColor);
                    defaultText.setVisible(true);
                }


                DormButton.mapT.revalidate();
                DormButton.mapT.repaint();

            }

            @Override
            public void mouseExited(MouseEvent e) {
                LibraryColourCut.setVisible(false);
                refreshButtonGrayness();
                defaultText.setVisible(false);
            }
        });






       CDSButton.addMouseListener(new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            jFrame.remove(CDSButton.mapT);
            loadingAnimationT.changeNextScene(CDS_LevelPanelT);
            CDS_LevelPanelT.PrepareForSceneTransition(loadingAnimationT, DormButton.mapT);
            jFrame.add(loadingAnimationT);
            loadingAnimationT.initializeTimer();

            CDSButton.mapT.CDSText.setVisible(false);
            DormButton.mapT.dormText.setVisible(false);
            ClassroomButton.mapT.classroomText.setVisible(false);
            LibraryButton.mapT.libraryText.setVisible(false);

            CDSButton.setBackground(Color.BLACK);
            jFrame.revalidate();
            jFrame.repaint();
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            CDSColourCut.setVisible(true);


            defaultText.setVisible(false);
            dormText.setVisible(false);
            CDSText.setVisible(false);
            libraryText.setVisible(false);
            classroomText.setVisible(false);

            if(gameProgress >= 4) {
                CDSButton.setBackground(hoveringActiveButtonColor);
                CDSText.setVisible(true);
            }else{
                CDSButton.setBackground(hoveringInactiveButtonColor);
                defaultText.setVisible(true);
            }


            DormButton.mapT.revalidate();
            DormButton.mapT.repaint();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            CDSColourCut.setVisible(false);
            refreshButtonGrayness();
            defaultText.setVisible(false);
        }
    });
}




    public void createMapBackground(){
        backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0,0, DeviceInformation.screenWidth, DeviceInformation.screenHeight);
        backgroundLabel.setIcon(getDefaultImage());
        this.add(backgroundLabel);
    }

    public ImageIcon getDefaultImage(){
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("images/Map_images/Levelbackground.png")));
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

    /*TODO:
    Fix score bullshit
     */
}

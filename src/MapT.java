package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;
import java.util.Objects;

public class MapT extends JPanel implements Runnable{

    JFrame jFrame;
    JLabel gameTitle;
    StartGameButton startGameButton;

    ImageIcon inFocus;
    
    ImageIcon outOfFocus;
    JLabel backgroundLabel;

    PlayerScoreBoard playerScoreBoard;

    int score;
    int MaxDormScore =0;
    int MaxLibraryScore =0;
    int MaxClassroomScore =0;
    JLabel padLockAC2;
    JLabel padLockDorm;
    JLabel padLockLibrary;
    JLabel padLockCDS;
    ImageIcon padLock;
    CloseButton closeButton;
    JLabel dormText;
    JLabel libraryText;
    JLabel classroomText;

    private LoadingAnimationT loadingAnimationT;
    private ALevelPanel dormRoomSceneT;
    private ALevelPanel librarySceneT;
    private ALevelPanel classroomSceneT;

    public MapT(JFrame jFrame){

        this.jFrame = jFrame;
        addDormText();
        addlibraryText();

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
        dormText.setFont(FontInfo.getResizedFont(32f));
        dormText.setVisible(false);
        add(dormText);
        repaint();
    }

    public void addlibraryText(){
        libraryText = new JLabel(
                "<html>I should check out the library ?<br/>Let's go  to my library and see if i can find this or not.</html>",
                SwingConstants.CENTER);
        libraryText.setLayout(null);
        libraryText.setBounds(DeviceInformation.screenWidth*1300/1536, DeviceInformation.screenHeight*200/864, DeviceInformation.screenWidth /5 -100, 500);
        libraryText.setBackground(Color.decode("#14171C"));
        libraryText.setForeground(Color.white);
//                text.setOpaque(true);
        libraryText.setFont(FontInfo.getResizedFont(32f));
        libraryText.setVisible(false);
        add( libraryText);
        repaint();
    }

    public void addClassroomText(){
        libraryText = new JLabel(
                "<html>I should go back to my classroom <br/> If i can found some of my things<br/>Let's go  to my Classroom.</html>",
                SwingConstants.CENTER);
        libraryText.setLayout(null);
        libraryText.setBounds(DeviceInformation.screenWidth*1300/1536, DeviceInformation.screenHeight*200/864, DeviceInformation.screenWidth /5 -100, 500);
        libraryText.setBackground(Color.decode("#14171C"));
        libraryText.setForeground(Color.white);
//                text.setOpaque(true);
        libraryText.setFont(FontInfo.getResizedFont(32f));
        libraryText.setVisible(false);
        add( libraryText);
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
        score = MaxDormScore + MaxLibraryScore +MaxClassroomScore;
        playerScoreBoard.setText("Current Score: " + score);
    }


    public void buildScene(){
        doMapButtonThings();
        createBackgroundPanel();
        addCustomWindowCloseButton(jFrame);
        padLock = getScaledImage("images/Level images/level_locked.png", 50,50);
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
    }
    public void AddAllScenes(LoadingAnimationT loadingAnimationT, ALevelPanel dormSceneT,ALevelPanel librarySceneT,ALevelPanel ClassroomSceneT) {
        this.loadingAnimationT = loadingAnimationT;
        this.dormRoomSceneT = dormSceneT;
        this.librarySceneT = librarySceneT;
        this.classroomSceneT = ClassroomSceneT;
    }


    private void doMapButtonThings() {
        MapLevelButtons mapLevelButtonsAC2 = new MapLevelButtons(DeviceInformation.screenWidth* 450/1536,DeviceInformation.screenHeight*80/864, DeviceInformation.screenWidth *200/1536, DeviceInformation.screenHeight *52/864,  "Academic Building 2", this);
        //        this.add(mapLevelButtonsAC2);
        MapLevelButtons mapLevelButtonsDorm = new MapLevelButtons(DeviceInformation.screenWidth* 699/1536,DeviceInformation.screenHeight*145/864, DeviceInformation.screenWidth *200/1536, DeviceInformation.screenHeight *52/864,  "Dormitory", this);
        //        this.add(mapLevelButtonsDorm);
        MapLevelButtons mapLevelButtonsCDS = new MapLevelButtons(DeviceInformation.screenWidth* 979/1536,DeviceInformation.screenHeight*300/864, DeviceInformation.screenWidth *200/1536, DeviceInformation.screenHeight *52/864,  "CDS", this);
        //        this.add(mapLevelButtonsCDS);
        MapLevelButtons mapLevelButtonLibrary =  new MapLevelButtons(DeviceInformation.screenWidth* 580/1536,DeviceInformation.screenHeight*300/864, DeviceInformation.screenWidth *200/1536, DeviceInformation.screenHeight *52/864,  "Library", this);
        //        this.add(mapLevelButtonLibrary);



        mapLevelButtonsDorm.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jFrame.remove(mapLevelButtonsDorm.mapT);
                loadingAnimationT.changeNextScene(dormRoomSceneT);
                dormRoomSceneT.PrepareForSceneTransition(loadingAnimationT,mapLevelButtonsAC2.mapT);
                jFrame.add(loadingAnimationT);
                loadingAnimationT.initializeTimer();

                mapLevelButtonsDorm.mapT.dormText.setVisible(false);
                mapLevelButtonLibrary.mapT.libraryText.setVisible(false);
                mapLevelButtonsAC2.mapT.classroomText.setVisible(false);

                mapLevelButtonsDorm.setBackground(Color.BLACK);

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
                mapLevelButtonsDorm.setBackground(Color.PINK);

                mapLevelButtonsDorm.mapT.dormText.setVisible(true);
                mapLevelButtonLibrary.mapT.libraryText.setVisible(false);
                mapLevelButtonsAC2.mapT.classroomText.setVisible(false);

                mapLevelButtonsDorm.mapT.revalidate();
                mapLevelButtonsDorm.mapT.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mapLevelButtonsDorm.setBackground(Color.BLACK);
            }
        });


        mapLevelButtonsAC2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jFrame.remove(mapLevelButtonsAC2.mapT);
                loadingAnimationT.changeNextScene(classroomSceneT);
                librarySceneT.PrepareForSceneTransition(loadingAnimationT, mapLevelButtonsCDS.mapT);
                jFrame.add(loadingAnimationT);
                loadingAnimationT.initializeTimer();

                mapLevelButtonsDorm.mapT.dormText.setVisible(false);
                mapLevelButtonLibrary.mapT.libraryText.setVisible(false);
                mapLevelButtonsAC2.mapT.classroomText.setVisible(false);

                mapLevelButtonsAC2.setBackground(Color.BLACK);
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

                mapLevelButtonsAC2.setBackground(Color.PINK);
                mapLevelButtonsDorm.mapT.dormText.setVisible(false);
                mapLevelButtonLibrary.mapT.libraryText.setVisible(false);
                mapLevelButtonsAC2.mapT.classroomText.setVisible(true);


                mapLevelButtonsAC2.mapT.revalidate();
                mapLevelButtonsAC2.mapT.repaint();

            }

            @Override
            public void mouseExited(MouseEvent e) {
                mapLevelButtonsAC2.setBackground(Color.BLACK);
            }
        });





        mapLevelButtonLibrary.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jFrame.remove(mapLevelButtonLibrary.mapT);
                loadingAnimationT.changeNextScene(librarySceneT);
                librarySceneT.PrepareForSceneTransition(loadingAnimationT, mapLevelButtonsCDS.mapT);
                jFrame.add(loadingAnimationT);
                loadingAnimationT.initializeTimer();

                mapLevelButtonLibrary.mapT.dormText.setVisible(false);
                mapLevelButtonLibrary.mapT.classroomText.setVisible(false);
                mapLevelButtonLibrary.mapT.libraryText.setVisible(false);

                mapLevelButtonLibrary.setBackground(Color.BLACK);
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

                mapLevelButtonLibrary.setBackground(Color.PINK);

                mapLevelButtonsDorm.mapT.dormText.setVisible(false);
                mapLevelButtonsAC2.mapT.classroomText.setVisible(false);
                mapLevelButtonLibrary.mapT.libraryText.setVisible(true);

                mapLevelButtonLibrary.mapT.revalidate();
                mapLevelButtonLibrary.mapT.repaint();

            }

            @Override
            public void mouseExited(MouseEvent e) {
                mapLevelButtonLibrary.setBackground(Color.BLACK);
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
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("images/Level images/LevelbackgroundColored.png")));
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

    /*TODO:
    Fix score bullshit
     */
}

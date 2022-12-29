package src.levels;

import src.buttons.AudioChangeButton;
import src.buttons.FinalLevelButton;
import src.buttons.LevelCloseButton;
import src.levelObjects.ScoreBoard;
import src.levelObjects.Sound;
import src.levelObjects.TimerLabel;
import src.setup.DeviceInformation;
import src.setup.FontInfo;
import src.transitionPanels.LoadingAnimationT;
import src.transitionPanels.MapT;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class DormVersion2 extends JPanel implements Runnable {
    public JFrame jFrame;
    Rectangle maxBounds;
    public Sound objClickSound;
    public LoadingAnimationT loadingAnimationT;
    MapT mapT;
    boolean levelFinished;
    public int taps;
    int textBox_height;
    public Sound backgroundMusic;
    public JLabel congratulationsConfetti;
    public ArrayList<JLabel> imageList = new ArrayList<>(); // array of screens
    public ArrayList<FinalLevelButton> buttonList = new ArrayList<>(); //all the buttons for the screens are in this
    public ArrayList<String> textList = new ArrayList<>(); //all the dialogues
    public JLabel TextBox;

    public DormVersion2(JFrame jFrame){
        this.jFrame = jFrame;
        levelFinished = false;
        maxBounds = DeviceInformation.graphicsEnvironment.getMaximumWindowBounds();
        textBox_height = DeviceInformation.screenHeight*50/864;
        this.setLayout(null);
    }

    public void createConfettiScreen(){
        congratulationsConfetti = new JLabel();
        congratulationsConfetti.setBounds(0,0, DeviceInformation.screenWidth,DeviceInformation.screenHeight);
        ImageIcon gif = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("images/congratulations/levelFinishedSuccessfullyConfetti.gif")));
        gif.setImage(gif.getImage().getScaledInstance(DeviceInformation.screenWidth, DeviceInformation.screenHeight, Image.SCALE_DEFAULT));
        congratulationsConfetti.setIcon(gif);
        congratulationsConfetti.setVisible(false);
        this.add(congratulationsConfetti);
    }

    public JLabel createObject(String image) throws IOException {
        JLabel objectLabel = new JLabel();
        objectLabel.setBounds(0,0,maxBounds.width,maxBounds.height-textBox_height);

        ImageIcon  obj1icon= new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(image)));
        obj1icon.setImage(obj1icon.getImage().getScaledInstance(maxBounds.width, maxBounds.height-textBox_height, Image.SCALE_DEFAULT));

        objectLabel.setIcon(obj1icon);
        imageList.add(objectLabel);
        return objectLabel;

    }
    public void createButton(String image,int posx, int posy, int sizex,int sizey) throws IOException {
        JLabel objectLabel = createObject(image);
        FinalLevelButton finalLevelButton = new FinalLevelButton(posx, posy, sizex, sizey, imageList.get(imageList.size() - 1), this, buttonList.size());
        this.add(finalLevelButton);
        this.add(objectLabel);
        buttonList.add(finalLevelButton);
    }

    public void createText (String text) {
        textList.add(text);
    }
    public int getLevelNumber(){
        return 6;
    }


    public void addAudioButton(){
        AudioChangeButton audioChangeButton = new AudioChangeButton(jFrame, backgroundMusic);
        this.add(audioChangeButton);
        this.repaint();
        this.revalidate();
    }
    public int getX(int x){
        return DeviceInformation.screenWidth*x/1536;
    }

    public int getY(int y){
        return  DeviceInformation.screenHeight*y/864;
    }

    public synchronized void generateScreenWithAllObjectsAndButtons() throws IOException {

        createButton("images/EndScene/bedafterremovingblanketandbag.png", getX(0), getY(0), getX(DeviceInformation.screenWidth), getY(DeviceInformation.screenHeight));
        createText("So that's what my mom packed for me. The cat must have scavenged it from my bag.. It really is such a pleasant surprise, a precious treasure.");
        createButton("images/EndScene/bedafterremovingblanketandbag.png", getX(0), getY(0), getX(DeviceInformation.screenWidth), getY(DeviceInformation.screenHeight));
        createText("That's such a cute cat. What's it doing here? Wait.. what's that? (Click on the shiny object)");
        createButton("images/EndScene/bedafterremovingblanket.png", getX(0), getY(0), getX(DeviceInformation.screenWidth), getY(DeviceInformation.screenHeight));
        createText("(Remove the Bag)");
        createButton("images/EndScene/bedinitial.png", getX(0), getY(0), getX(DeviceInformation.screenWidth), getY(DeviceInformation.screenHeight));
        createText("(Remove the Blanket)");
        createButton("images/EndScene/meow.gif", getX(0), getY(0), getX(DeviceInformation.screenWidth), getY(DeviceInformation.screenHeight));
        createText("What was that?! (Tap to Continue)");
        createButton("images/EndScene/almirahscene.png", getX(0), getY(0), getX(DeviceInformation.screenWidth), getY(DeviceInformation.screenHeight));
        createText("Where's my T-shirt?");
        createButton("images/EndScene/almirahscene.png", getX(0), getY(0), getX(DeviceInformation.screenWidth), getY(DeviceInformation.screenHeight));
        createText("Where's my T-shirt?");
        createButton("images/dormImages/LevelOneMain.png", getX(0), getY(0), getX(DeviceInformation.screenWidth), getY(DeviceInformation.screenHeight));
        createText("Where's my T-shirt?");


    }

    public void StartLevel() { //when clicked from Map, resets some things and starts the scene
        congratulationsConfetti.setVisible(false);
        resetVariables();
    }

    public void PrepareForSceneTransition(LoadingAnimationT loadingAnimationT, MapT mapT) {
        this.loadingAnimationT = loadingAnimationT;
        this.mapT = mapT;
    }
    public void EndLevel() {

        if(backgroundMusic != null){
            backgroundMusic.stop();
        }

        revalidate();
        repaint();

        jFrame.remove(this);

        loadingAnimationT.changeNextScene(mapT);
        loadingAnimationT.iNeedYouToRemoveMe = true;

        jFrame.add(loadingAnimationT);
        loadingAnimationT.initializeTimer();
    }
    public String getBackgroundMusicPath(){
            return "audio/background_music/dormBackgroundAudio.wav";
    }

    public void buildScene() throws IOException {
        createConfettiScreen();
        generateScreenWithAllObjectsAndButtons();
        MessNotification();

        backgroundMusic = new Sound();
        backgroundMusic.setFile(getBackgroundMusicPath());

        objClickSound = new Sound();
        objClickSound.setFile("audio/soundeffects/objectFoundClick2.wav");



        addCustomWindowCloseButton();
        addAudioButton();

        revalidate();
        repaint();

        taps =0;

        repaint();

    }



    public void MessNotification(){
        TextBox = new JLabel(textList.get(0));
        TextBox.setFont(FontInfo.getResizedFont(38f));
        TextBox.setEnabled(false);
        TextBox.setBounds(0, DeviceInformation.screenHeight -(textBox_height*2), DeviceInformation.screenWidth, textBox_height*2);
        TextBox.setBackground(Color.decode("#14171C"));
        TextBox.setForeground(Color.white);
        TextBox.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.decode("#14171C"),3), BorderFactory.createLineBorder(Color.white,3)));
        TextBox.setOpaque(true);
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PanelClick();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                PanelClick();
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
        this.add(TextBox);
    }

    public void PanelClick(){
        switch (taps){
            case 0 -> {

            }
            case 1 -> {
            }
            case 2 -> {
            }
            case 3 -> {
            }
            case 4 -> {
            }
            case 5 -> {

            }

        }
    }




    @Override
    public void run(){
        try {
            buildScene();

        }catch (IOException e){
            System.out.println(e.toString());
        }
    }

    public void addCustomWindowCloseButton(){
        LevelCloseButton levelCloseButton = new LevelCloseButton("X",jFrame,this);
        this.add(levelCloseButton);
        this.repaint();
        this.revalidate();
    }

    public void resetVariables(){
        taps = 0;
        for(JLabel image : imageList){
            image.setVisible(false);
        }
        imageList.get(0).setVisible(true);
    }
}

package src.levels;

import src.Sound;
import src.buttons.ObjectHidingButton;
import src.levelObjects.ScoreBoard;
import src.levelObjects.TimerLabel;
import src.setup.DeviceInformation;
import src.transitionPanels.LoadingAnimationT;
import src.transitionPanels.MapT;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public abstract class ALevelPanel extends JPanel implements Runnable{
    public TimerLabel timerLabel;
    public ScoreBoard scoreBoard;

    public Sound sound;
    public JLabel ShowGottenScore;
    public JLabel HintAnimationGif;
    JLabel congratulationsConfetti;
    public ArrayList<JLabel> imageList = new ArrayList<>(); // array of images of items we may need to find
    public ArrayList<ObjectHidingButton> buttonList = new ArrayList<>(); //all the buttons for the objects are in this
    public ArrayList<String> textList = new ArrayList<>(); //all the names of the objects are in this\
    ArrayList<JLabel> ListOfAllItemNamesAsLabels = new ArrayList<>(); //the labels containing Strings of 'item names' that were randomly chosen
    public ArrayList<Integer> RandObjIndices;

    public void createConfettiScreen(){
        congratulationsConfetti = new JLabel();
        congratulationsConfetti.setBounds(0,0, DeviceInformation.screenWidth,DeviceInformation.screenHeight);
        ImageIcon gif = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("images/congratulations/levelFinishedSuccessfullyConfetti.gif")));
        gif.setImage(gif.getImage().getScaledInstance(DeviceInformation.screenWidth, DeviceInformation.screenHeight, Image.SCALE_DEFAULT));
        congratulationsConfetti.setIcon(gif);
        congratulationsConfetti.setVisible(false);
        this.add(congratulationsConfetti);
    }
    public abstract void startScene(); //when clicked from Map, resets some things and starts the scene
    public abstract void PrepareForSceneTransition(LoadingAnimationT loadingAnimationT, MapT mapT); // scene transition to mapT
    public abstract  void EndLevel();
    public abstract void buildScene() throws IOException;

    @Override
    public void run(){
        try {
            buildScene();

        }catch (IOException e){

        }
    }

    public void createSound(String audioFilePath){
        sound = new Sound();
        sound.setFile(audioFilePath);
    }



}

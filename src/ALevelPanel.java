package src;

import javax.swing.*;
import java.util.ArrayList;

public abstract class ALevelPanel extends JPanel {
    TimerLabel timerLabel;
    ScoreBoard scoreBoard;
    JLabel ShowGottenScore;
    JLabel HintAnimationGif;

    Main main;

    public ArrayList<JLabel> imageList = new ArrayList<>(); // array of images of items we may need to find
    public ArrayList<ObjectHidingButton> buttonList = new ArrayList<>(); //all the buttons for the objects are in this
    public ArrayList<String> textList = new ArrayList<>(); //all the names of the objects are in this\
    ArrayList<JLabel> ListOfAllItemNamesAsLabels = new ArrayList<>(); //the labels containing Strings of 'item names' that were randomly chosen
    public ArrayList<Integer> RandObjIndices;


    public abstract void startScene(); //when clicked from Map, resets some things and starts the scene
    public abstract void PrepareForSceneTransition(LoadingAnimationT loadingAnimationT, MapT mapT); // scene transition to mapT
    public abstract  void EndLevel();

}

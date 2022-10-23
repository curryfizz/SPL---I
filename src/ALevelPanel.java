package src;

import javax.swing.*;

public abstract class ALevelPanel extends JPanel {
    TimerLabel timerLabel;
    ScoreBoard scoreBoard;
    JLabel ShowGottenScore;

    public abstract void startScene(); //when clicked from Map, resets some things and starts the scene
    public abstract void PrepareForSceneTransition(LoadingAnimationT loadingAnimationT, MapT mapT); // scene transition to mapT
    public abstract  void EndLevel();

}

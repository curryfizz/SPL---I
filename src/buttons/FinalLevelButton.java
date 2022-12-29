package src.buttons;

import src.DatabaseConnection.OracleDatabase;
import src.DatabaseConnection.PlayerInfo;
import src.events.SceneObjectEvents;
import src.levels.ALevelPanel;
import src.levels.DormVersion2;
import src.popups.LevelFinishDialog;
import src.popups.LevelFinishedNotEnoughScore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class FinalLevelButton extends JButton{

    int positionX;
    int positionY;
    int buttonWidth;
    int buttonHeight;
    public JLabel associatedLabel;
    public DormVersion2 levelPanel;
    public int myIndex;
    public boolean HintWasUsed = false;

    public FinalLevelButton(int posx, int posy, int width, int height, JLabel associatedLabel, DormVersion2 levelPanel, int myIndex) {
        this.positionX = posx;
        this.positionY = posy;
        this.buttonWidth = width;
        this.buttonHeight = height;
        this.associatedLabel = associatedLabel;
        this.levelPanel = levelPanel;
        this.myIndex = myIndex;
        createInvisibleButton();
    }

    public void createInvisibleButton(){

        repaint();

        /* during debugging*/

        setOpaque(true);
        setBackground(new Color(255, 0, 0, 100));


        /* stuff that works*/

//        setOpaque(false);
//        setContentAreaFilled(false);

        repaint();
        setFocusPainted(false);
        setBorderPainted(false);
        setEnabled(false);

        setBounds(positionX,positionY,buttonWidth,buttonHeight);
        addSceneEventsListener(this);

    }

    public void addSceneEventsListener(FinalLevelButton button){
        addMouseListener(new SceneObjectEvents(associatedLabel, levelPanel){
            @Override
            public void mouseClicked(MouseEvent e) {

//                if(!isEnabled())
//                    return;

//                if(levelPanel.taps == 0){
//                    levelPanel.imageList.get(0).setVisible(false);
//                    levelPanel.imageList.get(1).setVisible(true);
//                }
//                else if(levelPanel.taps == 1) {
//                    levelPanel.imageList.get(1).setVisible(false);
//                }
//
//                levelPanel.taps++;








                if(isEnabled()) {
                    System.out.println("idk man, me a button got pushed, was enabled");

                    levelPanel.objClickSound.play();
                    levelPanel.imageList.get(levelPanel.taps).setVisible(false);
                    levelPanel.taps++;

                    setEnabled(false);
//                    associatedLabel.setVisible(false);
                    // here images found works more like taps on the screen

                    if(levelPanel.buttonList.size()-1 >= myIndex+1){
                        System.out.println("Bro wtf");
                        levelPanel.buttonList.get(myIndex + 1).setEnabled(true);
                        levelPanel.imageList.get(myIndex+1).setVisible(true);

                    }

                    if(levelPanel.taps == levelPanel.imageList.size()){
                        levelPanel.congratulationsConfetti.setVisible(true);

                        /** Change this JDialogue to another one **/
                        LevelFinishDialog levelFinishDialog = new LevelFinishDialog(levelPanel.jFrame, levelPanel.getLevelNumber(),  levelPanel);

                        System.out.println("Skipped levelFinishDialogue"); // IT DOESNT SKIP, IT WAITS FOR A RESPONSE FROM THE JDIALOGUE

                        if(PlayerInfo.gameProgress < levelPanel.getLevelNumber()){ // first time
                            PlayerInfo.gameProgress = levelPanel.getLevelNumber();
                        }

                        levelPanel.taps=0;
                        levelPanel.EndLevel();
                        if(levelPanel.backgroundMusic != null) {
                            levelPanel.backgroundMusic.stop();
                        }

                        levelPanel.revalidate();
                        levelPanel.repaint();
                        levelPanel.jFrame.revalidate();
                        levelPanel.jFrame.repaint();
                    }
                    else {
                        levelPanel.buttonList.get(myIndex + 1).setEnabled(true);
                    }
                }
            }
            @Override
            public void mouseEntered(MouseEvent e){
            }
        });
    }
}

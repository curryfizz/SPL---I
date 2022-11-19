package src.buttons;
import src.events.SceneObjectEvents;
import src.levels.ALevelPanel;
import src.popups.LevelFinishDialog;
import src.setup.PlayerInfo;
import src.transitionPanels.MapT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class ObjectHidingButton extends JButton {

    int positionX;
    int positionY;
    int buttonWidth;
    int buttonHeight;
    public JLabel associatedLabel;
    public ALevelPanel levelPanel;
    public int myIndex;
    public boolean HintWasUsed = false;

    public ObjectHidingButton(int posx, int posy, int width, int height, JLabel label, ALevelPanel levelPanel, int myIndex){
        this.positionX = posx;
        this.positionY = posy;
        this.buttonWidth = width;
        this.buttonHeight = height;
        this.associatedLabel = label;
        this.levelPanel = levelPanel;
        this.myIndex = myIndex;
        createInvisibleButton();
    }


    public void createInvisibleButton(){

        repaint();

        /* during debugging*/

//        setOpaque(true);
//        setBackground(new Color(255, 0, 0, 100));


        /* stuff that works*/

        setOpaque(false);
        setContentAreaFilled(false);

        repaint();
        setFocusPainted(false);
        setBorderPainted(false);
        setEnabled(false);

        //setLocation(DeviceInformation.graphicsConfiguration.getBounds().x+positionX, DeviceInformation.graphicsConfiguration.getBounds().y+positionY);
       // setSize(buttonWidth,buttonHeight);
        setBounds(positionX,positionY,buttonWidth,buttonHeight);
        addSceneEventsListener(this);

    }

    public void addSceneEventsListener(ObjectHidingButton button){
        addMouseListener(new SceneObjectEvents(associatedLabel, levelPanel){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(isEnabled()) {

                    levelPanel.objClickSound.play();


                    imageLabel.setVisible(false);
                    levelPanel.imagesFound+=1;

                    if((levelPanel.timerLabel.elapsedTime - levelPanel.timeSinceLastFind) < 10){
                        levelPanel.currentCombo++;
                    }else{
                        levelPanel.currentCombo = 0;
                    }

                    levelPanel.timeSinceLastFind = levelPanel.timerLabel.elapsedTime;

                    int gottenScore;
                    if(HintWasUsed){
                        gottenScore = levelPanel.scoreBoard.setScore(50, 0);
                        levelPanel.currentCombo = 0;
                        levelPanel.hintAnimationGif.setVisible(false);
                        HintWasUsed = false;
                    }
                    else{
                        gottenScore = levelPanel.scoreBoard.setScore((int) (levelPanel.timerLabel.elapsedTime/2.0), levelPanel.currentCombo);
                    }
                    levelPanel.scoreBoard.refreshScore();

                    levelPanel.ShowGottenScore.setText("+" + gottenScore);
                    levelPanel.ShowGottenScore.setLocation(button.getLocation());
                    levelPanel.ShowGottenScore.setVisible(true);
                    repaint();
                    levelPanel.timerLabel.AnimateScore(e.getPoint());

                    setEnabled(false);
                    levelPanel.ListOfAllItemNamesAsLabels.get(myIndex).setVisible(false);
                    if(levelPanel.imagesFound == 6){
                        levelPanel.timerLabel.isTimeOver = true;
                        levelPanel.imagesFound=0;
                        levelPanel.loadingAnimationT.LevelOverProgressUpdated = true;
                        levelPanel.congratulationsConfetti.setVisible(true);
                        LevelFinishDialog levelFinishDialog = new LevelFinishDialog(levelPanel.jFrame, levelPanel);
                        PlayerInfo.gameProgress = levelPanel.getLevelNumber();
                        MapT.gameProgress = levelPanel.getLevelNumber();
                        levelPanel.revalidate();
                        levelPanel.repaint();
                        levelPanel.jFrame.revalidate();
                        levelPanel.jFrame.repaint();

                    }
                }
                else {
                    levelPanel.PanelClick();
                }

            }
            @Override
            public void mouseEntered(MouseEvent e){
            }
        });
    }


}
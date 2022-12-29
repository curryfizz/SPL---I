package src.buttons;

import src.DatabaseConnection.OracleDatabase;
import src.DatabaseConnection.PlayerInfo;
import src.events.SceneObjectEvents;
import src.levels.ALevelPanel;
import src.levels.DormVersion2;
import src.popups.LevelFinishDialog;
import src.popups.LevelFinishedNotEnoughScore;

import javax.swing.*;
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

    public FinalLevelButton(int posx, int posy, int width, int height, JLabel label, DormVersion2 levelPanel, int myIndex) {
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

        setBounds(positionX,positionY,buttonWidth,buttonHeight);
        addSceneEventsListener(this);

    }

    public void addSceneEventsListener(FinalLevelButton button){
        addMouseListener(new SceneObjectEvents(associatedLabel, levelPanel){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(isEnabled()) {

                    levelPanel.objClickSound.play();
                    imageLabel.setVisible(false);
                    levelPanel.taps++;

                    setEnabled(false);
                    levelPanel.imageList.get(myIndex).setVisible(false);

                    // here images found works more like taps on the screen

                    if(levelPanel.taps == 6){
                        levelPanel.congratulationsConfetti.setVisible(true);

                        /** Change this JDialogue to another one **/
                        LevelFinishDialog levelFinishDialog = new LevelFinishDialog(levelPanel.jFrame, levelPanel);

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

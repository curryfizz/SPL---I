package src.buttons;
import src.events.SceneObjectEvents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class ObjectHidingButton extends JButton {

    int positionX;
    int positionY;
    int buttonWidth;
    int buttonHeight;
    public JLabel associatedLabel;
    public JPanel scenePanel;
    public int myIndex;
    public boolean HintWasUsed = false;

    public ObjectHidingButton(int posx, int posy, int width, int height, JLabel label, JPanel scenePanel, int myIndex){
        this.positionX = posx;
        this.positionY = posy;
        this.buttonWidth = width;
        this.buttonHeight = height;
        this.associatedLabel = label;
        this.scenePanel = scenePanel;
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

        //setLocation(DeviceInformation.graphicsConfiguration.getBounds().x+positionX, DeviceInformation.graphicsConfiguration.getBounds().y+positionY);
       // setSize(buttonWidth,buttonHeight);
        setBounds(positionX,positionY,buttonWidth,buttonHeight);
        addSceneEventsListener(this);

    }

    public void addSceneEventsListener(ObjectHidingButton button){
        addMouseListener(new SceneObjectEvents(associatedLabel, scenePanel){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(isEnabled())
                imageLabel.setOpaque(false);
                imageLabel.setVisible(false);
                scenePanel.repaint();
//                this.scenePanel.score += 100;

            }
            @Override
            public void mouseEntered(MouseEvent e){
            }
        });
    }

}
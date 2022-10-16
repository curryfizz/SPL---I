package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class ObjectHidingButton extends JButton {

    int positionX;
    int positionY;
    int buttonWidth;
    int buttonHeight;
    JLabel associatedLabel;
    JPanel scenePanel;

    public ObjectHidingButton(int posx, int posy, int width, int height, JLabel label, JPanel scenePanel){
        this.positionX = posx;
        this.positionY = posy;
        this.buttonWidth = width;
        this.buttonHeight = height;
        this.associatedLabel = label;
        createInvisibleButton();
        this.scenePanel = scenePanel;
    }


    public void createInvisibleButton(){

        repaint();
        setBackground(new Color(0,0,0,0));
        setBackground(Color.magenta);
        repaint();
        setBounds(positionX,positionY,buttonWidth,buttonHeight);
        setFocusPainted(false);
//        setContentAreaFilled(false);
        setBorderPainted(false);
        addSceneEventsListener(this);

    }

    public void addSceneEventsListener(ObjectHidingButton button){
        addMouseListener(new SceneObjectEvents(associatedLabel, scenePanel){
            @Override
            public void mouseClicked(MouseEvent e) {
                imageLabel.setVisible(false);
//                this.scenePanel.score += 100;

            }
        });
    }

}
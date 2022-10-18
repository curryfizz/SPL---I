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
    int myIndex;

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
        setBackground(new Color(0,0,0,0));
//        setBackground(null);
        setBackground(Color.pink);
//        setVisible(false);
        repaint();

        setFocusPainted(false);
        setBorderPainted(false);

        setEnabled(false); // plz uncomment me
//        setEnabled(true);// debugging

//        setBackground(C);
        setBounds(positionX,positionY,buttonWidth,buttonHeight);
//        setOpaque(false);
//        setContentAreaFilled(false);


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
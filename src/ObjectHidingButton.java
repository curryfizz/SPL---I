package src;
import javax.swing.*;
import java.awt.*;

public class ObjectHidingButton extends JButton {

    int positionX;
    int positionY;
    int buttonWidth;
    int buttonHeight;
    JLabel associatedLabel;

    public ObjectHidingButton(int posx, int posy, int width, int height, JLabel label){
        this.positionX = posx;
        this.positionY = posy;
        this.buttonWidth = width;
        this.buttonHeight = height;
        this.associatedLabel = label;
        createInvisibleButton();

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
        addMouseListener(new SceneObjectEvents(associatedLabel));

    }

}
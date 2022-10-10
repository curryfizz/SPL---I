package src;

import javax.swing.*;
import java.awt.*;

public class StartButton extends JButton {

    StartButton(DeviceScreenInformation deviceScreenInformation, String text, JFrame jFrame){
        setDefaultPosition(deviceScreenInformation);
        removeBackground();
        addBorder();
        removeFocusPaint();
        removeContentArea();
        assignFont(deviceScreenInformation.getResizedFont(50f));
        addButtonText(text);
        setDefaultTextColor();
        setAlignment();
        assignFont(deviceScreenInformation.getResizedFont(50f));

    }

    public void setPosition(int Posx, int Posy, int width, int height){
        setBounds(Posx,Posy,width,height);
    }

    private void setDefaultPosition(DeviceScreenInformation deviceScreenInformation){
        setBounds(deviceScreenInformation.screenWidth/2 - 150, 2*deviceScreenInformation.screenHeight/3, 300,70);
    }

    private void removeBackground(){
        setBackground(null);
    }

    private void addBorder(){
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.white, 2),
                BorderFactory.createEmptyBorder(0,20,0,20)));
    }

    private void setAlignment(){
        setHorizontalAlignment(JButton.CENTER);
    }

    private void removeFocusPaint(){
        setFocusPainted(false);
    }

    private void removeContentArea(){
        setContentAreaFilled(false);
    }

    private void assignFont(Font assignedFont){
        setFont(assignedFont);
    }


    public void setTextColor(Color color){
        setForeground(color);
    }

    private void setDefaultTextColor(){
        setForeground(Color.white);
    }

    private void addButtonText(String text){
        setText(text);
    }

    private void addCloseActions(DeviceScreenInformation deviceScreenInformation, JFrame jFrame){
        addMouseListener(new closeWindowMouseEvents(deviceScreenInformation,jFrame));
    }
}

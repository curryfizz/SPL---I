package src;

import javax.swing.*;
import java.awt.*;

public class CloseButton extends JButton {

    //too many one line method
    CloseButton(DeviceScreenInformation deviceScreenInformation, String text, JFrame jFrame){
        setDefaultPosition(deviceScreenInformation);
        removeBackground();
        removeBorder();
        removeFocusPaint();
        removeContentArea();
        assignFont(deviceScreenInformation.getResizedFont(50f));
        addExitText(text);
        setDefaultTextColor();
        addCloseActions(deviceScreenInformation,jFrame);
    }

    public void setPosition(int Posx, int Posy, int width, int height){
        setBounds(Posx,Posy,width,height);
    }

    private void setDefaultPosition(DeviceScreenInformation deviceScreenInformation){
        setBounds(deviceScreenInformation.screenWidth-50, 5, 50,50);
    }
    private void removeBackground(){
        setBackground(null);
    }

    private void removeBorder(){
        setBorder(null);
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

    private void addExitText(String text){
        setText(text);
    }

    public void setTextColor(Color color){
        setForeground(color);
    }

    private void setDefaultTextColor(){
        setForeground(Color.white);
    }

    private void addCloseActions(DeviceScreenInformation deviceScreenInformation, JFrame jFrame){
        addMouseListener(new closeWindowMouseEvents(deviceScreenInformation,jFrame));
    }
}

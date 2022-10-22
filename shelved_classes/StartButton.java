package shelved_classes;

import src.DeviceInformation;
import src.FontInfo;

import javax.swing.*;
import java.awt.*;

public class StartButton extends JButton {
    DeviceInformation deviceInformation;
    FontInfo fontInfo;

    StartButton(DeviceInformation deviceInformation, String text, JFrame jFrame, FontInfo fontInfo){
        this.deviceInformation = deviceInformation;
        this.fontInfo = fontInfo;
        setDefaultPosition(deviceInformation);
        removeBackground();
        addBorder();
        removeFocusPaint();
        removeContentArea();
        assignFont(fontInfo.getResizedFont(50f));
        addButtonText(text);
        setDefaultTextColor();
        setAlignment();
        assignFont(fontInfo.getResizedFont(50f));

    }

    public void setPosition(int Posx, int Posy, int width, int height){
        setBounds(Posx,Posy,width,height);
    }

    private void setDefaultPosition(DeviceInformation deviceInformation){
        setBounds(deviceInformation.screenWidth/2 - 150, 2* deviceInformation.screenHeight/3, 300,70);
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

//    private void addCloseActions(DeviceScreenInformation deviceScreenInformation, JFrame jFrame, FontInfo fontInfo){
//        addMouseListener(new closeWindowMouseEvents(fontInfo,jFrame,exi));
//    }
}

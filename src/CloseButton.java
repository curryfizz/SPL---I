package src;

import javax.swing.*;
import java.awt.*;

public class CloseButton extends JButton {

    //too many one line methods
    DeviceScreenInformation deviceScreenInformation;
    FontInfo fontInfo;
    CloseButton(DeviceScreenInformation deviceScreenInformation, String text, JFrame jFrame, FontInfo fontInfo){
        this.deviceScreenInformation = deviceScreenInformation;
        this.fontInfo = fontInfo;
        setDefaultPosition(deviceScreenInformation);
        removeBackground();
        removeBorder();
        removeFocusPaint();
        removeContentArea();
        assignFont(fontInfo.getResizedFont(50f));
        addExitText(text);
        setDefaultTextColor();
        addCloseActions(fontInfo,jFrame);
        jFrame.repaint();
        jFrame.revalidate();
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

    private void addCloseActions(FontInfo fontInfo, JFrame jFrame){
        addMouseListener(new closeWindowMouseEvents(fontInfo,jFrame));
    }
}

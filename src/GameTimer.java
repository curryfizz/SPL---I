package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameTimer extends JLabel{
    JFrame jFrame;
    JPanel backGroundPanel;
    DeviceScreenInformation deviceScreenInformation;
    FontInfo fontInfo;
    Timer timer;
    int second;
    int minute;

    public GameTimer(JFrame jFrame, JPanel backGroundPanel, DeviceScreenInformation deviceScreenInformation, FontInfo fontInfo) {
        this.jFrame = jFrame;
        this.backGroundPanel = backGroundPanel;
        this.deviceScreenInformation = deviceScreenInformation;
        this.fontInfo = fontInfo;

//        backGroundPanel.setLayout(null);
//        backGroundPanel.setBounds(0, 0, 80, 60);
//        backGroundPanel.setBackground(new Color(150, 150, 150));

        second = 10;
        minute = 0;

        SetupTimerLabel();
        backGroundPanel.add(this);

        simpleTimer();
        timer.start(); // kinda like background operation hoye jay

    }

    public void SetupTimerLabel(){
//        counterLabel = new JLabel();
        this.setBounds(5,5, 100, 50);
        this.setBackground(Color.gray);
        this.setForeground(Color.black);
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.white, 2),
                BorderFactory.createEmptyBorder(0,20,0,20)));
        this.setOpaque(true);
        this.setFont(fontInfo.getResizedFont(29f));
        ConfirmationWindowPopup timeUpWindowPopup = new ConfirmationWindowPopup(fontInfo);
        jFrame.add(timeUpWindowPopup);
        drawTimer();

        backGroundPanel.add(this);
    }

    public void simpleTimer() {

        timer = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (second <=0)
                {
                    if(minute <=0 ){
                        timeOver();
                        return;
                    }
                    minute --;
                    second = 60;
                }
                second --;
                drawTimer();
                backGroundPanel.repaint();
                jFrame.repaint();
            }

        }); // updates every 1 second
    }

    public void drawTimer() {
        if(minute<10 && second<10) {
            setText ("0" + minute + ":" + "0" + second);
        }
        else if (minute<10) {
            setText ("0" + minute + ":" + second);
        }
        else if (second<10) {
            setText ( minute + ":" + "0" + second);
        }
        else {
            setText (minute + ":" + second);
        }
    }

    private void timeOver(){ //the popup glitches idk why orz
        timer.stop();
        int choice = ConfirmationWindowPopup.showConfirmDialog(jFrame,"Oh no! Your Time is Up","Time Up",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
        if(choice==JOptionPane.OK_OPTION){
            System.exit(0);
        }
    }
}

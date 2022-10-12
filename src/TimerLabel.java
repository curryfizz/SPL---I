package src;

import javax.swing.*;
import java.awt.*;

public class TimerLabel extends JLabel implements Runnable{
    JFrame jFrame;
    JPanel backGroundPanel;
    DeviceScreenInformation deviceScreenInformation;
    FontInfo fontInfo;
    int second;
    int minute;
    long StartTimeMili;
    int FPS = 60;
    Thread TimerThread;
    boolean isTimeOver = false;
    Timer timer;

    public TimerLabel(JFrame jFrame, JPanel backGroundPanel, DeviceScreenInformation deviceScreenInformation, FontInfo fontInfo) {
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
        backGroundPanel.repaint();
        backGroundPanel.revalidate();

        StartTimer();
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

        drawTimer();
        backGroundPanel.repaint();
        backGroundPanel.revalidate();
        backGroundPanel.add(this);
    }

    public void StartTimer() {
        TimerThread = new Thread(this);
        StartTimeMili = System.currentTimeMillis();
        TimerThread.start(); // starts run method in another thread
    }

    private void UpdateTimeVariables(){
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
        isTimeOver = true;
//        timer.stop();
        ConfirmationWindowPopup timeUpWindowPopup = new ConfirmationWindowPopup(fontInfo);
        backGroundPanel.add(timeUpWindowPopup);
        backGroundPanel.repaint();
        backGroundPanel.revalidate();
        jFrame.repaint();
        jFrame.revalidate();
        int choice = ConfirmationWindowPopup.showConfirmDialog(backGroundPanel,"Oh no! Your Time is Up","Time Up",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
        if(choice==JOptionPane.OK_OPTION){
            System.exit(0);
        }

    }

    @Override
    public void run() {

        long lastUpdatedAt = StartTimeMili;
        long milisecs = 0;

        while(!isTimeOver) {
            milisecs = System.currentTimeMillis() - lastUpdatedAt;
            drawTimer();
//            backGroundPanel.repaint();
//            backGroundPanel.revalidate();
            jFrame.repaint();
//            jFrame.revalidate();
            this.repaint();

            if(milisecs > 1000){ //one second has passed
                UpdateTimeVariables();
                lastUpdatedAt = System.currentTimeMillis();
            }
        }



//        timer = new Timer(1000, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                UpdateTimeVariables();
//                drawTimer();
//                backGroundPanel.repaint();
//                backGroundPanel.revalidate();
//            }
//
//        });
//        timer.start();
    }
}

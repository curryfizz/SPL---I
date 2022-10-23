package src;

import javax.swing.*;
import java.awt.*;

public class TimerLabel extends JLabel implements Runnable{
    JFrame jFrame;
    ALevelPanel backGroundPanel;

    ConfirmationWindowPopup timeUpWindowPopup;
    int second;
    int minute;
    long StartTimeMili;
    int FPS = 60;
    Thread TimerThread;

    int elapsedTime = 0;

//    int choice;
    boolean isTimeOver = false;
    public TimerLabel(JFrame jFrame, ALevelPanel backGroundPanel) {
        this.jFrame = jFrame;
        this.backGroundPanel = backGroundPanel;

//        backGroundPanel.setLayout(null);
//        backGroundPanel.setBounds(0, 0, 80, 60);
//        backGroundPanel.setBackground(new Color(150, 150, 150));

//        second = 10;
//        minute = 2;

        SetupTimerLabel();
//        backGroundPanel.repaint();
//        backGroundPanel.revalidate();
        this.setHorizontalAlignment(SwingConstants.CENTER);

//        StartTimer();
    }


//    public void disableRemainingObjects(){
//        if(backGroundPanel instanceof DormRoomSceneT){
//
//
//            for(int i=0; i<((DormRoomSceneT)backGroundPanel).RandObjIndices.size(); i++){
//                ((DormRoomSceneT)backGroundPanel).buttonList.get(i).setEnabled(false);
//            }
//
//        }
//    }


    public void endLevel(){
        isTimeOver=true;
        backGroundPanel.EndLevel();


    }
    public void SetupTimerLabel(){
//        counterLabel = new JLabel();
        this.setBounds(5,5, 120, 45);
        this.setBackground(Color.decode("#14171C"));
        this.setForeground(Color.white);
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.white, 2),
                BorderFactory.createEmptyBorder(0,20,0,20)));
        this.setOpaque(true);
        this.setFont(FontInfo.getResizedFont(35f));

        drawTimer();
        backGroundPanel.repaint();
        backGroundPanel.revalidate();
        backGroundPanel.add(this);
        backGroundPanel.revalidate();
        backGroundPanel.repaint();
    }

    public void StartTimer() { //starts outside the class

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
        elapsedTime ++;
        System.out.println(elapsedTime);
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
        timeUpWindowPopup = new ConfirmationWindowPopup();
        backGroundPanel.add(timeUpWindowPopup);
        backGroundPanel.repaint();
        backGroundPanel.revalidate();
        jFrame.repaint();
        jFrame.revalidate();
        TimeOverConfirmationDialog timeOverConfirmationDialog = new TimeOverConfirmationDialog(jFrame, backGroundPanel);

    }

    @Override
    public void run() {

        long lastUpdatedAt = System.currentTimeMillis();
        long milisecs = 0;

        while(!isTimeOver) {
            milisecs = System.currentTimeMillis() - lastUpdatedAt;
            drawTimer();
            jFrame.repaint();
            this.repaint();

            if(milisecs > 1000){ //one second has passed
                UpdateTimeVariables();
                lastUpdatedAt = System.currentTimeMillis();
                if(minute==0 && second==30){
                    this.setForeground(new Color(200,0,0));
                }
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

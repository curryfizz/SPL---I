package src;

import javax.swing.*;
import java.awt.*;

public class TimerLabel extends JLabel implements Runnable{
    JFrame jFrame;
    JPanel backGroundPanel;
    DeviceScreenInformation deviceScreenInformation;

    ConfirmationWindowPopup timeUpWindowPopup;
    FontInfo fontInfo;
    int second;
    int minute;
    long StartTimeMili;
    int FPS = 60;
    Thread TimerThread;

    int score;
    LoadingAnimationT loadingAnimationT;

    JPanel nextScene;
    int choice;
    boolean isTimeOver = false;
    public TimerLabel(JFrame jFrame, JPanel backGroundPanel, DeviceScreenInformation deviceScreenInformation, FontInfo fontInfo) {
        this.jFrame = jFrame;
        this.backGroundPanel = backGroundPanel;
        this.deviceScreenInformation = deviceScreenInformation;
        this.fontInfo = fontInfo;

//        backGroundPanel.setLayout(null);
//        backGroundPanel.setBounds(0, 0, 80, 60);
//        backGroundPanel.setBackground(new Color(150, 150, 150));

//        second = 10;
//        minute = 2;
        score=0;

        SetupTimerLabel();
        backGroundPanel.repaint();
        backGroundPanel.revalidate();
        this.setHorizontalAlignment(SwingConstants.CENTER);

//        StartTimer();
    }


    public void disableRemainingObjects(){
        if(backGroundPanel instanceof DormRoomSceneT){

            for(int i=0; i<((DormRoomSceneT)backGroundPanel).RandObjIndices.size(); i++){
                ((DormRoomSceneT)backGroundPanel).buttonList.get(i).setEnabled(false);
            }

        }
    }


    public void endLevel(){
        isTimeOver=true;
        if(backGroundPanel instanceof DormRoomSceneT){
            ((DormRoomSceneT)backGroundPanel).resetItemNameLabelList();
//            disableRemainingObjects();
            ((DormRoomSceneT)backGroundPanel).remove(((DormRoomSceneT) backGroundPanel).bigItemListLabel);
            ((DormRoomSceneT)backGroundPanel).revalidate();
            ((DormRoomSceneT)backGroundPanel).repaint();
            ((DormRoomSceneT)backGroundPanel).showItemNamesInTextBox();
            ((DormRoomSceneT)backGroundPanel).revalidate();
            ((DormRoomSceneT)backGroundPanel).repaint();

        }

        jFrame.remove(backGroundPanel);
        loadingAnimationT.changeNextScene(nextScene);
        if(loadingAnimationT.nextScene instanceof MapT){
            ((MapT) loadingAnimationT.nextScene).score += score;
            ((MapT) loadingAnimationT.nextScene).updateScore();

        }
        jFrame.add(loadingAnimationT);
        loadingAnimationT.initializeTimer();
        jFrame.revalidate();
        jFrame.repaint();
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
        this.setFont(fontInfo.getResizedFont(35f));

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
        score = 0;
//        timer.stop();
        timeUpWindowPopup = new ConfirmationWindowPopup(fontInfo);
        backGroundPanel.add(timeUpWindowPopup);
        backGroundPanel.repaint();
        backGroundPanel.revalidate();
        jFrame.repaint();
        jFrame.revalidate();
        TimeOverConfirmationDialog timeOverConfirmationDialog = new TimeOverConfirmationDialog(jFrame, fontInfo, backGroundPanel);

    }

    @Override
    public void run() {

        long lastUpdatedAt = System.currentTimeMillis();
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

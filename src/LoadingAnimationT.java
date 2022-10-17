package src;

import shelved_classes.IScene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadingAnimationT extends JPanel implements ActionListener,Runnable {
    JFrame jFrame;

    JPanel nextScene;
    DeviceScreenInformation deviceInfo;
    FontInfo fontInfo;
    Timer timer;
    JLabel loadingText;
    long animationStartTime;
    int loadingBarPosX, loadingBarPosY, loadingBarWidth;
    int loadingDotPosX;
    int loadingDotPosY;
    int loadingDotMaxX; //maximum x position of the dot, will return to initial position
    int loadingDotCurrentX; //current dot position, will be used by paint/repaint
    int animationDuration;

    boolean timerStopped;
    long animationRunTime;
    int increment;
    LoadingAnimationT(JFrame jFrame, DeviceScreenInformation deviceScreenInformation, FontInfo fontInfo, int animationDuration, JPanel nextScene){
        this.jFrame = jFrame;
        this.deviceInfo = deviceScreenInformation;
        this.fontInfo = fontInfo;
        this.animationDuration = animationDuration;
        this.nextScene = nextScene;
        this.increment = 500/(animationDuration*20);

//        timer.start();
//        animationStartTime = System.nanoTime();

    }

    public void changeNextScene(JPanel nextScene){
        this.nextScene = nextScene;
    }

    private void buildScene(){
        setPreferredSize(new Dimension(deviceInfo.screenWidth,deviceInfo.screenHeight));
        setBackground(Color.decode("#14171C"));
        setLayout(null);
        SetLoadingText();
    }

    public void initializeTimer(){
        timer = new Timer(50,this);
        timer.start();
        timerStopped=false;
        loadingBarWidth=1;
        animationStartTime = System.currentTimeMillis();
        animationRunTime=0;
    }
    private void initializeAnimationParameters(){
        loadingBarPosX = deviceInfo.screenWidth/3;
        loadingBarWidth =1;
        loadingBarPosY = deviceInfo.screenHeight/2 + 50;
        loadingDotPosY = deviceInfo.screenHeight/2+120;
        loadingDotPosX = deviceInfo.screenWidth/2 +65;
        loadingDotCurrentX = loadingDotPosX;
        loadingDotMaxX = loadingDotCurrentX +50;
    }
    private void SetLoadingText(){
        loadingText =  new JLabel("Loading");
        loadingText.setFont(fontInfo.getResizedFont(50f));
        loadingText.setBounds(deviceInfo.screenWidth/2 - 50,deviceInfo.screenHeight/2+100,200,50);
        loadingText.setBackground(null);
        loadingText.setForeground(Color.white);
        add(loadingText);
    }

    public void paint(Graphics g){
        super.paint(g); //paint background
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.white);
        g2d.fillOval(loadingDotCurrentX, loadingDotPosY, 10,10);
        g2d.fillRect(loadingBarPosX, loadingBarPosY +10, loadingBarWidth,20);

    }

    public boolean isrunning(){
        if(timerStopped){
            return false;
        }else{
            return true;
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        animationRunTime = (System.currentTimeMillis()-animationStartTime)/1000;
        if(animationRunTime > animationDuration){
            timerStopped=true;
            timer.stop();
            try {
                Thread.sleep((long)100);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            jFrame.remove(this);

            if(nextScene instanceof IScene){
                ((IScene) nextScene).startScene();
            }
            jFrame.add(nextScene);
            jFrame.revalidate();
            jFrame.repaint();
//            LoadMenu(this.jFrame);
//            LoadMenu();
        }

        if(loadingBarWidth <500){
            loadingBarWidth = loadingBarWidth +increment;
        }

        if (loadingDotCurrentX > loadingDotMaxX){
            loadingDotCurrentX = loadingDotPosX;
        }else{
            loadingDotCurrentX += 2;
        }

        repaint();
    }

    public void LoadMenu(){

//        Map map = new Map(jFrame);
        jFrame.remove(this);
//        jFrame.add(map);
        jFrame.revalidate();
        jFrame.repaint();
    }


    @Override
    public void run() {

        buildScene();
        initializeAnimationParameters();
//        initializeTimer();
    }

}

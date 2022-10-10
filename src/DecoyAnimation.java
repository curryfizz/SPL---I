package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DecoyAnimation extends JPanel implements ActionListener{
    JFrame jFrame;
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
    DecoyAnimation(JFrame jFrame, DeviceScreenInformation deviceScreenInformation, FontInfo fontInfo, int animationDuration){
        this.jFrame = jFrame;
        this.deviceInfo = deviceScreenInformation;
        this.fontInfo = fontInfo;
        this.animationDuration = animationDuration;

        setPreferredSize(new Dimension(deviceInfo.screenWidth,deviceInfo.screenHeight));
        setBackground(Color.decode("#14171C"));
        setLayout(null);
        SetLoadingText();

        loadingBarPosX = deviceInfo.screenWidth/3;
        loadingBarWidth =1;
        loadingBarPosY = deviceInfo.screenHeight/2 + 50;
        loadingDotPosY = deviceInfo.screenHeight/2+200;
        loadingDotPosX = deviceInfo.screenWidth/2 +50;
        loadingDotCurrentX = loadingDotPosX;
        loadingDotMaxX = loadingDotCurrentX +20;

        timer = new Timer(30, this);
        timer.start();
        animationStartTime = System.nanoTime();

        jFrame.add(this);
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

    @Override
    public void actionPerformed(ActionEvent e) {

        if((System.nanoTime() - animationStartTime)/1000000000 == animationDuration){
//            LoadMenu(this.jFrame);
            LoadMenu();
        }

        if(loadingBarWidth <500){
            loadingBarWidth = loadingBarWidth +2;
        }

        if (loadingDotCurrentX > loadingDotMaxX){
            loadingDotCurrentX = loadingDotPosX;
        }else{
            loadingDotCurrentX += 6;
        }
        repaint();
    }

    public void LoadMenu(){
        Map map = new Map(jFrame);
        jFrame.add(map);
        jFrame.revalidate();
        jFrame.remove(this);
        jFrame.revalidate();
    }

}

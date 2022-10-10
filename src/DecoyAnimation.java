package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class DecoyAnimation extends JPanel implements ActionListener{
    Font eastSeaDokdo ;
    JFrame jFrame;
    GraphicsEnvironment ge;
    JLabel loadingText;
    int height;
    int width;

    Image mark;

    int loadingBarPosX, loadingBarPosY, og, loadingBarWidth;
    Timer timer;
    long animationStartTime;
    int loadingDotPosX;

    int loadingDotPosY;

    int loadingDotMaxX; //maximum x postion of the dot, will return to initial position

    int loadingDotCurrentX; //current dot postion, will be used by paint/repaint
    int seconds_passed;
    DecoyAnimation(JFrame jFrame, int seconds_passed){
        this.jFrame = jFrame;
        ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        importFont();

        height = ge.getMaximumWindowBounds().height;
        width = ge.getMaximumWindowBounds().width;
        setPreferredSize(new Dimension(width,height));
        setBackground(Color.decode("#14171C"));
        loadingBarPosX = width/3;
        loadingBarWidth =1;
        loadingBarPosY = height/2 + 50;
        loadingDotPosY = height/2+200;
        loadingDotPosX = width/2 +50;
        loadingDotCurrentX = loadingDotPosX;
        loadingDotMaxX = loadingDotCurrentX +20;
        this.seconds_passed = seconds_passed;
        loadingText =  new JLabel("Loading");
        loadingText.setFont(eastSeaDokdo);
        setLayout(null);
        loadingText.setBounds(width/2 - 50,height/2+100,200,50);
        loadingText.setBackground(null);
        loadingText.setForeground(Color.white);
        add(loadingText);
        timer = new Timer(30, this);
        timer.start();
        animationStartTime = System.nanoTime();

        jFrame.add(this);
    }

    public void paint(Graphics g){
        super.paint(g); //paint background
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.white);
        g2d.fillOval(loadingDotCurrentX, loadingDotPosY, 10,10);
        g2d.fillRect(loadingBarPosX, loadingBarPosY +10, loadingBarWidth,20);

    }

    private void importFont(){
        //registering custom font
        try {
            eastSeaDokdo = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/EastSeaDokdo-Regular.ttf"));
            eastSeaDokdo = eastSeaDokdo.deriveFont(50f);
            ge.registerFont(eastSeaDokdo);
        }catch(Exception e){
            //filoIO errors
            //custom font will be set to Monospaced;
            eastSeaDokdo = new Font("Monospaced",Font.BOLD,17);
        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if((System.nanoTime() - animationStartTime)/1000000000 == seconds_passed){
            System.exit(0);
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
}

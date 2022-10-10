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
    int xVelocity = 1;

    int getxVelocity = 1;
    int x, y , og,xr,xy;
    Timer timer;
    long start;
    int dotx;
    int seconds;
    DecoyAnimation(JFrame jFrame, int seconds_passed){
        this.jFrame = jFrame;
        ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        importFont();

        height = ge.getMaximumWindowBounds().height;
        width = ge.getMaximumWindowBounds().width;
        setPreferredSize(new Dimension(width,height));
        setBackground(Color.decode("#14171C"));
        x = width/3;
        xr=1;
        xy=1;
        og  = x;
        y = height/2 + 50;
        seconds = seconds_passed;
        dotx = width/2 +50;
        loadingText =  new JLabel("Loading");
        loadingText.setFont(eastSeaDokdo);
        setLayout(null);
        loadingText.setBounds(width/2 - 50,height/2+100,200,50);

        loadingText.setBackground(null);
        loadingText.setForeground(Color.white);
        add(loadingText);
        timer = new Timer(15, this);
        timer.start();
        start = System.nanoTime();

        jFrame.add(this);
    }

    public void paint(Graphics g){
        super.paint(g); //paint background
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.white);
        g2d.fillOval(dotx, height/2+200, 10,10);
        g2d.fillRect(og,y+10,xr,20);

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

        if((System.nanoTime() - start)/1000000000 == seconds  ){
            System.exit(0);
        }
        if(x>=2*og){
            x=og;
//            xVelocity*=-1;
        }
        if(xr<500){
            xr = xr+2;
        }
        x = x+xVelocity;
//        xr = xr+xVelocity;

        repaint();
    }
}

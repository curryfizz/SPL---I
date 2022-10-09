package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

public class AnimationTest extends JPanel implements ActionListener{
    Font eastSeaDokdo ;
    JFrame jFrame;
    GraphicsEnvironment ge;

    int height;
    int width;
    Image mark;

    int xVelocity = 1;
    int getxVelocity = 1;

    int x, y , og,xr,xy;
    Image question;
    Timer timer;
    AnimationTest(){

        ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        importFont();
        mark = new ImageIcon("images/questionmark.png").getImage();
        height = ge.getMaximumWindowBounds().height;
        width = ge.getMaximumWindowBounds().width;
        jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        jFrame.setBackground(Color.decode("#14171C"));
        jFrame.setUndecorated(true);
        this.setPreferredSize(new Dimension(width,height));
        this.setBackground(Color.decode("#14171C"));
        jFrame.add(this);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        JButton jButton = new JButton();
        jButton.setText("X");
        this.add(jButton);
        jButton.setBorder(null);
        jButton.setForeground(Color.white);
        jButton.setBackground(null);
        jButton.setFont(eastSeaDokdo);
        jButton.setBounds(1000,600, 50,50);
        x = width/3;
        xr=1;
        xy=1;
        og  = x;
        y = height/2 + 50;
        jButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                jFrame.dispose();
                System.exit(0);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        jFrame.pack();
//        jFrame.setUndecorated(true);
        jFrame.setVisible(true);
        timer = new Timer(100, this);
        timer.start();


    }

    public void paint(Graphics g){
        super.paint(g); //paint background
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.white);
        g2d.setFont(eastSeaDokdo);
        g2d.drawString("?",x,y);
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

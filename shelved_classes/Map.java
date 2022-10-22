package shelved_classes;


import src.CloseButton;
import src.DeviceInformation;
import src.FontInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Map extends JPanel implements IScene {

    DeviceInformation deviceInfo;
    FontInfo fontInfo;


    CloseButton closeButton;
    JFrame jFrame;

    JButton DormButton;
    JLabel backgroundLabel;
    int width;
    int height;
    public Map(JFrame jFrame, DeviceInformation deviceInformation, FontInfo fontInfo){
        this.jFrame = jFrame;
//        jFrame.revalidate();
//        jFrame.repaint();
        this.deviceInfo = deviceInformation;
        this.fontInfo =fontInfo;
        width = deviceInfo.screenWidth;
        height = deviceInfo.screenHeight;
        createBackground();
        createMapBackground();
        addCloseButton();
        addElementaryButton();
        jFrame.add(this);
    }


    public void createBackground(){
        this.setLayout(null);
        this.setPreferredSize(new Dimension(width,height));
//        this.setBackground(Color.decode("#14171C"));
//        this.setForeground(Color.decode("#C64C1D"));
        this.setBackground(Color.CYAN);
    }

    public void createMapBackground(){
        backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0,0, deviceInfo.screenWidth, deviceInfo.screenHeight);
        backgroundLabel.setIcon(getDefaultImage());
        this.add(backgroundLabel);
    }

    public ImageIcon getDefaultImage(){
        ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource("images/Level images/LevelbackgroundColored.png"));
        Image image = imageIcon.getImage();
        image = image.getScaledInstance(deviceInfo.screenWidth, deviceInfo.screenHeight, Image.SCALE_DEFAULT);
        imageIcon = new ImageIcon(image);
        return imageIcon;
    }

    public void addCloseButton(){
        closeButton = new CloseButton( "X", jFrame);
        closeButton.setTextColor(Color.black);
        this.add(closeButton);
    }

    private void addElementaryButton(){
        DormButton = new JButton("Dorm Scene");
        DormButton.setBounds(deviceInfo.screenWidth/2-150,2*deviceInfo.screenHeight/3, 300,70);
        DormButton.setHorizontalAlignment(JButton.CENTER);
        DormButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.white, 2),
                BorderFactory.createEmptyBorder(0,20,0,20)));
        DormButton.setBackground(Color.gray);
        DormButton.setForeground(Color.white);
        DormButton.setFocusPainted(false);
        DormButton.setContentAreaFilled(true);
        DormButton.setFont(fontInfo.getResizedFont(50f));
//        this.setVisible(true);
        this.add(DormButton);
        DormButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                StartDormScene();
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
    }

    public void StartDormScene(){
        System.out.println("Entered Dorm Scene");
        DecoyAnimation decoyAnimation = new DecoyAnimation(new Map(jFrame,deviceInfo,fontInfo),jFrame, deviceInfo,fontInfo,7);
//        this.setVisible(false);

//        DormButton.setOpaque(false);
//        DormButton.setVisible(false);
//        this.remove(closeButton);
//        repaint();
//        closeButton.setOpaque(false);
//        closeButton.setVisible(false);
//        this.remove(closeButton);
//        repaint();

//        backgroundLabel.setVisible(false);
//        DecoyAnimation decoyAnimation = new DecoyAnimation(new DormRoom(jFrame,deviceInfo,fontInfo),jFrame,deviceInfo,fontInfo,7);
//        jFrame.removeAll();
//        this.remove(backgroundLabel);
//        jFrame.repaint();
        jFrame.remove(this);
        jFrame.revalidate();
        jFrame.repaint();

    }

    @Override
    public void callSelf() {
        new Map(jFrame,deviceInfo,fontInfo);
    }

    @Override
    public void startScene() {

    }
}

package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

public class MessageFromMomT extends JPanel implements Runnable {

    JFrame jFrame;
    DeviceScreenInformation deviceInfo;
    FontInfo fontInfo;
    JLabel backgroundLabel;
    JLabel Bubble1;
    JLabel Bubble2;
    int taps = 0;

    public  MessageFromMomT(JFrame jFrame, DeviceScreenInformation deviceInfo, FontInfo fontInfo){
        this.jFrame = jFrame;
        this.deviceInfo = deviceInfo;
        this.fontInfo = fontInfo;
    }
    public void addCustomWindowCloseButton(){
        CloseButton closeButton = new CloseButton(deviceInfo,"X",jFrame, fontInfo);
        this.add(closeButton);
    }

    public void buildScene(){
        createBubble1();
        createBubble2();
        add(Bubble2);
        add(Bubble1);
        addCustomWindowCloseButton();
        addTapToContinue();
        addListener();
        createBackground("images/PhoneScreenImages/PhoneScreen.png");
        this.repaint();
        this.repaint();
    }

    private void createBubble2() {
        Bubble2 = new JLabel();
        Bubble2.setBounds(0,0, deviceInfo.screenWidth, deviceInfo.screenHeight);
        Bubble2.setVisible(false);
        ImageIcon  bubbleIcon= new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("images/PhoneScreenImages/TwoSpeechBubbles.png")));
        Image image1 = bubbleIcon.getImage();
        image1 = image1.getScaledInstance(deviceInfo.screenWidth, deviceInfo.screenHeight, Image.SCALE_DEFAULT);
        ImageIcon bubbleIconScaled = new ImageIcon(image1);

        Bubble2.setIcon(bubbleIconScaled);
    }

    private void createBubble1() {
        Bubble1 = new JLabel();
        Bubble1.setBounds(0,0, deviceInfo.screenWidth, deviceInfo.screenHeight);
        Bubble1.setVisible(false);
        ImageIcon  bubbleIcon= new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("images/PhoneScreenImages/LeftSpeechBubble.png")));
        Image image1 = bubbleIcon.getImage();
        image1 = image1.getScaledInstance(deviceInfo.screenWidth, deviceInfo.screenHeight-100, Image.SCALE_DEFAULT);
        ImageIcon bubbleIconScaled = new ImageIcon(image1);

        Bubble1.setIcon(bubbleIconScaled);

    }

    private void addListener() {
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                taps++;
                if(taps == 1 ){
                    Bubble1.setVisible(true);
                    repaint();
                    revalidate();
                }else if(taps == 2){
                    Bubble1.setVisible(false);
                    Bubble2.setVisible(true);
                    repaint();
                    revalidate();
                }else {
                    // next scene
                }

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

    private void addTapToContinue() {
        JLabel TapToContinue = new JLabel("Tap to Continue", SwingConstants.CENTER);
        TapToContinue.setBounds((deviceInfo.screenWidth/2) - 150, deviceInfo.screenHeight-50, 300, 50);
        TapToContinue.setBackground(Color.BLACK);
        TapToContinue.setForeground(Color.decode("#14171C"));
        TapToContinue.setFont(fontInfo.getResizedFont(42f));
        this.add(TapToContinue);
        revalidate();
        repaint();
    }

    public void createBackground(String pictureLocation) {
        this.setBounds(0, 0, deviceInfo.screenWidth, deviceInfo.screenHeight);//size of the background image
        this.setBackground(Color.black);
        this.setLayout(null);

        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(pictureLocation)));
        Image image = imageIcon.getImage();
        image = image.getScaledInstance(deviceInfo.screenWidth, deviceInfo.screenHeight, Image.SCALE_DEFAULT);
        ImageIcon imageIconScaled = new ImageIcon(image);
        backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0,0, deviceInfo.screenWidth,deviceInfo.screenHeight);
        backgroundLabel.setIcon(imageIconScaled);
        this.add(backgroundLabel);
        repaint();
        revalidate();
        jFrame.add(this);
    }

    @Override
    public void run() {
        buildScene();
    }
}

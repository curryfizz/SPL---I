package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

public class MessageFromMomT extends JPanel implements Runnable {

    JFrame jFrame;
//    DeviceInformation deviceInfo;
//    FontInfo fontInfo;
    JLabel backgroundLabel;
    JLabel Bubble1;
    JLabel Bubble2;
    JLabel Text1;
    JLabel Text2;
    LoadingAnimationT loadingAnimationT;
    JPanel nextScene;
    int taps = 0;

    public  MessageFromMomT(JFrame jFrame){
        this.jFrame = jFrame;
//        this.deviceInfo = deviceInfo;
//        this.fontInfo = fontInfo;
        this.setLayout(null);
    }
    public void addCustomWindowCloseButton(){
        CloseButton closeButton = new CloseButton("X",jFrame);
        this.add(closeButton);
    }

    public void buildScene(){
        createText1();
        createText2();
        createBubble1();
        createBubble2();


        addCustomWindowCloseButton();
        addTapToContinue();
        addListener();
        createBackground("images/PhoneScreenImages/PhoneScreen.png");
        this.repaint();
        this.repaint();
    }


    private void createBubble1() {
        Bubble1 = new JLabel();
        Bubble1.setLayout(null);
        Bubble1.setBounds(0,0, DeviceInformation.screenWidth, DeviceInformation.screenHeight);
        Bubble1.setVisible(false);
        ImageIcon  bubbleIcon= new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("images/PhoneScreenImages/LeftSpeechBubble.png")));
        Image image1 = bubbleIcon.getImage();
        image1 = image1.getScaledInstance(DeviceInformation.screenWidth, DeviceInformation.screenHeight, Image.SCALE_DEFAULT);
        ImageIcon bubbleIconScaled = new ImageIcon(image1);

        Bubble1.setIcon(bubbleIconScaled);
        add(Bubble1);

    }
    private void createBubble2() {
        Bubble2 = new JLabel();
        Bubble2.setLayout(null);
        Bubble2.setBounds(0,0, DeviceInformation.screenWidth, DeviceInformation.screenHeight);
        Bubble2.setVisible(false);
        ImageIcon  bubbleIcon= new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("images/PhoneScreenImages/TwoSpeechBubbles.png")));
        Image image1 = bubbleIcon.getImage();
        image1 = image1.getScaledInstance(DeviceInformation.screenWidth, DeviceInformation.screenHeight, Image.SCALE_DEFAULT);
        ImageIcon bubbleIconScaled = new ImageIcon(image1);
        Bubble2.setIcon(bubbleIconScaled);
        add(Bubble2);
    }

    private void createText1(){
        Text1 = new JLabel(
                "<html>Assalamualaikum Dear<br/>Did you reach safely?<br/>Hope your classes are going well.</html>",
                SwingConstants.CENTER);
        Text1.setLayout(null);
        Text1.setBounds(400, 50, 400, 300);
        Text1.setBackground(Color.BLACK);
        Text1.setForeground(Color.decode("#14171C"));
        Text1.setFont(FontInfo.getResizedFont(42f));

        Text1.setVisible(false);
        this.add(Text1);

        revalidate();
        repaint();
    }
    private void createText2(){
        Text2 = new JLabel(
                "<html>Oh I forgot to mention<br/>I packed a special present for you in your bag<br/>Tell me if you like it.</html>",
                SwingConstants.CENTER);
        Text2.setLayout(null);
        Text2.setBounds(1050, 10, 400, 300);
        Text2.setBackground(Color.BLACK);
        Text2.setForeground(Color.decode("#14171C"));
        Text2.setFont(FontInfo.getResizedFont(42f));

        Text2.setVisible(false);
        this.add(Text2);

        revalidate();
        repaint();
    }

    public void PrepareForSceneTransition(LoadingAnimationT loadingAnimationT, JPanel nextScene) {
        this.loadingAnimationT = loadingAnimationT;
        this.nextScene = nextScene;
    }
    public void EndScene(){
        jFrame.remove(this);
        loadingAnimationT.changeNextScene(nextScene); //to map
        jFrame.add(loadingAnimationT);
        loadingAnimationT.initializeTimer();

        jFrame.revalidate();
        jFrame.repaint();
    }

    private void addListener() {
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                taps++;
                if(taps == 1 ){
                    Bubble1.setVisible(true);
                    Text1.setVisible(true);
                    repaint();
                    revalidate();
                }else if(taps == 2){
                    Bubble1.setVisible(false);
                    Bubble2.setVisible(true);
                    Text2.setVisible(true);
                    repaint();
                    revalidate();
                }else {
                    // next scene
                    EndScene();
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
        TapToContinue.setLayout(null);
        TapToContinue.setBounds((DeviceInformation.screenWidth /2) - 150, DeviceInformation.screenHeight -50, 300, 50);
        TapToContinue.setBackground(Color.BLACK);
        TapToContinue.setForeground(Color.decode("#14171C"));
        TapToContinue.setFont(FontInfo.getResizedFont(42f));
        this.add(TapToContinue);
        revalidate();
        repaint();
    }

    public void createBackground(String pictureLocation) {
        this.setBounds(0, 0, DeviceInformation.screenWidth, DeviceInformation.screenHeight);//size of the background image
        this.setBackground(Color.black);
        this.setLayout(null);

        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(pictureLocation)));
        Image image = imageIcon.getImage();
        image = image.getScaledInstance(DeviceInformation.screenWidth, DeviceInformation.screenHeight, Image.SCALE_DEFAULT);
        ImageIcon imageIconScaled = new ImageIcon(image);
        backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0,0, DeviceInformation.screenWidth, DeviceInformation.screenHeight);
        backgroundLabel.setIcon(imageIconScaled);
        this.add(backgroundLabel);
        repaint();
        revalidate();
    }

    @Override
    public void run() {
        buildScene();
    }
}

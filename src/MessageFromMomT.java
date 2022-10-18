package src;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MessageFromMomT extends JPanel implements Runnable {

    JFrame jFrame;
    DeviceScreenInformation deviceInfo;
    FontInfo fontInfo;
    JLabel backgroundLabel;

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
        createBackground("images/PhoneScreenImages/PhoneScreen.png");
        addCustomWindowCloseButton();
        this.repaint();
        this.repaint();
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

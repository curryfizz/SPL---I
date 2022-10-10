package src;

import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel{

    DeviceScreenInformation deviceScreenInformation;

    JLabel backgroundLabel;
    int width;
    int height;
    public Menu(JFrame window){
        deviceScreenInformation = new DeviceScreenInformation();
        width = deviceScreenInformation.screenWidth;
        height = deviceScreenInformation.screenHeight;
        createBackground();
        createMapBackground();
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
        backgroundLabel.setBounds(0,0,deviceScreenInformation.screenWidth,deviceScreenInformation.screenHeight);
        backgroundLabel.setIcon(getDefaultImage());
        this.add(backgroundLabel);
    }

    public ImageIcon getDefaultImage(){
        ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource("images/Level images/LevelbackgroundColored.png"));
        Image image = imageIcon.getImage();
        image = image.getScaledInstance(deviceScreenInformation.screenWidth, deviceScreenInformation.screenHeight, Image.SCALE_DEFAULT);
        imageIcon = new ImageIcon(image);
        return imageIcon;
    }



}

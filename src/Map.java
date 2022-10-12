package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Map extends JPanel{

    DeviceScreenInformation deviceInfo;
    FontInfo fontInfo;
    JFrame window;
    JLabel backgroundLabel;
    int width;
    int height;
    public Map(JFrame window){
        this.window = window;
        window.revalidate();
        window.repaint();
        deviceInfo = new DeviceScreenInformation();
        fontInfo = new FontInfo();
        width = deviceInfo.screenWidth;
        height = deviceInfo.screenHeight;
        createBackground();
        addCloseButton();
        addElementaryButton();
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
        CloseButton closeButton = new CloseButton(deviceInfo, "X", window, fontInfo);
        closeButton.setTextColor(Color.black);
        this.add(closeButton);
    }

    private void addElementaryButton(){
        JButton DormButton = new JButton("Dorm Scene");
        DormButton.setBounds(deviceInfo.screenWidth/2-150,2*deviceInfo.screenHeight/3, 300,70);
        DormButton.setHorizontalAlignment(JButton.CENTER);
        DormButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.white, 2),
                BorderFactory.createEmptyBorder(0,20,0,20)));
        DormButton.setBackground(Color.gray);
        DormButton.setForeground(Color.white);
        DormButton.setFocusPainted(false);
        DormButton.setOpaque(true);
        DormButton.setContentAreaFilled(false);
        DormButton.setFont(fontInfo.getResizedFont(50f));
        this.setVisible(true);
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
        DormRoom dormRoomScene = new DormRoom(window, new DeviceScreenInformation(), new FontInfo());
        window.remove(this);
        window.revalidate();
        window.repaint();

    }

}

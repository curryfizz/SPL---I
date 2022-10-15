package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StartMenuScreenT extends JPanel implements Runnable{
    JFrame jFrame;
    JLabel gameTitle;
    StartGameButton startGameButton;
    DeviceScreenInformation deviceInfo;
    FontInfo fontInfo;
    CloseButton closeButton;

    public StartMenuScreenT(JFrame jFrame, DeviceScreenInformation deviceScreenInformation, FontInfo fontInfo){
        this.deviceInfo = deviceScreenInformation;
        this.fontInfo = fontInfo;
        this.jFrame = jFrame;


    }


    public void buildScene(){
        createBackgroundPanel();
//        addStartGameButton();
        addCustomWindowCloseButton(jFrame);
        createGameTitleLabel();
        this.add(gameTitle);

//        this.add(startGameButton);
    }



    public void createBackgroundPanel(){
        this.setLayout(null);
        this.setPreferredSize(new Dimension(deviceInfo.screenWidth, deviceInfo.screenHeight));
        this.setBackground(Color.decode("#14171C"));
        this.setForeground(Color.decode("#C64C1D"));
    }

    public void addStartGameButton(){
//        startGameButton = new StartGameButton(jdeviceInfo,fontInfo);
//        this.add(startGameButton);
    }
    public void createGameTitleLabel(){
        gameTitle = new JLabel();
        gameTitle.setBounds(0,deviceInfo.screenHeight/2,deviceInfo.screenWidth,100);
        gameTitle.setFont(fontInfo.getResizedFont(100f));
        gameTitle.setText("LOST TREASURES");
        gameTitle.setHorizontalAlignment(JLabel.CENTER);
        gameTitle.setForeground(Color.white);
        gameTitle.setBackground(new Color(0,0,0,0));
    }
    public void addCustomWindowCloseButton(JFrame jFrame){
        closeButton = new CloseButton(deviceInfo,"X",jFrame, fontInfo);
        this.add(closeButton);
    }

    @Override
    public void run() {
        buildScene();
    }
}

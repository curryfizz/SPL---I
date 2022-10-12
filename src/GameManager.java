package src;

import javax.swing.*;

public class GameManager {


    public static void main(String[] args) throws InterruptedException{
        new GameManager();
    }

    public GameManager(){
//        GameTimer gameTimer = new GameTimer(ui);

        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setUndecorated(true);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.setVisible(true);
        StartMenuScreen startMenu = new StartMenuScreen(jFrame, new DeviceScreenInformation(), new FontInfo());
//        DormRoom dormRoomScene = new DormRoom(jFrame, new DeviceScreenInformation(), new FontInfo());
        jFrame.pack();
        jFrame.repaint();
    }
}

package src;

import javax.swing.*;

public class GameManager {

//    UI ui = new UI(this);
    DecoyAnimation animationTest;
    public static void main(String[] args) throws InterruptedException{
        new GameManager();
    }

    public GameManager(){
//        GameTimer gameTimer = new GameTimer(ui);

        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        jFrame.setBackground(Color.decode("#14171C"));
        jFrame.setUndecorated(true);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        DecoyAnimation decoy = new DecoyAnimation(jFrame,7);
//        jFrame.add(animationTest);
//        StartMenu startMenu = new StartMenu(jFrame);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}

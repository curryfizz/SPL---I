package src;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class GameManager {

//    UI ui = new UI(this);
    AnimationTest animationTest;
    public static void main(String[] args) throws InterruptedException{
        new GameManager();

    }

    public GameManager(){
//        GameTimer gameTimer = new GameTimer(ui);
//        animationTest = new AnimationTest(10);

        JFrame jFrame = new JFrame();
        jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        jFrame.setBackground(Color.decode("#14171C"));
        jFrame.setUndecorated(true);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        jFrame.add(animationTest);
        StartMenu startMenu = new StartMenu(jFrame);
//        jFrame.add
//        jFrame.add(startMenu.closeButton);
        jFrame.pack();
        jFrame.setVisible(true);

    }
}

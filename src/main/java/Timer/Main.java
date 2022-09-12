package Timer;

import javax.swing.JFrame;

public class Main {


    JFrame window;


    public static void main(String[] args) {

        new Main();
    }

    public Main() {

        window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null); //makes default layout to be null
        window.setVisible(true);

        GameTimer gameTimer = new GameTimer(window);

    }


}

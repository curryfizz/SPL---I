package src;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class GameManager {

    UI ui = new UI(this);
    public static void main(String[] args) throws InterruptedException{
        new GameManager();
    }

    public GameManager(){
        GameTimer gameTimer = new GameTimer(ui);
    }
}

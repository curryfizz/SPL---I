package src;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class GameManager {
    UI ui = new UI(this);
    public static void main(String[] args) throws InterruptedException{
        new GameManager();

        while(true == true)
        {
            TimeUnit.SECONDS.sleep(1/2);
            double mouseX = MouseInfo.getPointerInfo().getLocation().getX();
            double mouseY = MouseInfo.getPointerInfo().getLocation().getY();
            System.out.println("X:" + mouseX);
            System.out.println("Y:" + mouseY);
            //make sure to import
        }
    }

    public GameManager(){

    }
}

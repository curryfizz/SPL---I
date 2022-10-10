package src;

import java.awt.*;
import java.io.File;

public class DeviceScreenInformation {

    GraphicsEnvironment graphicsEnvironment;
    Integer screenHeight;
    Integer screenWidth;
    Font eastSeaDokdo;

    DeviceScreenInformation(){
        graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        screenWidth = graphicsEnvironment.getMaximumWindowBounds().width;
        screenHeight= graphicsEnvironment.getMaximumWindowBounds().height;
        importFont();
    }

    private void importFont(){
        try {
            eastSeaDokdo = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/EastSeaDokdo-Regular.ttf"));
            eastSeaDokdo = eastSeaDokdo.deriveFont(100f);
            graphicsEnvironment.registerFont(eastSeaDokdo);
        }catch(Exception e){
            //filoIO errors
            //custom font will be set to Monospaced;
            eastSeaDokdo = new Font("Monospaced",Font.BOLD,17);
        }
    }


    public Font getResizedFont(Float size){
        Font eastSeaDokdoResized = eastSeaDokdo.deriveFont(size);
        return eastSeaDokdoResized;
    }

}

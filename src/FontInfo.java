package src;

import java.awt.*;
import java.io.File;

public class FontInfo {
//    GraphicsEnvironment graphicsEnvironment;
    static Font eastSeaDokdo;
    FontInfo(){
//        graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        importFont();
    }
    private void importFont(){
        try {
            eastSeaDokdo = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/EastSeaDokdo-Regular.ttf"));
            eastSeaDokdo = eastSeaDokdo.deriveFont(100f);
//            graphicsEnvironment.registerFont(eastSeaDokdo);
        }catch(Exception e){
            //filoIO errors
            //custom font will be set to Monospaced;
            eastSeaDokdo = new Font("Monospaced",Font.BOLD,17);
        }
    }


    public static Font getResizedFont(Float size){
        Font eastSeaDokdoResized = eastSeaDokdo.deriveFont(size);
        return eastSeaDokdoResized;
    }
}

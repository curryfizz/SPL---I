package src;

import java.awt.*;

public class DeviceInformation {


    static GraphicsEnvironment graphicsEnvironment;
    public static Integer screenHeight;
    public static Integer screenWidth;

    DeviceInformation(){
        graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
//        screenHeight = graphicsEnvironment.getMaximumWindowBounds().height;
//       screenWidth = graphicsEnvironment.getMaximumWindowBounds().width;

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        screenHeight = d.height;
        screenWidth = d.width;
    }

}

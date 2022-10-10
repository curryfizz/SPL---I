package src;

import java.awt.*;
import java.io.File;

public class DeviceScreenInformation {

    GraphicsEnvironment graphicsEnvironment;
    Integer screenHeight;
    Integer screenWidth;

    DeviceScreenInformation(){
        graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        screenWidth = graphicsEnvironment.getMaximumWindowBounds().width;
        screenHeight= graphicsEnvironment.getMaximumWindowBounds().height;

    }

}

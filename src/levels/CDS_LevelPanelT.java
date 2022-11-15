package src.levels;

import src.LabelListener;
import src.MusicPlayer;
import src.buttons.CloseButton;
import src.buttons.LevelCloseButton;
import src.buttons.ObjectHidingButton;
import src.events.SceneObjectEvents;
import src.levelObjects.ScoreBoard;
import src.levelObjects.TimerLabel;
import src.popups.LevelFinishDialog;
import src.setup.DeviceInformation;
import src.setup.FontInfo;
import src.setup.RandomGenerator;
import src.transitionPanels.LoadingAnimationT;
import src.transitionPanels.MapT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class CDS_LevelPanelT extends ALevelPanel implements Runnable{


    public CDS_LevelPanelT(JFrame jFrame){
        super(jFrame);
    }

    @Override
    public int getLevelNumber() {
        return 3;
    }
    @Override
    public String getBackgroundPath() {
        return "images/Classroom images/classroomMain.PNG";
    }

    @Override
    public String getBackgroundMusicPath() {
        return "SoundAndMusic/BackgroundMusic/library.wav";
    }

    @Override
    public String getMessMessage(){
        return "<html>CDS is a busy place to find things as always.<br/> Can i find my things in CDS ??  (Tap to Search)</html>";
    }







    public  void generateScreenWithAllObjectsAndButtons() throws IOException {

        createButton("images/Classroom images/classroom objects/item 0.PNG", DeviceInformation.screenWidth *0/1920, DeviceInformation.screenHeight *435/1080,
                DeviceInformation.screenWidth *25/1920, DeviceInformation.screenHeight *50/1080);
        createText("<html>Brown Coffee<br/> cup </html>");
        createButton("images/Classroom images/classroom objects/item 1.PNG",DeviceInformation.screenWidth *1837/1920, DeviceInformation.screenHeight *640/1080,
                DeviceInformation.screenWidth *80/1920, DeviceInformation.screenHeight *20/1080);
        createText("Pencil");
        createButton("images/Classroom images/classroom objects/item 2.PNG",DeviceInformation.screenWidth *1782/1920, DeviceInformation.screenHeight *658/1080,
                DeviceInformation.screenWidth *110/1920, DeviceInformation.screenHeight *35/1080);
        createText("Chips Packet");
        createButton("images/Classroom images/classroom objects/item 3.PNG",DeviceInformation.screenWidth *1702/1920, DeviceInformation.screenHeight *640/1080,
                DeviceInformation.screenWidth *60/1920, DeviceInformation.screenHeight *30/1080);
        createText("Eraser");
        createButton("images/Classroom images/classroom objects/item 4.PNG",DeviceInformation.screenWidth *1554/1920, DeviceInformation.screenHeight *535/1080,
                DeviceInformation.screenWidth *40/1920, DeviceInformation.screenHeight *50/1080);
        createText("Pokemon Ball");
        createButton("images/Classroom images/classroom objects/item 5.PNG",DeviceInformation.screenWidth *1703/1920, DeviceInformation.screenHeight *528/1080,
                DeviceInformation.screenWidth *50/1920, DeviceInformation.screenHeight *50/1080);
        createText("Orange");
        createButton("images/Classroom images/classroom objects/item 6.PNG",DeviceInformation.screenWidth *1850/1920, DeviceInformation.screenHeight *375/1080,
                DeviceInformation.screenWidth *40/1920, DeviceInformation.screenHeight *120/1080);
        createText("Lamp");
        createButton("images/Classroom images/classroom objects/item 7.PNG", DeviceInformation.screenWidth *1871/1920, DeviceInformation.screenHeight *476/1080,
                DeviceInformation.screenWidth *60/1920, DeviceInformation.screenHeight *40/1080);
        createText("Tissue Box");
        createButton("images/Classroom images/classroom objects/item 8.PNG", DeviceInformation.screenWidth *1683/1920, DeviceInformation.screenHeight *402/1080,
                DeviceInformation.screenWidth *40/1920, DeviceInformation.screenHeight *60/1080);
        createText("Milk Bottle");
        createButton("images/Classroom images/classroom objects/item 9.PNG", DeviceInformation.screenWidth *1481/1920, DeviceInformation.screenHeight *427/1080,
                DeviceInformation.screenWidth *40/1920, DeviceInformation.screenHeight *50/1080);
        createText("Brown Coffee cup");
        createButton("images/Classroom images/classroom objects/item 10.PNG", DeviceInformation.screenWidth *1523/1920, DeviceInformation.screenHeight *340/1080,
                DeviceInformation.screenWidth *30/1920, DeviceInformation.screenHeight *70/1080);
        createText("Water Bottle");




        this.add(backgroundLabel);
    }






}


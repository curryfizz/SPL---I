package src.levels;

import src.GameManager;
import src.setup.DeviceInformation;

import javax.swing.*;
import java.io.IOException;


public  class CDS_LevelPanelT extends ALevelPanel {


    public CDS_LevelPanelT(JFrame jFrame) {
        super(jFrame);
    }

    @Override
    public int getLevelNumber() {
        return 5;
    }


    @Override
    public String getBackgroundPath() {
        return "images/Classroom images/classroomMain.PNG";
    }


    @Override
    public String getBackgroundMusicPath() {
        return "audio/background_music/libraryBackGroundAudio.wav";
    }


    @Override
    public String getMessMessage(){
        return "<html>Oh No, The Classroom is very difficult to find  my things.<br/> Guess I'll have to look for my present (Tap to Search)</html>";
    }

    @Override
    public void generateScreenWithAllObjectsAndButtons() throws IOException {
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
/*
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
        createButton("images/Classroom images/classroom objects/item 11.PNG",DeviceInformation.screenWidth *1465/1920, DeviceInformation.screenHeight *491/1080,
                DeviceInformation.screenWidth *40/1920, DeviceInformation.screenHeight *50/1080);
        createText("Red Mug");
        createButton("images/Classroom images/classroom objects/item 12.PNG", DeviceInformation.screenWidth *1172/1920, DeviceInformation.screenHeight *525/1080,
                DeviceInformation.screenWidth *100/1920, DeviceInformation.screenHeight *30/1080);
        createText("keyBoard");
        createButton("images/Classroom images/classroom objects/item 13.PNG", DeviceInformation.screenWidth *1314/1920, DeviceInformation.screenHeight *581/1080,
                DeviceInformation.screenWidth *80/1920, DeviceInformation.screenHeight *35/1080);
        createText("Kitkat");
        createButton("images/Classroom images/classroom objects/item 14.PNG", DeviceInformation.screenWidth *1199/1920, DeviceInformation.screenHeight *485/1080,
                DeviceInformation.screenWidth *70/1920, DeviceInformation.screenHeight *30/1080);
        createText("Diary");
        createButton("images/Classroom images/classroom objects/item 15.PNG",DeviceInformation.screenWidth *1308/1920, DeviceInformation.screenHeight *445/1080,
                DeviceInformation.screenWidth *50/1920, DeviceInformation.screenHeight *20/1080);
        createText("Banana");
        createButton("images/Classroom images/classroom objects/item 16.PNG", DeviceInformation.screenWidth *1319/1920, DeviceInformation.screenHeight *417/1080,
                DeviceInformation.screenWidth *30/1920, DeviceInformation.screenHeight *30/1080);
        createText("Among us");
        createButton("images/Classroom images/classroom objects/item 17.PNG", DeviceInformation.screenWidth *1172/1920, DeviceInformation.screenHeight *410/1080,
                DeviceInformation.screenWidth *40/1920, DeviceInformation.screenHeight *50/1080);
        createText("Paper Packet");
        createButton("images/Classroom images/classroom objects/item 18.PNG", DeviceInformation.screenWidth *1087/1920, DeviceInformation.screenHeight *399/1080,
                DeviceInformation.screenWidth *20/1920, DeviceInformation.screenHeight *60/1080);
        createText("Coca Cola bottle");
        createButton("images/Classroom images/classroom objects/item 19.PNG", DeviceInformation.screenWidth *1186/1920, DeviceInformation.screenHeight *204/1080,
                DeviceInformation.screenWidth *30/1920, DeviceInformation.screenHeight *190/1080);
        createText("Side Bag");
        createButton("images/Classroom images/classroom objects/item 20.PNG",DeviceInformation.screenWidth *1041/1920, DeviceInformation.screenHeight *237/1080,
                DeviceInformation.screenWidth *55/1920, DeviceInformation.screenHeight *60/1080);
        createText("Painting");


      */

    }



}

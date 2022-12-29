package src.levels;

import javax.swing.*;
import java.io.IOException;

public class FinalDormRoomSceneT extends ALevelPanel{


    public FinalDormRoomSceneT(JFrame jFrame){
        super(jFrame);
    }

    @Override
    public int getLevelNumber() {
        return 6;
    }

    @Override
    public synchronized void generateScreenWithAllObjectsAndButtons() throws IOException {

        createButton("images/dormImages/01.png", getX(308), getY(428), getX(68), getY(85));
        createText("Cornflakes Box");
        createButton("images/dormImages/02.png", getX(1188), getY(493), getX(18), getY(37));
        createText("CocaCola Can");
        createButton("images/dormImages/03.png", getX(1235), getY(400), getX(86), getY(65));
        createText("Shoulder Bag");
        createButton("images/dormImages/04.png", getX(1130), getY(730), getX(67), getY(26));
        createText("HeadPhone");
        createButton("images/dormImages/05.png", getX(325), getY(728), getX(43), getY(32));
        createText("Phone");
        createButton("images/dormImages/06.png", getX(419), getY(672), getX(60), getY(19));
        createText("Calculator");
        createButton("images/dormImages/07.png", getX(1102), getY(703), getX(70), getY(25));
        createText("Sunglasses");

        this.add(backgroundLabel);
    }

    @Override
    public String getBackgroundPath() {
        return "images/dormImages/LevelOneMain.png";
    }

    @Override
    public String getBackgroundMusicPath() {
        return "audio/background_music/dormBackgroundAudio.wav";
    }

    @Override
    public String getMessMessage(){
        return "<html>Oh No, The room looks like it got ransacked?! Where is my present?<br/> Guess I'll have to tidy up (Tap to Search)</html>";
    }
}



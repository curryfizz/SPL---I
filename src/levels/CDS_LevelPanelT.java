package src.levels;

import src.setup.DeviceInformation;

import javax.swing.*;
import java.io.IOException;


public  class CDS_LevelPanelT extends ALevelPanel {


    public CDS_LevelPanelT(JFrame jFrame){
        super(jFrame);
    }

    @Override
    public int getLevelNumber() {
        return 5;
    }

    @Override
    public synchronized void generateScreenWithAllObjectsAndButtons() throws IOException {

        createButton("images/cdsimages/bag.png", getX(308), getY(428), getX(68), getY(85));
        createText("Bag");
        createButton("images/cdsimages/book.png", getX(308), getY(428), getX(68), getY(85));
        createText("NoteBook");
        createButton("images/cdsimages/change.png", getX(308), getY(428), getX(68), getY(85));
        createText("Money");
        createButton("images/cdsimages/glasses.png", getX(308), getY(428), getX(68), getY(85));
        createText("Glasses");
        createButton("images/cdsimages/headphones.png", getX(308), getY(428), getX(68), getY(85));
        createText("Headphones");
        createButton("images/cdsimages/key.png", getX(308), getY(428), getX(68), getY(85));
        createText("KeyRing");
        createButton("images/cdsimages/mug.png", getX(308), getY(428), getX(68), getY(85));
        createText("Travel Mug");
        createButton("images/cdsimages/phone.png", getX(308), getY(428), getX(68), getY(85));
        createText("Phone");
        createButton("images/cdsimages/wallet.png", getX(308), getY(428), getX(68), getY(85));
        createText("Wallet");
        createButton("images/cdsimages/watch.png", getX(308), getY(428), getX(68), getY(85));
        createText("Watch");
        this.add(backgroundLabel);
    }

    @Override
    public String getBackgroundPath() {
        return "images/cdsimages/cds.png";
    }

    @Override
    public String getBackgroundMusicPath() {
        return "audio/background_music/dormBackgroundAudio.wav";
    }
    @Override
    public String getMessMessage(){
        return "<html>Goodness! The room is such a mess! Where is my present?<br/> Guess I'll have to tidy up.. (Tap to Search)</html>";
    }
}

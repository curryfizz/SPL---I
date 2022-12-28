package src.events;

import src.DatabaseConnection.PlayerInfo;
import src.buttons.MapLevelButton;
import src.levelObjects.Sound;
import src.levels.*;
import src.transitionPanels.MapT;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MapButtonEvents implements MouseListener {
    MapT mapT;
    int serial;
//    ALevelPanel SceneT;
    JLabel SidePanelText;
    JLabel DefaultText;
    JLabel padLock;
    JLabel CutOut;
    MapLevelButton motherButton;
    Sound errorSound;
    Sound clickSound;
    public MapButtonEvents(MapT mapT, MapLevelButton motherButton, int priority){
        this.serial = priority;
        this.motherButton = motherButton;
        this.mapT = mapT;
        this.SidePanelText = mapT.SidePanelTextList.get(serial+1);
        this.CutOut = mapT.CutOutList.get(serial);
        this.DefaultText = mapT.SidePanelTextList.get(0);
        this.padLock = mapT.PadLockList.get(serial);
        errorSound = new Sound();
        clickSound = new Sound();
        clickSound.setFile("audio/soundeffects/mixkit-mouse-click-close-1113.wav");
        errorSound.setFile("audio/soundeffects/mixkit-click-error-1110.wav");
    }

    public ALevelPanel getPanel(int serial){
        ALevelPanel Scene = null;
        ExecutorService es = Executors.newFixedThreadPool(1);

        if(serial == 0){
            Scene = new DormRoomSceneT(mapT.jFrame);
        } else if (serial == 1) {
            Scene = new ClassRoomSceneT(mapT.jFrame);
        } else if (serial == 2) {
            Scene = new LibrarySceneT(mapT.jFrame);
        } else if (serial == 3) {
            Scene = new CDS_LevelPanelT(mapT.jFrame);
        } else if (serial==4) {
            Scene = new FinalDormRoomSceneT(mapT.jFrame);
        }
        es.execute(Scene);
        es.shutdown();
        return Scene;
    }

    private void hideAllText(){
        for( JLabel SideText : mapT.SidePanelTextList){
            SideText.setVisible(false);
        }
    }

    private void hideAllCutOutsAndLock(){
        for(int i = 3; i > PlayerInfo.gameProgress-2 ; i--){
            mapT.CutOutList.get(i).setVisible(false);
        }
        for(int i = 3; i > PlayerInfo.gameProgress-2 ; i--){
            mapT.PadLockList.get(i).setVisible(false);
        }

    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(PlayerInfo.gameProgress > serial){
            clickSound.play();
            mapT.ArrowGif.setVisible(false);

            ALevelPanel SceneT = getPanel(serial);
            mapT.mapMusic.stop();

            mapT.jFrame.remove(mapT);
            mapT.loadingAnimationT.changeNextScene(SceneT);
            SceneT.PrepareForSceneTransition(mapT.loadingAnimationT, mapT);
            mapT.jFrame.add(mapT.loadingAnimationT);
            mapT.loadingAnimationT.initializeTimer();

            hideAllText();
            hideAllCutOutsAndLock();

            mapT.jFrame.revalidate();
            mapT.jFrame.repaint();
        }
        else{
            errorSound.play();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        CutOut.setVisible(true);

        hideAllText();

        if(PlayerInfo.gameProgress > serial) {
            motherButton.setBackground(mapT.hoveringActiveButtonColor);
            SidePanelText.setVisible(true);
        }else{
            motherButton.setBackground(mapT.hoveringInactiveButtonColor);
            DefaultText.setVisible(true);
            padLock.setVisible(true);
        }

        mapT.revalidate();
        mapT.repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(PlayerInfo.gameProgress-2 < serial) {
            CutOut.setVisible(false);
        }
        mapT.refreshButtonGrayness();
        DefaultText.setVisible(false);
        padLock.setVisible(false);
    }
}

package src.levels;

import src.levels.ALevelPanel;

import javax.swing.*;
import java.io.IOException;

public class CDS_LevelPanelT extends ALevelPanel {
    public CDS_LevelPanelT(JFrame jFrame) {
        super(jFrame);
    }

    @Override
    public int getLevelNumber() {
        return 0;
    }

    @Override
    public void generateScreenWithAllObjectsAndButtons() throws IOException {

    }

    @Override
    public String getBackgroundPath() {
        return null;
    }

    @Override
    public String getBackgroundMusicPath() {
        return null;
    }

    @Override
    public String getMessMessage() {
        return null;
    }
}

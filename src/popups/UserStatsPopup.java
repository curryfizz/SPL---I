package src.popups;

import src.DatabaseConnection.PlayerInfo;
import src.buttons.CustomPopupCloseButton;
import src.buttons.LevelCloseButton;
import src.levelObjects.Sound;
import src.levels.ALevelPanel;
import src.setup.FontInfo;
import src.transitionPanels.MapT;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

public class UserStatsPopup extends JDialog{
    JFrame jFrame;
    MapT backgoundPanel;
    JLabel textLabel;
    public Sound objClickSound;
    String AdvancedInfo;

    public UserStatsPopup(JFrame jFrame){
        this.jFrame = jFrame;

        setModal(true);
        setUndecorated(true);

        getContentPane().setBackground(Color.decode("#14171C"));
        setLayout(new FlowLayout());
        getRootPane().setBorder(new LineBorder(Color.white,2));
        textLabel = new JLabel();
        textLabel.setPreferredSize(new Dimension(400,500));
//        textLabel.setBackground(Color.blue);
        textLabel.setLayout(new FlowLayout());
        textLabel.setForeground(Color.white);

//        BasicInfo();
        AdvancedInfo();

        textLabel.setText(convertToMultiline(AdvancedInfo));
        objClickSound = new Sound();
        objClickSound.setFile("audio/soundeffects/mixkit-mouse-click-close-1113.wav");
        System.gc();

        textLabel.setFont(FontInfo.getResizedFont(28f));
        add(textLabel);
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        setSize(420, 520);
        setLocationRelativeTo(jFrame);
        setResizable(false);

        CustomPopupCloseButton exitButton = new CustomPopupCloseButton(this);

        setVisible(true);
    }


    private void AdvancedInfo() {

        String username = Objects.requireNonNullElse(PlayerInfo.username, "unavailable");
        String email = Objects.requireNonNullElse(PlayerInfo.email, "unavailable");

        AdvancedInfo = "User name: " + username + "\nemail: " + email + "\n";

        AdvancedInfo += "Game Progress: ";
        switch (PlayerInfo.gameProgress) {
            case 0 ->{
                AdvancedInfo += "Just Started";
            }
            case 1 -> {
                AdvancedInfo += "Completed Introduction";
            }
            case 2 -> {
                AdvancedInfo += "Completed Dorm Room";
            }
            case 3 -> {
                AdvancedInfo += "Completed up to Classroom";
            }
            case 4 -> {
                AdvancedInfo += "Completed up to Library";
            }
            case 5 ->{
                AdvancedInfo += "Finished the game.";
            }
        }
        if(PlayerInfo.gameProgress > 1)
            AdvancedInfo += "\n\nDormRoom Highest Score: " + PlayerInfo.DormHighScore+ "\nDormRoom Least Time taken: " + PlayerInfo.DormLeastTime;
        if(PlayerInfo.gameProgress > 2)
            AdvancedInfo += "\n\nClassroom Highest Score: " + PlayerInfo.ClassroomHighScore + "\nClassroom Least Time taken: " + PlayerInfo.ClassroomLeastTime;
        if(PlayerInfo.gameProgress > 3)
            AdvancedInfo += "\n\nLibrary Highest Score: " + PlayerInfo.LibraryHighScore + "\nLibrary Least Time taken: " + PlayerInfo.LibraryLeastTime;
        if(PlayerInfo.gameProgress > 4)
            AdvancedInfo += "\n\nDormRoom Version2 Highest Score: " + PlayerInfo.DormV2HighScore + "\nDormRoom Version2 Least Time taken: " + PlayerInfo.DormV2LeastTime;



    }

    public static String convertToMultiline(String orig)
    {
        return "<html>" + orig.replaceAll("\n", "<br>") + "</html>";
    }

}

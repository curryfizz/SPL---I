package src.transitionPanels;

import src.buttons.CloseButton;
import src.buttons.StartGameButton;
import src.levelObjects.Sound;
import src.setup.DeviceInformation;
import src.setup.FontInfo;
import src.transitionPanels.LoadingAnimationT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StartMenuScreenT extends JPanel implements Runnable{
    JFrame jFrame;
    JLabel gameTitle;
    Sound bgMusic;
    Sound clickSound;
    public StartGameButton startGameButton;
    CloseButton closeButton;
    LoadingAnimationT loadingAnimationT;
    JPanel nextScene;

    public StartMenuScreenT(JFrame jFrame){
//        this.deviceInfo = deviceInformation;
//        this.fontInfo = fontInfo;
        this.jFrame = jFrame;
        this.startGameButton = new StartGameButton(this);
        this.add(startGameButton);

        bgMusic = new Sound();
//        bgMusic.setFile("audio/background_music/Ibrahim - Bullet Train Fantasy(for StartMenu).wav");
//        bgMusic.loop();
        clickSound = new Sound();
        clickSound.setFile("audio/soundeffects/mixkit-mouse-click-close-1113.wav");
    }

    public void PrepareForSceneTransition(LoadingAnimationT loadingAnimationT, JPanel nextScene) {
        this.loadingAnimationT = loadingAnimationT;
        this.nextScene = nextScene;
    }


    public void buildScene(){


        createBackgroundPanel();
        addCustomWindowCloseButton(jFrame);
        createGameTitleLabel();
        this.add(gameTitle);
        doButtonThings();

    }

    private void doButtonThings() {
        startGameButton.addMouseListener(new MouseListener() {
            boolean isHovering = false;
            @Override
            public void mouseClicked(MouseEvent e) {
                clickSound.play();

                jFrame.remove(startGameButton.startMenu);
                jFrame.add(loadingAnimationT);

                bgMusic.stop();

                loadingAnimationT.initializeTimer();

                jFrame.revalidate();
                jFrame.repaint();

            }

            @Override
            public void mousePressed(MouseEvent e) {
                startGameButton.setBackground(new Color(100,70,120));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                startGameButton.setBackground(null);
                if(isHovering) {
                    clickSound.play();

                    jFrame.remove(startGameButton.startMenu);
                    jFrame.add(loadingAnimationT);
                    loadingAnimationT.initializeTimer();

//                    bgMusic.stop();

                    jFrame.revalidate();
                    jFrame.repaint();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                isHovering = true;
                startGameButton.setBackground(new Color(0,40,80));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovering = false;
                startGameButton.setBackground(null);
            }
        });
    }


    public void createBackgroundPanel(){
        this.setLayout(null);
        this.setPreferredSize(new Dimension(DeviceInformation.screenWidth, DeviceInformation.screenHeight));
        this.setBackground(Color.decode("#14171C"));
        this.setForeground(Color.decode("#C64C1D"));
    }

    public void addStartGameButton(){
//        startGameButton = new StartGameButton(jdeviceInfo,fontInfo);
//        this.add(startGameButton);
    }
    public void createGameTitleLabel(){
        gameTitle = new JLabel();
        gameTitle.setBounds(0, DeviceInformation.screenHeight /2, DeviceInformation.screenWidth,DeviceInformation.screenHeight/6);
        gameTitle.setFont(FontInfo.getResizedFont((float)0.09*(DeviceInformation.screenWidth)));
        gameTitle.setText("LOST TREASURES");
        gameTitle.setHorizontalAlignment(JLabel.CENTER);
        gameTitle.setForeground(Color.white);
        gameTitle.setBackground(new Color(0,0,0,0));
    }
    public void addCustomWindowCloseButton(JFrame jFrame){
        closeButton = new CloseButton("X",jFrame);
        this.add(closeButton);
    }

    @Override
    public void run() {
        buildScene();
    }
}

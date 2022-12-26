package src.transitionPanels;

import src.buttons.CloseButton;
import src.buttons.StartGameButton;
import src.levelObjects.Sound;
import src.levelObjects.StartMenuBackGroundLabels;
import src.levelObjects.StartMenuLabel;
import src.levelObjects.TimerLabel;
import src.setup.DeviceInformation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static java.lang.Thread.sleep;

public class StartMenuScreenT extends JPanel implements Runnable{
    JFrame jFrame;
    JLabel backGroundImageLabel;

    Image gameIcon = new ImageIcon("images/StartMenu/startmenuicon.png").getImage();
    JLabel gameIconBackgroundLabel;
    Sound bgMusic;
    Sound clickSound;
    public StartGameButton startGameButton;
    CloseButton closeButton;
    LoadingAnimationT loadingAnimationT;
    JPanel nextScene;

    public StartMenuScreenT(JFrame jFrame){

        this.jFrame = jFrame;
//        this.startGameButton = new StartGameButton(this);
//        this.add(startGameButton);

        bgMusic = new Sound();
        clickSound = new Sound();
        clickSound.setFile("audio/soundeffects/mixkit-mouse-click-close-1113.wav");
    }

    public void PrepareForSceneTransition(LoadingAnimationT loadingAnimationT, JPanel nextScene) {
        this.loadingAnimationT = loadingAnimationT;
        this.nextScene = nextScene;
    }

    private void addBackGroundImageLabel(){
        backGroundImageLabel = createBackgroundLabel("images/StartMenu/startmenubackground.png");
        add(backGroundImageLabel);
    }
    private void addGameIconBackgroundLabel(){
        gameIconBackgroundLabel = createBackgroundLabel("images/StartMenu/startmenuicon.png");
        gameIconBackgroundLabel.setIcon(null);
        add(gameIconBackgroundLabel);
    }

    private JLabel createBackgroundLabel(String path){
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0,0,DeviceInformation.screenWidth, DeviceInformation.screenHeight);
        ImageIcon backGroundImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(path)));
        backGroundImage.setImage(backGroundImage.getImage().getScaledInstance(DeviceInformation.screenWidth, DeviceInformation.screenHeight, Image.SCALE_SMOOTH));
        backgroundLabel.setIcon(backGroundImage);
        return backgroundLabel;
    }


    public void buildScene(){
        createBackgroundPanel();
        StartMenuBackGroundLabels gameIcon = new StartMenuBackGroundLabels("images/StartMenu/startmenuicon.png");
        StartMenuBackGroundLabels innerMostCircle = new StartMenuBackGroundLabels("images/StartMenu/innermostcircle.png");
        add(gameIcon);
        add(innerMostCircle);
        gameIcon.startAnimation(100);
        innerMostCircle.timer.setInitialDelay(2000);
        innerMostCircle.startAnimation(10);
//        StartMenuBackGroundLabels secondInnerMostCircle = new StartMenuBackGroundLabels("images/StartMenu/startmenuicon.png");
//        add(secondInnerMostCircle);
//        gameIcon.startAnimation(80);
//        StartMenuBackGroundLabels thirdInnerMostCircle = new StartMenuBackGroundLabels("images/StartMenu/startmenuicon.png");
//        add(thirdInnerMostCircle);
//        gameIcon.startAnimation(80);
//        StartMenuBackGroundLabels fourthInnerMostCircle = new StartMenuBackGroundLabels("images/StartMenu/startmenuicon.png");
//        add(fourthInnerMostCircle);
//        gameIcon.startAnimation(80);
//        StartMenuBackGroundLabels fifthInnerMostCircle = new StartMenuBackGroundLabels("images/StartMenu/startmenuicon.png");
//        add(fifthInnerMostCircle);
//        gameIcon.startAnimation(80);
//        StartMenuBackGroundLabels outerMostCircle = new StartMenuBackGroundLabels("images/StartMenu/startmenuicon.png");
//        add(outerMostCircle);
//        gameIcon.startAnimation(80);


//        addCustomWindowCloseButton(jFrame);
//        addGameTitleLabel();
//        addGameIconBackgroundLabel();

//        addBackGroundImageLabel();
//        doButtonThings();

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
    }

    public void addGameTitleLabel(){
        StartMenuLabel gameTitle = new StartMenuLabel(DeviceInformation.screenWidth/2, DeviceInformation.screenHeight/5);
        gameTitle = new StartMenuLabel();
        gameTitle.setBounds(DeviceInformation.screenWidth*2/5,DeviceInformation.screenHeight/6, DeviceInformation.screenWidth/2, DeviceInformation.screenHeight/6);
        add(gameTitle);

    }
    public void addCustomWindowCloseButton(JFrame jFrame){
        closeButton = new CloseButton("X",jFrame);
        this.add(closeButton);
    }

    @Override
    public void run() {
        buildScene();
    }

    private void addGameIconAnimation(){
        Timer timer = new Timer(0, new ActionListener() {
            float alpha = 0.0F;
            @Override
            public void actionPerformed(ActionEvent e) {
                alpha+=0.1f;
                if(alpha==1.0f){
                    return;
                }

                try {
                    animateGameIcon(alpha);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        timer.setDelay(1000);
        timer.start();
    }

    private void animateGameIcon(float alpha) throws IOException {
        Graphics2D g2d = (Graphics2D)gameIconBackgroundLabel.getGraphics();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_IN, alpha));
        g2d.drawImage(gameIcon,0,0,null );

    }
}

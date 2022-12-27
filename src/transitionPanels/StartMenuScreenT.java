package src.transitionPanels;

import src.buttons.BasicBlueButton;
import src.buttons.CloseButton;
import src.buttons.StartGameButton;
import src.buttons.StartScreenButtons;
import src.events.LoginButtonEvent;
import src.events.SignUpButtonEvent;
import src.levelObjects.Sound;
import src.levelObjects.StartMenuBackGroundLabels;
import src.levelObjects.StartMenuLabel;
import src.levelObjects.TimerLabel;
import src.setup.DeviceInformation;
import src.setup.FontInfo;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.TableHeaderUI;
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

    JLabel gameIconBackgroundLabel;
    Sound bgMusic;
    Sound clickSound;
    public StartGameButton startGameButton;
    CloseButton closeButton;
    LoadingAnimationT loadingAnimationT;
    JPanel nextScene;

    StartScreenButtons loginButton;
    StartScreenButtons signupButton;
    StartScreenButtons quitButton;
    StartMenuBackGroundLabels gameIcon;
    public StartMenuScreenT(JFrame jFrame){

        this.jFrame = jFrame;
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
        addLoginButton();
        addSignupButton();
        addQuitButton();
        addGameTitleLabel();
        addGameIconAnimation();
        addBackGroundImageLabel();
        SwingUtilities.invokeLater(() -> gameIcon.startAnimation(5));

//        addCustomWindowCloseButton(jFrame);
//        addGameIconBackgroundLabel();

//        doButtonThings();

    }

    public void addLoginButton(){
        loginButton = new StartScreenButtons(DeviceInformation.screenWidth/4, 70, "Login");
        loginButton.setBounds(0, DeviceInformation.screenHeight/3, DeviceInformation.screenWidth/5, 70);
        loginButton.addMouseListener(new LoginButtonEvent(jFrame));
        add(loginButton);
    }

    public void addSignupButton(){
        signupButton = new StartScreenButtons(DeviceInformation.screenWidth/4, 70, "Signup");
        signupButton.setBounds(0, DeviceInformation.screenHeight/2, DeviceInformation.screenWidth/5, 70);
        signupButton.addMouseListener(new SignUpButtonEvent(jFrame));
        add(signupButton);
    }

    public void addQuitButton(){
        quitButton = new StartScreenButtons(DeviceInformation.screenWidth/4, 70, "Quit");
        quitButton.setBounds(0, DeviceInformation.screenHeight*2/3, DeviceInformation.screenWidth/5, 70);
        add(quitButton);
        addQuitButtonMouseEvents();
    }



    private void createBackgroundPanel(){
        this.setLayout(null);
        this.setPreferredSize(new Dimension(DeviceInformation.screenWidth, DeviceInformation.screenHeight));
        this.setBackground(Color.decode("#14171C"));
    }

    private void addGameTitleLabel(){
        StartMenuLabel gameTitle = new StartMenuLabel();
        gameTitle.setBounds(DeviceInformation.screenWidth*2/5,DeviceInformation.screenHeight/6, DeviceInformation.screenWidth/2, DeviceInformation.screenHeight/6);
        add(gameTitle);

    }

    @Override
    public void run() {
        buildScene();
    }

    private void addGameIconAnimation(){
        gameIcon = new StartMenuBackGroundLabels("images/StartMenu/startmenuicon.png");
        add(gameIcon);
    }

    private void addQuitButtonMouseEvents(){
        quitButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

}

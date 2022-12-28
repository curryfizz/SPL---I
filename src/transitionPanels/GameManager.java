package src.transitionPanels;

import src.DatabaseConnection.OracleDatabase;
import src.popups.LoginDialog;
import src.popups.SignupDialog;
import src.setup.DeviceInformation;
import src.setup.FontInfo;
import src.DatabaseConnection.PlayerInfo;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameManager{

    public String testString;

    public static void main(String[] args) throws InterruptedException, UnsupportedAudioFileException, LineUnavailableException, IOException {
        new GameManager();
//        OracleDatabase oracle = new OracleDatabase();
//        try {
//            test(oracle);
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }

//        test();
    }

    public static void test(){
        OracleDatabase oracleDatabase = new OracleDatabase();
        System.setProperty("sun.java2d.uiScale", "1.0");
        FontInfo fontInfo = new FontInfo();
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setBounds(300, 100, 600, 600);
//        LoginDialog loginDialog = new LoginDialog(jFrame,oracleDatabase);
        SignupDialog signupDialog = new SignupDialog(jFrame, oracleDatabase);
        jFrame.setVisible(true);
//        try {

//            DeviceInformation deviceInformation = new DeviceInformation();
//            System.out.println(deviceInformation.screenHeight + " " + deviceInformation.screenWidth);
//            /* Set up the frame*/
//            JFrame jFrame = new JFrame();
//            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            jFrame.setBounds(300, 100, 600, 600);
//            JPanel jPanel = new JPanel();
//            jPanel.setBackground(new Color(220, 90, 70, 255));
//            jPanel.setPreferredSize(new Dimension(500, 500));
//            JButton signup = new JButton("Signup");
//            JTextField jTextField = new JTextField();
//            jTextField.setPreferredSize(new Dimension(100, 60));
//            JTextField jTextField2 = new JTextField();
//            jTextField2.setPreferredSize(new Dimension(100, 60));
//
//            JTextField getUser = new JTextField();
//            getUser.setPreferredSize(new Dimension(200,50));
//            JTextPane showDeets =  new JTextPane();
//            showDeets.setPreferredSize(new Dimension(300,80));
//            JButton login = new JButton("login");
//            signup.addMouseListener(new MouseListener() {
//                @Override
//                public void mouseClicked(MouseEvent e) {
//                    SignupDialog signupDialog = new SignupDialog(jFrame, oracleDatabase);
//                    if(signup.isEnabled()) {
//                        jTextField2.setEnabled(false);
//                        jTextField.setEnabled(false);
//                        try {
//                            if(oracleDatabase.insertUser(jTextField.getText(), jTextField2.getText()) == false){
//                                System.out.println("User already exists!");
//                            }
//
//                        } catch (SQLException ex) {
//                            ex.printStackTrace();
//                        }
//                        signup.setEnabled(false);
//                    }
//                }
//
//                @Override
//                public void mousePressed(MouseEvent e) {
//
//                }
//
//                @Override
//                public void mouseReleased(MouseEvent e) {
//
//                }
//
//                @Override
//                public void mouseEntered(MouseEvent e) {
//
//                }
//
//                @Override
//                public void mouseExited(MouseEvent e) {
//
//                }
//            });
//
//            login.addMouseListener(new MouseListener() {
//                @Override
//                public void mouseClicked(MouseEvent e) {
//                    if(login.isEnabled()) {
//                        try {
//                            if (oracleDatabase.retrieveUserInfo(getUser.getText())) {
//                                showDeets.setText(PlayerInfo.getString());
//                            } else {
//                                showDeets.setForeground(Color.red);
//                                showDeets.setText("User doesnt exist!");
//                            }
//
//                        } catch (SQLException ex) {
//
//                            ex.printStackTrace();
//                        } finally {
//
//                            login.setEnabled(false);
//                        }
//                    }
//                }
//
//                @Override
//                public void mousePressed(MouseEvent e) {
//
//                }
//
//                @Override
//                public void mouseReleased(MouseEvent e) {
//
//                }
//
//                @Override
//                public void mouseEntered(MouseEvent e) {
//
//                }
//
//                @Override
//                public void mouseExited(MouseEvent e) {
//
//                }
//            });
//            jPanel.add(jTextField2);
//            jPanel.add(jTextField);
//            jPanel.add(signup);
//            jPanel.add(getUser);
//            jPanel.add(login);
//            jPanel.add(showDeets);
//            jFrame.add(jPanel);
//            jFrame.pack();
//            jFrame.setVisible(true);
//            System.out.println(PlayerInfo.getString());
////        testAnimation testAnimation = new testAnimation();
////        jFrame.add(testAnimation);
////        testAnimation.initializeTimer();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }
    public GameManager() throws IOException {
        System.setProperty("sun.java2d.uiScale", "1.0");
        DeviceInformation deviceInformation = new DeviceInformation();
        FontInfo fontInfo = new FontInfo();
        PlayerInfo player = new PlayerInfo(1); // taken from database

        System.out.println(deviceInformation.screenHeight + " " + deviceInformation.screenWidth);

        /* Set up the frame*/
        JFrame jFrame = new JFrame();
        jFrame.setBackground(Color.black);
        jFrame.setForeground(Color.black);
        jFrame.setTitle("Lost Treasures");
        jFrame.setIconImage(new ImageIcon("images/icons/logo2.png").getImage());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setUndecorated(true);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.setBackground(Color.decode("#14171C"));
        jFrame.pack();
        jFrame.setVisible(true);


        //thread pool
        ExecutorService pl = Executors.newFixedThreadPool(1);

        StartMenuScreenT startMenu = new StartMenuScreenT(jFrame);

        MessageFromMomT messageFromMomT = new MessageFromMomT(jFrame);
        LoadingAnimationT loadingAnimationT = new LoadingAnimationT(jFrame,3,messageFromMomT);
        MapT mapT = new MapT(jFrame, loadingAnimationT);


        startMenu.PrepareForSceneTransition(loadingAnimationT, null); //nextscene is not being used
                                                                                //perhaps later we can use player info to change next scene
                                                                                //for loadingscene
        messageFromMomT.PrepareForSceneTransition(loadingAnimationT, mapT);

        pl.execute(startMenu);
        pl.execute(mapT);
        pl.execute(loadingAnimationT);
        pl.execute(messageFromMomT);

        jFrame.add(startMenu); //should be startmenu during real play

        pl.shutdown();

    }

    /*TODO:
    - add small icon in map to go to player stats;
    - make it another panel
    */
    /*TODO:
    - why does score Animation keep disappearing so quickly;
    - tie all the music and sound effects together, or make them seperate, people may find it too loud;
    - add sound effects for other button clicks
    //- add music for start menu and map
    - make sure map level buttons don't work unless gameProgress is satisfactory;
    - Just completing the level shouldn't be counted as completing, there should be a threshold score to achieve eg:500 points
    - change forest music for classroom
    - try re-rendering dormscene if there's time, it looks bad compared to it's fancy classroom and fancy library brothers
    - add transcucent background image for text Box in AlevelPanels, black box looks kinda bad;
    - add background image for timer label and score label;
    - the buttons in mapT looks kinda bad, I vote changing to transparent buttons with  text on them;
    - add arrow gif pointing at the next level in map?
    - time over, do you want to exit to map, all these confirmation dialogue's text is too small. should be larger and should be scaled
    - message from momT does not give 2 sounds if clicked too quickly, make 2 sound objs so that it does.
    - managed to?????? ayesha bhule gese
    */
}

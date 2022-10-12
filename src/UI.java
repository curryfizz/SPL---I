package src;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class UI {
    GameManager gm;
    JFrame window;
    GraphicsEnvironment ge;
    Rectangle maxBounds;
    public JPanel bgPanel = new JPanel();
    Font eastSeaDokdo ;
    public JLabel bgLabel = new JLabel();

    JButton button;
    StartMenuScreen startMenu;

    GraphicsDevice gd;
    public UI(GameManager gm){
        this.gm = gm;
        //hierarchy is important :3
        getScreenInformation();
        gd = ge.getDefaultScreenDevice();
        importFont();
        createMainField();
//        startMenu = new StartMenu(this);
//        window.add(startMenu.startMenuPanel);
//        createStartButton();
        createMenuBackground();
//        createMenuButtons();
        window.setVisible(true);
        gd.setFullScreenWindow(window);
    }

    public void createStartButton(){
        button = new JButton();
        button.setBounds(500,500,200,50);
        button.setText("Start korr");
//        startMenu.startMenuPanel.add(button);
//        button.addMouseListener(new MouseListener() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                window.remove(startMenu.startMenuPanel);
//                window.repaint();
//                createMenuBackground();
//                createMenuButtons();
////                window.revalidate();
//            }
//
//            @Override
//            public void mousePressed(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseEntered(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//
//            }
//        });

    }

    private void getScreenInformation(){
        //For accessing screen details - height and width
        ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        //The rectangle available for design
        maxBounds = ge.getMaximumWindowBounds();
    }

    private void importFont(){
        //registering custom font
        try {
            eastSeaDokdo = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/EastSeaDokdo-Regular.ttf"));
            eastSeaDokdo = eastSeaDokdo.deriveFont(29f);
            ge.registerFont(eastSeaDokdo);
        }catch(Exception e){
            //filoIO errors
            //custom font will be set to Monospaced;
            eastSeaDokdo = new Font("Monospaced",Font.BOLD,17);
        }


    }

    public ImageIcon menuImages(String imageLocation){
        ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource(imageLocation));
        Image image = imageIcon.getImage();
        image = image.getScaledInstance(maxBounds.width, maxBounds.height, Image.SCALE_DEFAULT);
        imageIcon = new ImageIcon(image);
        return imageIcon;
    }

    public ImageIcon getDefaultImage(){
        ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource("images/Level images/LevelbackgroundColored.png"));
        Image image = imageIcon.getImage();
        image = image.getScaledInstance(maxBounds.width, maxBounds.height, Image.SCALE_DEFAULT);
        imageIcon = new ImageIcon(image);
        return imageIcon;
    }


    public void createMainField() {
        window = new JFrame();
        window.setSize(maxBounds.width, maxBounds.height);
//        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.GRAY);
        window.setResizable(false);
        window.setLayout(null);
    }
    public  void createMenuBackground(){
        bgPanel = new JPanel();
        bgPanel.setBounds(0,0,maxBounds.width, maxBounds.height);
        bgPanel.setBackground(new Color(0,0,0,0));
        bgPanel.setLayout(null);
        window.add(bgPanel);

        bgLabel = new JLabel();
        bgLabel.setBounds(0,0, maxBounds.width,maxBounds.height);

        bgLabel.setIcon(getDefaultImage());

    }

    public JLabel createCustomLabel(ImageIcon image){
        JLabel label = new JLabel();
        label.setBounds(0,0,maxBounds.width,maxBounds.height);
        label.setIcon(image);

        return label;
    }

    public JButton createLocationButton(String locationName, int posx, int posy, String imageLocation, JLabel label, JPanel panel){
        panel.add(createCustomLabel(menuImages(imageLocation)));
        JButton locationButton = new JButton(locationName);
        locationButton.setBackground(Color.decode("#14171C"));
        locationButton.setForeground(Color.white);
        locationButton.setFont(eastSeaDokdo);
        locationButton.setBounds(posx,posy,200,36);
        locationButton.setFocusPainted(false);
        locationButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.decode("#14171C"),3), BorderFactory.createLineBorder(Color.decode("#55a38b"),2)));
        ImageIcon outOfFocus = menuImages("images/Level images/Levelbackground.png");
        locationButton.addMouseListener(new MenuMouseEvents(label,getDefaultImage(), outOfFocus));
        return locationButton;
    }
    public void createMenuButtons(){
        bgPanel.add(createLocationButton("Academic Building",450,50, "images/Level images/AcademicBuildingCutOut.png", bgLabel,bgPanel));
        bgPanel.add(createLocationButton("Dormitory",699,145,"images/Level images/DormCutOut.png", bgLabel,bgPanel));
        bgPanel.add(createLocationButton("Library",580,475,"images/Level images/LibraryCutOut.png",bgLabel,bgPanel));
        bgPanel.add(createLocationButton("CDS",979,300,"images/Level images/CDSCutOut.png", bgLabel,bgPanel));
        bgPanel.add(createTranslucentSideBar(300));
        bgPanel.add(bgLabel);
        //hibijini

    }
    public JLabel createTranslucentSideBar(int widthOfSideBar){
        JLabel jLabel = new JLabel();
        jLabel.setBounds(maxBounds.width-widthOfSideBar, 0, widthOfSideBar, maxBounds.height);
        jLabel.setBackground(new Color(0,0,0,130));
        jLabel.setOpaque(true);
        return jLabel;
    }


}

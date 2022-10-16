package shelved_classes;

import src.GameManager;
import src.LGameManager;

import javax.swing.*;
import java.awt.*;

public class checkPictureSize {
    LGameManager Lgm;
    JFrame window;
    GraphicsEnvironment ge;
    Rectangle maxBounds;
    public JTextArea messageText;

    public JPanel bgPanel[] = new JPanel[10];
    public JLabel bgLabel[] = new JLabel[10];

    public checkPictureSize(GameManager gm) {
        ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        maxBounds = ge.getMaximumWindowBounds();
        this.Lgm = Lgm;
        createMainField();
        createBackground();
        window.setVisible(true);
    }


    public void createMainField() {
        window = new JFrame();
        window.setSize(maxBounds.width, maxBounds.height);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.GRAY);
        window.setResizable(false);
        window.setLayout(null);


        messageText = new JTextArea();
        messageText.setSize(maxBounds.width, 100);
        messageText.setBackground(Color.black);
        messageText.setForeground(Color.white);
        messageText.setFont(new Font("Impact", Font.PLAIN, 26));
        messageText.setText(maxBounds.height + " " + maxBounds.width);
        window.add(messageText);

//        window.setLa;

    }

    public void createBackground() {
        bgPanel[1] = new JPanel();
        bgPanel[1].setBounds(0, 0, maxBounds.width, maxBounds.height);
        bgPanel[1].setBackground(Color.blue);
        bgPanel[1].setLayout(null);
        window.add(bgPanel[1]);


        ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource("images/LevelOneMain.png"));
        Image image = imageIcon.getImage();
        //titlebar height ~ 50px -> need to make it smaller to accomodate textbox though -> check info in textbox after running
        image = image.getScaledInstance(maxBounds.width, maxBounds.height - 100, Image.SCALE_DEFAULT);
        imageIcon = new ImageIcon(image);
        bgLabel[1] = new JLabel();
        bgLabel[1].setBounds(0, 0, maxBounds.width, maxBounds.height);
        bgLabel[1].setIcon(imageIcon);
        bgPanel[1].add(bgLabel[1]);


    }
}

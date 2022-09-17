import javax.swing.*;
import java.awt.*;

public class UI {
    JFrame window;
    GameManager gm;

    public JTextArea messageText;
    public UI(GameManager gm){
        this.gm = gm;
        createMainField();
        window.setVisible(true);
    }


    public void createMainField(){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        GraphicsConfiguration gc = gd.getDefaultConfiguration();
        window = new JFrame();
        window.setSize(gd.getDisplayMode().getWidth(), gd.getDisplayMode().getHeight());
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.GRAY);
        window.setLayout(null);



        messageText = new JTextArea();
        messageText.setSize(gd.getDisplayMode().getWidth(), 100);
        messageText.setBackground(Color.black);
        window.add(messageText);

//        window.setLa;

    }


}

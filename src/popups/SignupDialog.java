package src.popups;

import src.setup.FontInfo;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SignupDialog extends JDialog {

    JLabel dialogBackgroundLabel;
    JButton closeButton;
    JButton closeButton2;

    public SignupDialog(JFrame jFrame){
        setModal(true);
        setUndecorated(true);

        getContentPane().setBackground(Color.decode("#14171C"));
        setLayout(new FlowLayout());
        getRootPane().setBorder(new LineBorder(Color.white,2));
        setSize(500,500);
        setLocationRelativeTo(jFrame);
        setResizable(false);
        dialogBackgroundLabel = new JLabel();
        dialogBackgroundLabel.setPreferredSize(new Dimension(400,400));
        dialogBackgroundLabel.setLayout(new GridLayout(5,1));
        dialogBackgroundLabel.setHorizontalTextPosition(JLabel.LEFT);
        add(dialogBackgroundLabel);

        JTextPane userNameTextPane = new JTextPane();
        userNameTextPane.setFont(FontInfo.getResizedFont(40f));
        userNameTextPane.setText("Username: ");
        userNameTextPane.setBackground(Color.decode("#14171C"));
        userNameTextPane.setForeground(Color.white);
        userNameTextPane.setVisible(true);
        dialogBackgroundLabel.add(userNameTextPane);


        JTextArea userNameTextArea = new JTextArea();
        userNameTextArea.setFont(FontInfo.getResizedFont(30f));
        userNameTextArea.setBackground(Color.white);
        userNameTextArea.setForeground(Color.decode("#14171C"));
        userNameTextArea.setVisible(true);
        userNameTextArea.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "none");
        userNameTextArea.setText("Enter username here");
        userNameTextArea.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(userNameTextArea.getText().equals("") || userNameTextArea.getText().isBlank() || userNameTextArea.getText().isEmpty()) {
                    userNameTextArea.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(userNameTextArea.getText().equals("") || userNameTextArea.getText().isBlank() || userNameTextArea.getText().isEmpty()) {
                    userNameTextArea.setText("Enter username here");
                }
            }
        });
        dialogBackgroundLabel.add(userNameTextArea);


        JTextPane emailTextPane = new JTextPane();
        emailTextPane.setFont(FontInfo.getResizedFont(40f));
        emailTextPane.setText("Email: ");
        emailTextPane.setBackground(Color.decode("#14171C"));
        emailTextPane.setForeground(Color.white);
        emailTextPane.setVisible(true);
        dialogBackgroundLabel.add(emailTextPane);


        JTextArea emailTextArea = new JTextArea();
        emailTextArea.setFont(FontInfo.getResizedFont(30f));
        emailTextArea.setBackground(Color.white);
        emailTextArea.setForeground(Color.decode("#14171C"));
        emailTextArea.setVisible(true);
        emailTextArea.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "none");
        dialogBackgroundLabel.add(emailTextArea);




        setVisible(true);

    }

    public static String convertToMultiline(String orig)
    {
        return "<html>" + orig.replaceAll("\n", "<br>");
    }
}

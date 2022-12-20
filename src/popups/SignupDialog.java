package src.popups;

import src.buttons.BasicBlueButton;
import src.setup.DeviceInformation;
import src.setup.FontInfo;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupDialog extends JDialog {

    JLabel dialogBackgroundLabel;
    JTextArea userNameTextArea;

    BasicBlueButton submitButton;

    private void addSubmitButton(){
        submitButton = new BasicBlueButton(400,60,"Submit: ");
        add(submitButton);
    }

    public SignupDialog(JFrame jFrame){
        addDialogStyles(jFrame);
        addDialogBackgroundLabel();
        addFieldLabel("Username: ", 400,65);
        addUserNameTextBox();
        addUserNameTextBoxNullTransition();
        addFieldLabel("Email: ", 400, 65);
        addEmailTextBox();
        addSubmitButton();
        setVisible(true);

    }

    private void addDialogStyles(JFrame jFrame) {
        setModal(true);
        setUndecorated(true);
        getContentPane().setBackground(Color.decode("#14171C"));
        setLayout(new FlowLayout());
        getRootPane().setBorder(new LineBorder(Color.white,2));
        setSize(500,500);
        setLocationRelativeTo(jFrame);
        setResizable(false);
    }

    private void addDialogBackgroundLabel() {
        dialogBackgroundLabel = new JLabel();
        dialogBackgroundLabel.setPreferredSize(new Dimension(400,400));
        dialogBackgroundLabel.setLayout(new GridLayout(5,1));
        dialogBackgroundLabel.setHorizontalTextPosition(JLabel.LEFT);
        add(dialogBackgroundLabel);
    }

    private void addUserNameTextBoxNullTransition() {
        userNameTextArea.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(userNameTextArea.getText().equals("") || userNameTextArea.getText().isBlank() || userNameTextArea.getText().isEmpty()|| userNameTextArea.getText().equals("Enter username here")) {
                    userNameTextArea.setForeground(new Color(2, 2, 23, 255));
                    repaint();
                    revalidate();
                    userNameTextArea.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(userNameTextArea.getText().equals("") || userNameTextArea.getText().isBlank() || userNameTextArea.getText().isEmpty()) {
                    userNameTextArea.setForeground(new Color(2, 2, 23, 122));
                    repaint();
                    revalidate();
                    userNameTextArea.setText("Enter username here");
                }
            }
        });
    }

    private void addUserNameTextBox() {
        userNameTextArea = new JTextArea();
        userNameTextArea.setFont(FontInfo.getResizedFont(30f));
        userNameTextArea.setBackground(Color.white);
        userNameTextArea.setForeground(new Color(2, 2, 23, 122));
        userNameTextArea.setVisible(true);
        userNameTextArea.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "none");
        userNameTextArea.setText("Enter username here");
        dialogBackgroundLabel.add(userNameTextArea);

    }

    private void addEmailTextBox() {
        JTextArea emailTextArea = new JTextArea();
        emailTextArea.setFont(FontInfo.getResizedFont(30f));
        emailTextArea.setBackground(Color.white);
        emailTextArea.setForeground(Color.decode("#14171C"));
        emailTextArea.setVisible(true);
        emailTextArea.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "none");
        dialogBackgroundLabel.add(emailTextArea);
    }

    private void addFieldLabel(String text, int height, int width) {
        SignupDialogLabels emailTextPane = new SignupDialogLabels(text,height,width);
        dialogBackgroundLabel.add(emailTextPane);
    }

    public boolean checkIfValid(String email){
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        if(matcher.matches()){
            return true;
        }else{
            return false;
        }
    }



}

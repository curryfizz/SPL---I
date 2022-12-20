package src.popups;

import src.DatabaseConnection.OracleDatabase;
import src.buttons.BasicBlueButton;
import src.setup.FontInfo;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupDialog extends JDialog {

    JLabel userNameFieldLabel;
    JLabel emailFieldLabel;
    JLabel submitFieldLabel;
    JTextArea userNameTextArea;
    JTextArea emailTextArea;
    SignupDialogLabel emailVerificationLabel;
    BasicBlueButton submitButton;
    private void addSubmitButton(){
        submitButton = new BasicBlueButton(200,30,"Submit: ");
        submitButton.setFont(FontInfo.getResizedFont(28f));
        submitFieldLabel.add(submitButton);
    }

    private void addEmailFieldLabel(){
        emailFieldLabel = new JLabel();
        emailFieldLabel.setPreferredSize(new Dimension(400,90));
        emailFieldLabel.setLayout(new FlowLayout(FlowLayout.CENTER,10,3));
        emailFieldLabel.setHorizontalTextPosition(JLabel.LEFT);
        add(emailFieldLabel);
        emailFieldLabel.add(createLabel("Email: ", 400, 28));
        addEmailTextBox();
        addEmailAlreadyRegisteredLabel();
    }

    private void addEmailAlreadyRegisteredLabel(){
        emailVerificationLabel = new SignupDialogLabel("*Email already exists, try again or enter new email", 400, 20);
        emailVerificationLabel.setFont(FontInfo.getResizedFont(20f));
        emailVerificationLabel.setForeground(Color.red);
        emailVerificationLabel.setVisible(false);
        emailFieldLabel.add(emailVerificationLabel);
    }
    private void addSubmitFieldLabel(){
        submitFieldLabel = new JLabel();
        submitFieldLabel.setPreferredSize(new Dimension(400,90));
        submitFieldLabel.setLayout(new FlowLayout(FlowLayout.CENTER,10,3));
        submitFieldLabel.setHorizontalTextPosition(JLabel.LEFT);
        add(submitFieldLabel);
        addSubmitButton();
        addSubmitEmailVerification();
    }
    public SignupDialog(JFrame jFrame){
        addDialogStyles(jFrame);
        addUserFieldLabel();
        addEmailFieldLabel();
        addSubmitFieldLabel();
        setVisible(true);

    }

    private void addDialogStyles(JFrame jFrame) {
        setModal(true);
        setUndecorated(true);
        getContentPane().setBackground(Color.decode("#14171C"));
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 2));
        getRootPane().setBorder(new LineBorder(Color.white,2));
        setSize(500,250);
        setLocationRelativeTo(jFrame);
        setResizable(false);
    }
    
    
    private void addUserFieldLabel() {
        userNameFieldLabel = new JLabel();
        userNameFieldLabel.setPreferredSize(new Dimension(400,70));
        userNameFieldLabel.setLayout(new FlowLayout(FlowLayout.CENTER,10,3));
        userNameFieldLabel.setHorizontalTextPosition(JLabel.LEFT);
        add(userNameFieldLabel);
        userNameFieldLabel.add(createLabel("Username: ", 400, 35));
        addUserNameTextBox();
        addUserNameTextBoxNullTransition();
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
        userNameTextArea.setFont(FontInfo.getResizedFont(24f));
        userNameTextArea.setBackground(Color.white);
        userNameTextArea.setBorder(new LineBorder(Color.white, 3));
        userNameTextArea.setPreferredSize(new Dimension(400, 30));
        userNameTextArea.setForeground(new Color(2, 2, 23, 122));
        userNameTextArea.setVisible(true);
        userNameTextArea.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "none");
        userNameTextArea.setText("Enter username here");
        userNameFieldLabel.add(userNameTextArea);

    }

    private void addEmailTextBox() {
        emailTextArea = new JTextArea();
        emailTextArea.setFont(FontInfo.getResizedFont(24f));
        emailTextArea.setBackground(Color.white);
        emailTextArea.setBorder(new LineBorder(Color.white, 3));
        emailTextArea.setPreferredSize(new Dimension(400, 30));
        emailTextArea.setForeground(Color.decode("#14171C"));
        emailTextArea.setVisible(true);
        emailTextArea.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "none");
        emailFieldLabel.add(emailTextArea);
    }

    private SignupDialogLabel createLabel(String text, int width, int height) {
        SignupDialogLabel signupDialogLabel = new SignupDialogLabel(text,width,height);
        signupDialogLabel.setFont(FontInfo.getResizedFont(28f));
        return signupDialogLabel;
    }

    private void insertUserIntoDatabase(OracleDatabase database){

    }
    private void addSubmitEmailVerification(){
        submitButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                userNameTextArea.setFocusable(false);
                userNameTextArea.setEnabled(false);
                emailTextArea.setFocusable(false);
                emailTextArea.setEnabled(false);
                submitButton.setEnabled(false);
                submitButton.setFocusable(false);
                repaint();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                if(isEmailValid(emailTextArea.getText())==false){
                    System.out.println(isEmailValid(emailTextArea.getText()));
                    emailVerificationLabel.setVisible(true);
                    emailVerificationLabel.setText("*Enter email in correct format!");
                    emailVerificationLabel.setVisible(true);
                }
                repaint();
                userNameTextArea.setFocusable(true);
                userNameTextArea.setEnabled(true);
                emailTextArea.setFocusable(true);
                emailTextArea.setEnabled(true);
                submitButton.setEnabled(true);
                submitButton.setFocusable(true);
                repaint();
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
    public boolean isEmailValid(String email){
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

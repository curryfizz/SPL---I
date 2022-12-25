package src.popups;

import src.DatabaseConnection.OracleDatabase;
import src.buttons.BasicBlueButton;
import src.setup.FontInfo;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupDialog extends JDialog {

    JLabel userNameFieldLabel;
    JLabel emailFieldLabel;
    JLabel submitFieldLabel;
    SignupTextAreas userNameTextArea;
    SignupTextAreas emailTextArea;
    SignupDialogLabel emailVerificationLabel;

    SignupDialogLabel accountCreatedExit;
    BasicBlueButton submitButton;

    OracleDatabase database;
    private void addSubmitButton(){
        submitButton = new BasicBlueButton(200,40,"Submit");
        submitButton.setFont(FontInfo.getResizedFont(30f));
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
        submitFieldLabel.setPreferredSize(new Dimension(400,80));
        submitFieldLabel.setLayout(new FlowLayout(FlowLayout.CENTER,10,6));
        submitFieldLabel.setHorizontalTextPosition(JLabel.LEFT);
        add(submitFieldLabel);
        addAccountCreatedExitLabel();
        addSubmitButton();
        addSubmitEmailVerification();
    }

    private void addAccountCreatedExitLabel(){
        accountCreatedExit = new SignupDialogLabel("", 400, 25);
        accountCreatedExit.setForeground(Color.pink);
        accountCreatedExit.setFont(FontInfo.getResizedFont(22f));
        submitFieldLabel.add(accountCreatedExit);
    }
    public SignupDialog(JFrame jFrame, OracleDatabase oracleDatabase){
        this.database = oracleDatabase;
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
        setLayout(new FlowLayout(FlowLayout.CENTER, 12, 12));
        getRootPane().setBorder(new LineBorder(Color.white,2));
        setSize(500,330);
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
    }

    private void addUserNameTextBox() {
        userNameTextArea = new SignupTextAreas(400,30);
        userNameTextArea.addFocusEvent("Enter username here");
        userNameFieldLabel.add(userNameTextArea);

    }

    private void addEmailTextBox() {
        emailTextArea = new SignupTextAreas(400,30);
        emailTextArea.addFocusEvent("Enter email here");
        emailFieldLabel.add(emailTextArea);
    }

    private SignupDialogLabel createLabel(String text, int width, int height) {
        SignupDialogLabel signupDialogLabel = new SignupDialogLabel(text,width,height);
        signupDialogLabel.setFont(FontInfo.getResizedFont(28f));
        return signupDialogLabel;
    }


    private void showEmailNotInCurrentFormatLabel(){
        emailVerificationLabel.setText("*Enter email in correct format!");
        emailVerificationLabel.setVisible(true);
        repaint();

    }

    private void disableEverything(){
        userNameTextArea.setFocusable(false);
        userNameTextArea.setEnabled(false);
        emailTextArea.setFocusable(false);
        emailTextArea.setEnabled(false);
        submitButton.setEnabled(false);
        submitButton.setFocusable(false);
        repaint();
    }

    private void enableEverything(){
        userNameTextArea.setFocusable(true);
        userNameTextArea.setEnabled(true);
        emailTextArea.setFocusable(true);
        emailTextArea.setEnabled(true);
        submitButton.setEnabled(true);
        submitButton.setFocusable(true);
        repaint();
    }

    private void showEmailAlreadyExistsLabel(){
        emailVerificationLabel.setText("*Email already exists, try again or enter new email!");
        emailVerificationLabel.setVisible(true);
        repaint();
    }


    private void addSubmitEmailVerification(){
        submitButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                try {
                    disableEverything();
                    Thread.sleep(500);
                        System.out.println(userNameTextArea.getText());
                        System.out.println(emailTextArea.getText());
                    if(isEmailValid(emailTextArea.getText())==false){
                        System.out.println(isEmailValid(emailTextArea.getText()));
                        showEmailNotInCurrentFormatLabel();
                        enableEverything();
                    }else if(database.insertUser(userNameTextArea.getText(), emailTextArea.getText())==false){
                        showEmailAlreadyExistsLabel();
                        enableEverything();
                    }else{
                        accountCreatedExitLabelCountDown();
                    }
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }catch (SQLException sqlException){
                    sqlException.printStackTrace();
                }


            }

            private void accountCreatedExitLabelCountDown() {
                Timer timer = new Timer(0, new ActionListener() {
                    int seconds = 10;
                    boolean timeOver = false;
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        accountCreatedExit.updateText(seconds);
                        if(seconds==-1){
                            dispose();
                            return;
                        }
                        seconds--;

                    }
                });

                timer.setDelay(1000);
                timer.start();
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

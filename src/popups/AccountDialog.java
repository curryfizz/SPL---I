package src.popups;

import src.DatabaseConnection.OracleDatabase;
import src.setup.FontInfo;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AccountDialog extends JDialog {
    JLabel emailFieldLabel;
    AccountDialogTextArea emailTextArea;
    OracleDatabase database;
    AccountDialogLabel emailVerificationLabel;

    JFrame jFrame;
    protected void doExitCountDown(AccountDialogLabel exitLabel, String text){
        Timer timer = new Timer(0, new ActionListener() {
            int seconds = 10;

            @Override
            public void actionPerformed(ActionEvent e) {
                exitLabel.updateText(seconds, text);
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
    protected void addEmailFieldLabel(String text){
        emailFieldLabel = new JLabel();
        emailFieldLabel.setPreferredSize(new Dimension(400,90));
        emailFieldLabel.setLayout(new FlowLayout(FlowLayout.CENTER,10,3));
        emailFieldLabel.setHorizontalTextPosition(JLabel.LEFT);
        add(emailFieldLabel);
        emailFieldLabel.add(createLabel("Email: ", 28));
        addEmailTextBox();
        addEmailVerificationLabel(text);
    }


    public AccountDialog(JFrame jFrame, OracleDatabase oracleDatabase){
        this.database = oracleDatabase;
        this.jFrame = jFrame;
        addDialogStyles(jFrame);
    }

    protected void addDialogStyles(JFrame jFrame) {
        setModal(true);
        setUndecorated(true);
        getContentPane().setBackground(Color.decode("#14171C"));
        setLayout(new FlowLayout(FlowLayout.CENTER, 12, 12));
        getRootPane().setBorder(new LineBorder(Color.white,2));
        setSize(500,330);
        setLocationRelativeTo(jFrame);
        setResizable(false);
    }

    protected void disableEmailFields(){
        emailTextArea.setEnabled(false);
        emailTextArea.setFocusable(false);
        repaint();
    }
    protected void enableEmailFields(){
        emailTextArea.setEnabled(true);
        emailTextArea.setFocusable(true);
        repaint();
    }

    protected void addEmailTextBox() {
        emailTextArea = new AccountDialogTextArea(400,30);
        emailTextArea.addFocusEvent("Enter email here");
        emailFieldLabel.add(emailTextArea);
    }

    protected AccountDialogLabel createLabel(String text, int height) {
        AccountDialogLabel accountDialogLabel = new AccountDialogLabel(text, 400,height);
        accountDialogLabel.setFont(FontInfo.getResizedFont(28f));
        return accountDialogLabel;
    }

    protected boolean isEmailValid(String email){
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    protected void addEmailVerificationLabel(String text){
        emailVerificationLabel = new AccountDialogLabel(text, 400, 20);
        emailVerificationLabel.setFont(FontInfo.getResizedFont(22f));
        emailVerificationLabel.setForeground(Color.red);
        emailVerificationLabel.setVisible(false);
        emailFieldLabel.add(emailVerificationLabel);
    }

    protected void showEmailNotInCurrentFormatLabel(){
        emailVerificationLabel.setText("*Enter email in correct format!");
        emailVerificationLabel.setVisible(true);
        repaint();

    }


}


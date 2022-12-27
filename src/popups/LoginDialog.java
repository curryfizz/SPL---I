package src.popups;

import src.DatabaseConnection.OracleDatabase;
import src.buttons.BasicBlueButton;
import src.setup.FontInfo;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

public class LoginDialog extends AccountDialog {

    JLabel loginFieldLabel;
    BasicBlueButton loginButton;
    AccountDialogLabel loginSuccessFullExitLabel;

    public LoginDialog(JFrame jFrame, OracleDatabase oracleDatabase) {
        super(jFrame, oracleDatabase);
        setSize(500, 220);
        addEmailFieldLabel("*Enter email in correct format!");
        addLoginFieldLabel();
        setVisible(true);
    }
    public LoginDialog(JFrame jFrame){
        super(jFrame);
        setSize(500, 220);
        addEmailFieldLabel("*Enter email in correct format!");
        addLoginFieldLabel();
        setVisible(true);
    }

    private void addLoginSuccessFullExitLabel(){
        loginSuccessFullExitLabel = new AccountDialogLabel("", 400, 25);
        loginSuccessFullExitLabel.setForeground(Color.pink);
        loginSuccessFullExitLabel.setFont(FontInfo.getResizedFont(22f));
        loginFieldLabel.add(loginSuccessFullExitLabel);
    }

    private void addLoginFieldLabel(){
        loginFieldLabel = new JLabel();
        loginFieldLabel.setPreferredSize(new Dimension(400,80));
        loginFieldLabel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 6));
        loginFieldLabel.setHorizontalTextPosition(JLabel.LEFT);
        add(loginFieldLabel);
        addLoginSuccessFullExitLabel();
        addLoginButton();
        addCancelButton();
    }

    @Override
    void addCancelButton(){
        super.addCancelButton();
        loginFieldLabel.add(cancelButton);
    }
    private void addLoginButton(){
        loginButton = new BasicBlueButton(120, 40, "Login");
        loginButton.setFont(FontInfo.getResizedFont(30f));
        loginFieldLabel.add(loginButton);
        addLoginButtonMouseEvents();
    }

    private void disableLoginButton(){
        loginButton.setFocusable(false);
        loginButton.setEnabled(false);
        repaint();
    }
    private void enableLoginButton(){
        loginButton.setFocusable(true);
        loginButton.setEnabled(true);
        repaint();
    }
    private void addLoginButtonMouseEvents(){
        loginButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    if(!loginButton.isEnabled()){
                        return;
                    }
                    disableEmailFields();
                    disableLoginButton();
                    Thread.sleep(500);

                    System.out.println(emailTextArea.getText());
                    if(!isEmailValid(emailTextArea.getText())){
                        System.out.println(isEmailValid(emailTextArea.getText()));
                        showEmailNotInCurrentFormatLabel();
                        enableEmailFields();
                        enableLoginButton();
                    }else if(OracleDatabase.retrieveUserInfo(emailTextArea.getText())){
                        doExitCountDown(loginSuccessFullExitLabel, "Login successful");
                    }else{
                        EmailNotFoundDialog emailNotFoundDialog = new EmailNotFoundDialog(returnSelf());
                        enableEmailFields();
                        enableLoginButton();
                    }
                }catch (InterruptedException ex){
                    throw new RuntimeException(ex);
                }catch (SQLException sqlException){
                    sqlException.printStackTrace();
                }
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

    private LoginDialog returnSelf(){
        return this;
    }

}

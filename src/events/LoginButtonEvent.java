package src.events;

import src.popups.LoginDialog;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LoginButtonEvent implements MouseListener {
    JFrame jFrame;

    public LoginButtonEvent(JFrame jFrame){
        this.jFrame = jFrame;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        LoginDialog loginDialog = new LoginDialog(jFrame);
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
}

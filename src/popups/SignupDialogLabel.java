package src.popups;

import src.setup.FontInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignupDialogLabel extends JLabel{

    public SignupDialogLabel(String text, int width, int height){
        addLabelStyles(text, width, height);
    }

    private void addLabelStyles(String text, int width, int height){
        setFont(FontInfo.getResizedFont(35f));
        setText(text);
        setPreferredSize(new Dimension(width,height));
        setFocusable(false);
        setBackground(Color.decode("#14171C"));
        setForeground(Color.white);
        setVisible(true);
    }

    public void updateText(int seconds){
        setHorizontalAlignment(CENTER);
        setText("Account created successfully! Exiting to main menu in (" + seconds + ")");
    }

}

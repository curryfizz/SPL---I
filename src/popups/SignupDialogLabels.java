package src.popups;

import src.buttons.BasicBlueButton;
import src.setup.FontInfo;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupDialogLabels extends JLabel{

    public SignupDialogLabels(String text, int width, int height){
        addLabelStyles(text, width, height);
    }

    private void addLabelStyles(String text, int width, int height){
        setFont(FontInfo.getResizedFont(40f));
        setText(text);
        setPreferredSize(new Dimension(width,height));
        setFocusable(false);
        setBackground(Color.decode("#14171C"));
        setForeground(Color.white);
        setVisible(true);
    }



}

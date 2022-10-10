package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameTimer extends JLabel{
    UI ui;
    JPanel backGroundPanel;
    Timer timer;

    Font font;
//    JLabel counterLabel;
    int second;
    int minute;

    public GameTimer(UI ui) {
        this.ui = ui;
        this.backGroundPanel = ui.bgPanel;
        this.font = ui.eastSeaDokdo;

//        backGroundPanel.setLayout(null);
//        backGroundPanel.setBounds(0, 0, 80, 60);
//        backGroundPanel.setBackground(new Color(150, 150, 150));

        createCounterLabel();

        backGroundPanel.add(this);
        backGroundPanel.add(backGroundPanel);

        second = 0;
        minute = 0;
        simpleTimer();
        timer.start(); // kinda like background operation hoye jay

    }

    public void createCounterLabel(){
//        counterLabel = new JLabel();
        this.setBounds(5,5, 100, 50);
        this.setFont(font);
        this.setText("00:00");

        backGroundPanel.add(this);
        backGroundPanel.add(backGroundPanel);
    }

    public void simpleTimer() {

        timer = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                second ++;
                if (second >= 60)
                {
                    minute ++;
                    second -= 60;
                }
                //drawTimer();
            }

        }); // updates every 1 second
    }

    public String drawTimer() {
        if(minute<10 && second<10) {
            return ("0" + minute + ":" + "0" + second);
        }
        else if (minute<10 && second>=10) {
            return ("0" + minute + ":" + second);
        }
        else if (minute>=10 && second<10) {
            return ( minute + ":" + "0" + second);
        }
        else {
            return (minute + ":" + second);
        }
    }
}

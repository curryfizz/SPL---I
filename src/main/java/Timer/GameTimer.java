package Timer;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameTimer {

    JPanel backGroundPanel;

    Timer timer;

    Font font1;
    JLabel counterLabel;
    int second;
    int minute;

    public GameTimer(JFrame window) {

        font1 = new Font("Impact", Font.PLAIN, 20);

        backGroundPanel = new JPanel();
        backGroundPanel.setLayout(null);
        backGroundPanel.setBounds(0, 0, 70, 50);
        backGroundPanel.setBackground(Color.gray);


        counterLabel = new JLabel();
        counterLabel.setBounds(5,0, 100, 50);
        counterLabel.setFont(font1);

        backGroundPanel.add(counterLabel);
        window.add(backGroundPanel);

        second = 0;
        minute = 0;
        simpleTimer();
        timer.start(); // kinda like background operation hoye jay

        System.out.println("kill me");
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
                updateTimer();
            }

        }); // updates every 1 second
    }

    public void updateTimer() {
        if(minute<10 && second<10) {
            counterLabel.setText("0" + minute + ":" + "0" + second);
        }
        else if (minute<10 && second>=10) {
            counterLabel.setText("0" + minute + ":" + second);
        }
        else if (minute>=10 && second<10) {
            counterLabel.setText( minute + ":" + "0" + second);
        }
        else {
            counterLabel.setText(minute + ":" + second);
        }
    }
}

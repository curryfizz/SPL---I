package src;

import shelved_classes.IScene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DormRoom extends JPanel implements IScene {
    JFrame jFrame;
    DeviceScreenInformation deviceInfo;
    FontInfo fontInfo;

    ScoreBoard scoreBoard;
    CloseButton closeButton;
    int score = 0;
    public DormRoom(JFrame jFrame, DeviceScreenInformation deviceScreenInformation, FontInfo fontInfo){
        jFrame.revalidate();
        jFrame.repaint();
        this.jFrame = jFrame;
        this.deviceInfo = deviceScreenInformation;
        this.fontInfo = fontInfo;
        createBackgroundPanel();
        addCustomWindowCloseButton(jFrame);

//        TimerLabel timerLabel = new TimerLabel(jFrame, this, deviceScreenInformation, fontInfo);
//        TextBox textBox = new TextBox(jFrame, this, deviceScreenInformation, fontInfo);
//        ScoreBoard scoreBoard = new ScoreBoard(jFrame, this, deviceScreenInformation, fontInfo);

        JButton DummyButton = new JButton("Object");
        DummyButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                score += 100;
                scoreBoard.setText(Integer.toString(score));
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
//        this.add(DummyButton);
        DummyButton.setBackground(Color.pink);
        DummyButton.setOpaque(true);
        DummyButton.setVisible(true);
        DummyButton.setBounds(900,400, 100, 100);
        jFrame.add(this);
        jFrame.repaint();
        jFrame.revalidate();
    }

    public void createBackgroundPanel(){
        this.setLayout(null);
        this.setPreferredSize(new Dimension(deviceInfo.screenWidth, deviceInfo.screenHeight));
        this.setBackground(Color.decode("#14171C"));
        this.setForeground(Color.decode("#C64C1D"));
        this.setOpaque(true);
    }

    public void addCustomWindowCloseButton(JFrame jFrame){
        closeButton = new CloseButton(deviceInfo,"X",jFrame, fontInfo);
        this.add(closeButton);
        this.revalidate();
        this.repaint();
    }

    @Override
    public void callSelf() {
        new DormRoom(jFrame,deviceInfo,fontInfo);
    }

    @Override
    public void startTimer() {
        TimerLabel timerLabel = new TimerLabel(jFrame, this, deviceInfo, fontInfo);
        TextBox textBox = new TextBox(jFrame, this, deviceInfo, fontInfo);
        scoreBoard = new ScoreBoard(jFrame, this, deviceInfo, fontInfo);

    }
}

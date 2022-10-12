package src;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class closeWindowMouseEvents implements MouseListener {

    FontInfo fontInfo;

    JFrame jFrame;
    closeWindowMouseEvents(FontInfo fontInfo, JFrame jFrame){//devicescreeninfo is bad here only needed for font :(
        this.fontInfo = fontInfo;
        this.jFrame = jFrame;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        ConfirmationWindowPopup exitWindowPopup = new ConfirmationWindowPopup(fontInfo);
        jFrame.add(exitWindowPopup);
        int choice = ConfirmationWindowPopup.showConfirmDialog(jFrame,"Do you want to exit?","Exit",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE);
        if(choice==JOptionPane.YES_OPTION){
            System.exit(0);
        }
//        jFrame.repaint();
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

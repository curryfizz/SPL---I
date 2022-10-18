package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuMouseEvents implements MouseListener {
    MapT mapT;
    String buttonInfo;
    MenuMouseEvents(MapT mapT, String text){
        buttonInfo = text;
        this.mapT = mapT;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(buttonInfo.equals("Dormitory")){
//            mapT.remove(mapT.padLockDorm);
//            mapT.revalidate();
//            mapT.repaint();
        }else if(buttonInfo.equals("Academic Building 2")){
//            mapT.remove(mapT.padLockAC2);
//            mapT.revalidate();
//            mapT.repaint();
        }else if(buttonInfo.equals("Library")){
//            mapT.remove(mapT.padLockLibrary);
//            mapT.revalidate();
//            mapT.repaint();
        }else if(buttonInfo.equals("CDS")){
//            mapT.remove(mapT.padLockCDS);
//            mapT.revalidate();
//            mapT.repaint();
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


}

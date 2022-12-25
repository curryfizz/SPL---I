package src.popups;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EmailAlreadyRegisteredDialog extends AccountNotSuccessDialog {
    public EmailAlreadyRegisteredDialog(AccountDialog accountDialog) {
        super(accountDialog);
        addUnsuccessfulReasonLabel("Email already registered!");
        addButtonFieldLabel();
        addYesButton("Login");
        addNoButton();
        setVisible(true);

    }

    @Override
    protected void addYesButtonMouseEvents() {
        yesButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                parentAccountDialog.dispose();
                LoginDialog loginDialog = new LoginDialog(parentAccountDialog.jFrame, parentAccountDialog.database);
                parentAccountDialog.jFrame.repaint();
                parentAccountDialog.jFrame.revalidate();
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
}

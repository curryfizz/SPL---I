package src;

import javax.swing.*;

public abstract class IScene extends JPanel {
    public void callSelf() {

    }

    public abstract void startScene();
    public abstract void PrepareForSceneTransition(LoadingAnimationT loadingAnimationT, JPanel mapT);

}

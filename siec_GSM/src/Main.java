import Model.*;
import View.MyFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MyFrame(VBDManager.getInstance(),
                        BTSLeftManager.getInstance(),
                        BSCManager.getInstance(0),
                        BTSRightManager.getInstance(),
                        VRDManager.getInstance(),
                        BSCManager.getInstance(0));
            }
        });
    }
}

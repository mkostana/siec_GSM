package View;

import Control.Addable;
import Control.LayerManagerControl;
import Control.VBDManagerControl;
import Control.VRDManagerControl;
import View.LeftPanel;
import View.MiddlePanel;
import View.RightPanel;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    public MyFrame(VBDManagerControl a, LayerManagerControl b, LayerManagerControl c, LayerManagerControl d, VRDManagerControl e, Addable f){
        this.setSize(1080, 720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setTitle("S28535Projekt03");

        LeftPanel leftPanel = new LeftPanel(a);

        MiddlePanel middlePanel = new MiddlePanel(b,c,d,f);

        RightPanel rightPanel = new RightPanel(e);

        this.add(leftPanel, BorderLayout.LINE_START);
        this.add(middlePanel, BorderLayout.CENTER);
        this.add(rightPanel, BorderLayout.LINE_END);

        this.setVisible(true);
    }
}

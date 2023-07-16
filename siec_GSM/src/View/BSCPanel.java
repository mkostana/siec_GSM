package View;


import Control.LayerControl;
import Control.Refresh;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class BSCPanel extends JPanel implements Refresh {
    private LayerControl bscControl;
    private JLabel jLabel2;
    private JLabel jLabel3;
    public BSCPanel(LayerControl bscControl){
        this.bscControl = bscControl;
        bscControl.setPanel(this);
        this.setLayout(new GridLayout(3,1));
        this.setBorder(new LineBorder(Color.BLACK, 2));

        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(new FlowLayout());
        JLabel jLabel1 = new JLabel("ID: " + bscControl.getNumber());
        jPanel1.add(jLabel1);
        JPanel jPanel2 = new JPanel();
        jPanel2.setLayout(new FlowLayout());
        jLabel2 = new JLabel("Processed SMS: " + bscControl.getProcessedSMS());
        jPanel2.add(jLabel2);
        JPanel jPanel3 = new JPanel();
        jPanel3.setLayout(new FlowLayout());
        jLabel3 = new JLabel("Pending SMS " + bscControl.getPendingSMS());
        jPanel3.add(jLabel3);

        this.add(jPanel1);
        this.add(jPanel2);
        this.add(jPanel3);
    }

    @Override
    public void refresh() {
        jLabel2.setText("Processed SMS: " + bscControl.getProcessedSMS());
        jLabel3.setText("Pending SMS " + bscControl.getPendingSMS());
        this.revalidate();
        this.repaint();
    }
}

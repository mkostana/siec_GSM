package View;

import Control.LayerControl;
import Control.Refresh;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class BTSPanel extends JPanel implements Refresh {
    private LayerControl btsControl;
    private JLabel jLabel2;
    private JLabel jLabel3;
    public BTSPanel(LayerControl btsControl){
        btsControl.setPanel(this);
        this.btsControl = btsControl;
        this.setLayout(new GridLayout(3,1));
        this.setBorder(new LineBorder(Color.BLACK, 2));

        JPanel jPanel1 = new JPanel();
        JLabel jLabel1 = new JLabel("ID: " + btsControl.getNumber());
        jPanel1.add(jLabel1);
        JPanel jPanel2 = new JPanel();
        jLabel2 = new JLabel("Processed SMS: " + btsControl.getProcessedSMS());
        jPanel2.add(jLabel2);
        JPanel jPanel3 = new JPanel();
        jLabel3 = new JLabel("Pending SMS " + btsControl.getPendingSMS());
        jPanel3.add(jLabel3);

        this.add(jPanel1);
        this.add(jPanel2);
        this.add(jPanel3);
    }

    @Override
    public void refresh() {
        jLabel2.setText("Processed SMS: " + btsControl.getProcessedSMS());
        jLabel3.setText("Pending SMS " + btsControl.getPendingSMS());
        this.revalidate();
        this.repaint();
    }
}

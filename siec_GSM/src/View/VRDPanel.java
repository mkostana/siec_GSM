package View;

import Control.Refresh;
import Control.VRDControl;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VRDPanel extends JPanel implements Refresh {
    private VRDControl vrdControl;
    private JLabel jLabel2;
    public VRDPanel(VRDControl vrdControl, RightPanel rightPanel){
        this.vrdControl = vrdControl;
        vrdControl.setPanel(this);
        VRDPanel vrdPanel = this;

        this.setLayout(new GridLayout(4,1));
        this.setBorder(new LineBorder(Color.BLACK, 2));

        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout());
        JLabel jLabel1 = new JLabel("ID: " + vrdControl.getNumber());
        namePanel.add(jLabel1);

        JPanel SMSreceivedPanel = new JPanel();
        SMSreceivedPanel.setLayout(new FlowLayout());
        jLabel2 = new JLabel("SMS received: " + vrdControl.getMessagesReceived());
        SMSreceivedPanel.add(jLabel2);

        JPanel autoRemovePanel = new JPanel();
        autoRemovePanel.setLayout(new FlowLayout());
        JLabel jLabel3 = new JLabel("Auto remove received SMS");
        JCheckBox jCheckBox = new JCheckBox();
        jCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vrdControl.setMessagesRemove(jCheckBox.isSelected());
                revalidate();
                repaint();
            }
        });
        autoRemovePanel.add(jLabel3);
        autoRemovePanel.add(jCheckBox);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton jButton = new JButton("REMOVE");
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vrdControl.remove();
                rightPanel.removeVRDPanel(vrdPanel);
                rightPanel.revalidate();
                rightPanel.repaint();
            }
        });
        buttonPanel.add(jButton);

        this.add(namePanel);
        this.add(SMSreceivedPanel);
        this.add(autoRemovePanel);
        this.add(buttonPanel);
    }

    @Override
    public void refresh() {
        jLabel2.setText("SMS received: " + vrdControl.getMessagesReceived());
        this.revalidate();
        this.repaint();
    }
}

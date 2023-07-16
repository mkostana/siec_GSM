package View;

import Control.VRDManagerControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RightPanel extends JPanel {
    private JPanel devicesPanel;
    public RightPanel(VRDManagerControl vrdManagerControl){
        this.setLayout(new BorderLayout());
        this.setBackground(Color.LIGHT_GRAY);

        devicesPanel = new JPanel();
        devicesPanel.setLayout(new BoxLayout(devicesPanel, BoxLayout.Y_AXIS));

        JScrollPane jScrollPane = new JScrollPane(devicesPanel);
        this.add(jScrollPane, BorderLayout.CENTER);

        RightPanel rightPanel = this;

        JButton jButton = new JButton("Add");
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vrdManagerControl.createVRD();
                VRDPanel vrdPanel = new VRDPanel(vrdManagerControl.getLastVRD(), rightPanel);
                devicesPanel.add(vrdPanel);
                revalidate();
                repaint();
            }
        });
        this.add(jButton, BorderLayout.PAGE_END);
    }

    public void removeVRDPanel(VRDPanel vrdPanel){
        devicesPanel.remove(vrdPanel);
    }
}

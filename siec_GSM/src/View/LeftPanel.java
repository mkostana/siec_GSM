package View;

import Control.VBDControl;
import Control.VBDManagerControl;
import Model.Status;
import Model.VBD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeftPanel extends JPanel {
    private JPanel devicesPanel;
    private VBDManagerControl vbdManagerControl;

    public LeftPanel(VBDManagerControl vbdManagerControl){
        this.vbdManagerControl = vbdManagerControl;
        this.setLayout(new BorderLayout());
        this.setBackground(Color.LIGHT_GRAY);

        devicesPanel = new JPanel();
        devicesPanel.setLayout(new BoxLayout(devicesPanel, BoxLayout.Y_AXIS));

        JScrollPane jScrollPane = new JScrollPane(devicesPanel);
        this.add(jScrollPane, BorderLayout.CENTER);

        LeftPanel leftPanel = this;


        JButton jButton = new JButton("Add");
        jButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String message = JOptionPane.showInputDialog("Enter the message:");
                        if(message != null && !message.isEmpty()){
                            vbdManagerControl.createVBD(message);
                            VBDPanel vbdPanel = new VBDPanel(vbdManagerControl.getLastVBD(), leftPanel);
                            devicesPanel.add(vbdPanel);
                            revalidate();
                            repaint();
                        }
                    }
                }
        );
        this.add(jButton, BorderLayout.PAGE_END);
    }

    public void removeVBDPanel(VBDPanel vbdPanel){
        devicesPanel.remove(vbdPanel);
    }
}

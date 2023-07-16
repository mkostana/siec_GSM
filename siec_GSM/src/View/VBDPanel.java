package View;

import Control.Refresh;
import Control.VBDControl;
import Model.Status;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VBDPanel extends JPanel {
    private VBDControl vbdControl;

    public VBDPanel(VBDControl vbdControl, LeftPanel leftPanel){
        this.vbdControl = vbdControl;

        this.setLayout(new GridLayout(5, 1));
        this.setBorder(new LineBorder(Color.BLACK, 2));

        JPanel frequencyPanel = new JPanel();
        frequencyPanel.setLayout(new FlowLayout());
        JLabel jLabel1 = new JLabel("Frequency: " + vbdControl.getFrequency());
        JSlider jSlider = new JSlider(1,10,5);
        jSlider.setMajorTickSpacing(2);
        jSlider.setMinorTickSpacing(1);
        jSlider.setPaintTicks(true);
        jSlider.setPaintLabels(true);
        jSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = jSlider.getValue();
                vbdControl.setFrequency(value);
                jLabel1.setText("Frequency: " + vbdControl.getFrequency());
                revalidate();
                repaint();
            }
        });
        frequencyPanel.add(jLabel1);
        frequencyPanel.add(jSlider);

        VBDPanel vbdPanel = this;

        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout());
        JTextField jTextField = new JTextField("ID: " + vbdControl.getNumber());
        jTextField.setEditable(false);
        namePanel.add(jTextField);

        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new FlowLayout());
        JLabel jLabel2 = new JLabel("Status: ");
        JComboBox<Status> jComboBox = new JComboBox<>(new Status[]{Status.WAITING, Status.ACTIVE});
        jComboBox.setSelectedItem(vbdControl.getStatus());
        jComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vbdControl.setStatus((Status) jComboBox.getSelectedItem());
            }
        });
        statusPanel.add(jLabel2);
        statusPanel.add(jComboBox);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton jButton = new JButton("REMOVE");
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vbdControl.remove();
                leftPanel.removeVBDPanel(vbdPanel);
                leftPanel.revalidate();
                leftPanel.repaint();
            }
        });
        buttonPanel.add(jButton);

        this.add(frequencyPanel);
        this.add(namePanel);
        this.add(statusPanel);
        this.add(buttonPanel);
    }
}

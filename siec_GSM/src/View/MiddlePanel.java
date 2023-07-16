package View;

import Control.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MiddlePanel extends JPanel implements RefreshMid {
    private JPanel leftBTSPanel;
    private List<JPanel> BSCPanel;
    private JPanel BSCsPanel;
    private JPanel rightBTSPanel;
    public MiddlePanel(LayerManagerControl btsLeftMC, LayerManagerControl bscMC, LayerManagerControl btsRightMC, Addable es){
        this.setLayout(new BorderLayout());
        this.setBackground(Color.LIGHT_GRAY);
        BSCPanel = new ArrayList<>();
        btsLeftMC.setPanel(this);
        bscMC.setPanel(this);
        btsRightMC.setPanel(this);

        leftBTSPanel = new JPanel();
        leftBTSPanel.setLayout(new BoxLayout(leftBTSPanel, BoxLayout.Y_AXIS));

        JScrollPane jScrollPane1 = new JScrollPane(leftBTSPanel);
        this.add(jScrollPane1, BorderLayout.LINE_START);
        btsLeftMC.createLayer();
        BTSPanel leftbtsPanel = new BTSPanel(btsLeftMC.getLastLayer());
        btsLeftMC.getLastLayer().setPanel(leftbtsPanel);
        leftBTSPanel.add(leftbtsPanel);

        BSCsPanel = new JPanel();
        BSCsPanel.setLayout(new BoxLayout(BSCsPanel, BoxLayout.X_AXIS));

        BSCPanel.add(new JPanel());
        BSCPanel.get(0).setLayout(new BoxLayout(BSCPanel.get(0), BoxLayout.Y_AXIS));

        JScrollPane jScrollPane2 = new JScrollPane(BSCPanel.get(0));
        BSCsPanel.add(jScrollPane2);
        this.add(BSCsPanel, BorderLayout.CENTER);
        bscMC.createLayer();
        BSCPanel bscPanel = new BSCPanel(bscMC.getLastLayer());
        BSCPanel.get(0).add(bscPanel);

        rightBTSPanel = new JPanel();
        rightBTSPanel.setLayout(new BoxLayout(rightBTSPanel, BoxLayout.Y_AXIS));

        JScrollPane jScrollPane3 = new JScrollPane(rightBTSPanel);
        this.add(jScrollPane3, BorderLayout.LINE_END);
        btsRightMC.createLayer();
        BTSPanel rightbtsPanel = new BTSPanel(btsRightMC.getLastLayer());
        rightBTSPanel.add(rightbtsPanel);

        JPanel AddRemoveBSCPanel = new JPanel();
        AddRemoveBSCPanel.setLayout(new FlowLayout());
        JButton addButton = new JButton("Add BSC");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                es.addNewBCSLayer();
                revalidate();
                repaint();
            }
        });
        JButton removeButton = new JButton("Remove BSC");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                es.removeBSCLayer();
                revalidate();
                repaint();
            }
        });
        AddRemoveBSCPanel.add(addButton);
        AddRemoveBSCPanel.add(removeButton);

        this.add(AddRemoveBSCPanel, BorderLayout.PAGE_END);
    }

    @Override
    public void refresh() {
        this.revalidate();
        this.repaint();
    }

    @Override
    public void addBSC(LayerControl layerControl) {
        BSCPanel bscPanel = new BSCPanel(layerControl);
        BSCPanel.get(layerControl.getLayer()).add(bscPanel);
    }

    @Override
    public void addLeftBTS(LayerControl layerControl){
        BTSPanel btsPanel = new BTSPanel(layerControl);
        leftBTSPanel.add(btsPanel);
    }

    @Override
    public void addRightBTS(LayerControl layerControl){
        BTSPanel btsPanel = new BTSPanel(layerControl);
        rightBTSPanel.add(btsPanel);
    }

    @Override
    public void addNewBSCLayer(Addable es, LayerManagerControl bscMC) {
        bscMC.setPanel(this);
        BSCPanel.add(new JPanel());
        BSCPanel.get(BSCPanel.size()-1).setLayout(new BoxLayout(BSCPanel.get(BSCPanel.size()-1), BoxLayout.Y_AXIS));

        JScrollPane jScrollPane = new JScrollPane(BSCPanel.get(BSCPanel.size()-1));
        BSCsPanel.add(jScrollPane);
        bscMC.createLayer();
        BSCPanel bscPanel = new BSCPanel(bscMC.getLastLayer());
        BSCPanel.get(BSCPanel.size()-1).add(bscPanel);
        revalidate();
        repaint();
    }

    @Override
    public void removeBSCLayer() {
        BSCsPanel.remove(BSCPanel.size()-1);
        BSCPanel.remove(BSCPanel.size()-1);
        revalidate();
        repaint();
    }
}

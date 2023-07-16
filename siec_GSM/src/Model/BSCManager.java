package Model;

import Control.*;

import java.util.ArrayList;
import java.util.List;

public class BSCManager implements LayerManagerControl, Addable {
    private static List<BSCManager> instance;
    private List<BSC> bscList;
    private RefreshMid panel;
    private int index;

    private BSCManager(int index) {
        bscList = new ArrayList<>();
        this.index = index;
    }

    public static BSCManager getInstance(int index) {
        if (instance == null) {
            instance = new ArrayList<>();
            instance.add(new BSCManager(index));
        }
        return instance.get(index);
    }

    public void receiveSMS(SMS sms) {
        int minPendingSMS = Integer.MAX_VALUE;
        BSC selectedBSC = null;

        for (BSC bsc : bscList)
            if (bsc.getPendingSMS() < minPendingSMS) {
                minPendingSMS = bsc.getPendingSMS();
                selectedBSC = bsc;
            }

        if (selectedBSC == null || minPendingSMS >= 5) {
            selectedBSC = new BSC(index);
            bscList.add(selectedBSC);
            panel.addBSC(selectedBSC);
            panel.refresh();
        }

        selectedBSC.receiveSMS(sms);
    }

    public List<BSC> getBscList(){
        return bscList;
    }

    public void createLayer(){
        BSC bsc = new BSC(index);
        bscList.add(bsc);
    }

    @Override
    public LayerControl getLastLayer() {
        return bscList.get(bscList.size()-1);
    }

    @Override
    public void setPanel(RefreshMid panel) {
        this.panel = panel;
    }

    public void addNewBCSLayer(){
        instance.add(new BSCManager(instance.size()));
        panel.addNewBSCLayer(instance.get(instance.size()-1), instance.get(instance.size()-1));
        panel.refresh();
    }

    @Override
    public void removeBSCLayer() {
        if(instance.size() <= 1)
            try{
                throw new Exception("You can't remove this BSC.");
            }catch (Exception e){
                e.printStackTrace();
            }
        else{
            for(BSC bsc : instance.get(instance.size()-1).getBscList())
                for(SMS sms : bsc.getSmsList())
                    BTSRightManager.getInstance().receiveSMS(sms);
            instance.remove(instance.size()-1);
            panel.removeBSCLayer();
        }
        panel.refresh();
    }

    public static int getNumberOfLayers(){
        return instance.size();
    }
}

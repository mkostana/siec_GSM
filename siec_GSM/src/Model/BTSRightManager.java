package Model;
import Control.LayerControl;
import Control.LayerManagerControl;
import Control.Refresh;
import Control.RefreshMid;

import java.util.ArrayList;
import java.util.List;

public class BTSRightManager implements LayerManagerControl {
    private static BTSRightManager instance;
    private List<BTSRight> btsRightList;
    private RefreshMid panel;

    private BTSRightManager(){
        btsRightList = new ArrayList<>();
    }

    public static BTSRightManager getInstance(){
        if(instance == null){
            instance = new BTSRightManager();
        }
        return instance;
    }

    public void receiveSMS(SMS sms){
        int minPendingSMS = Integer.MAX_VALUE;
        BTSRight selectedBTS = null;

        for(BTSRight bts : btsRightList)
            if(bts.getPendingSMS() < minPendingSMS){
                minPendingSMS = bts.getPendingSMS();
                selectedBTS = bts;
            }

        if(selectedBTS == null || minPendingSMS >=5){
            selectedBTS = new BTSRight();
            btsRightList.add(selectedBTS);
            panel.addRightBTS(selectedBTS);
            panel.refresh();
        }

        selectedBTS.receiveSMS(sms);
    }

    public List<BTSRight> getBtsList(){
        return btsRightList;
    }

    public void createLayer(){
        BTSRight btsRight = new BTSRight();
        btsRightList.add(btsRight);
    }

    @Override
    public LayerControl getLastLayer() {
        return btsRightList.get(btsRightList.size()-1);
    }

    @Override
    public void setPanel(RefreshMid panel) {
        this.panel = panel;
    }
}

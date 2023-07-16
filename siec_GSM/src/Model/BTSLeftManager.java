package Model;
import Control.LayerControl;
import Control.LayerManagerControl;
import Control.Refresh;
import Control.RefreshMid;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BTSLeftManager implements LayerManagerControl {
    private static BTSLeftManager instance;
    private List<BTSLeft> btsLeftList;
    private RefreshMid panel;

    private BTSLeftManager(){
        btsLeftList = new ArrayList<>();
    }

    public static BTSLeftManager getInstance(){
        if(instance == null){
            instance = new BTSLeftManager();
        }
        return instance;
    }

    public void receiveSMS(SMS sms){
        int minPendingSMS = Integer.MAX_VALUE;
        BTSLeft selectedBTS = null;

        for(BTSLeft bts : btsLeftList)
            if(bts.getPendingSMS() < minPendingSMS){
                minPendingSMS = bts.getPendingSMS();
                selectedBTS = bts;
            }

        if(selectedBTS == null || minPendingSMS >=5){
            selectedBTS = new BTSLeft();
            btsLeftList.add(selectedBTS);
            panel.addLeftBTS(selectedBTS);
            panel.refresh();
        }

        selectedBTS.receiveSMS(sms);
    }

    public List<BTSLeft> getBtsList(){
        return btsLeftList;
    }

    public void createLayer(){
        BTSLeft btsLeft = new BTSLeft();
        btsLeftList.add(btsLeft);
    }

    @Override
    public LayerControl getLastLayer() {
        return btsLeftList.get(btsLeftList.size()-1);
    }

    @Override
    public void setPanel(RefreshMid panel) {
        this.panel = panel;
    }
}

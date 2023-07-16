package Model;
import Control.VBDManagerControl;

import java.util.ArrayList;
import java.util.List;

public class VBDManager implements VBDManagerControl {
    private static VBDManager instance;
    private List<VBD> vbdList;

    private VBDManager(){
        vbdList = new ArrayList<>();
    }

    public static VBDManager getInstance(){
        if(instance == null)
            instance = new VBDManager();
        return instance;
    }

    @Override
    public void createVBD(String message){
        VBD vbd = new VBD(message);
        vbdList.add(vbd);
    }

    @Override
    public VBD getLastVBD() {
        return vbdList.get(vbdList.size()-1);
    }

    public void addVBD(VBD vbd){
        vbdList.add(vbd);
    }

    public List<VBD> getVbdList(){
        return vbdList;
    }

    public void wakeUpActiveVBD(){
        for(VBD val : vbdList)
            if(val.getStatus() == Status.ACTIVE)
                val.wakeUp();
    }
}

package Model;
import Control.VRDControl;
import Control.VRDManagerControl;

import java.util.ArrayList;
import java.util.List;

public class VRDManager implements VRDManagerControl {
    private static VRDManager instance;
    private List<VRD> vrdList;

    private VRDManager(){vrdList = new ArrayList<>();}

    public static VRDManager getInstance(){
        if(instance == null)
            instance = new VRDManager();
        return instance;
    }

    @Override
    public void createVRD(){
        VRD vrd = new VRD();
        vrdList.add(vrd);
        if(vrdList.size() == 1)
            VBDManager.getInstance().wakeUpActiveVBD();
    }

    @Override
    public VRDControl getLastVRD() {
        return vrdList.get(vrdList.size()-1);
    }

    public void removeVRD(VRD vrd){
        vrdList.remove(vrd);
    }

    public int getSize(){
        return vrdList.size();
    }

    public VRD getRandomVRD(){
        if(vrdList.size() == 0)
            return null;
        else{
            int index = (int)(Math.random()*vrdList.size());
            return vrdList.get(index);
        }
    }

    public VRD getVRD(int index){
        return vrdList.get(index);
    }

    public List<VRD> getVrdList(){
        return vrdList;
    }
}

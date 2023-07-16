package Model;

import Control.Refresh;
import Control.VRDControl;
import Control.VRDManagerControl;

public class VRD extends Thread implements VRDControl {
    private int number;
    private int messagesReceived;
    private boolean messagesRemove;
    private Refresh panel;

    public VRD(){
        number = generateNumber();
        messagesReceived = 0;
        messagesRemove = false;
        start();
    }

    @Override
    public void run(){
        while(true){
            try{
                sleep(10000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            if(messagesRemove)
                messagesReceived=0;
            panel.refresh();
        }
    }

    public void receiveSMS(SMS sms){
        messagesReceived++;
        panel.refresh();
    }

    private int generateNumber(){
        boolean isUniqNumber;
        int generatedNumber;
        do{
            isUniqNumber = true;
            generatedNumber = (int)(1000+Math.random()*9000);
            for(VRD val : VRDManager.getInstance().getVrdList())
                if (generatedNumber == val.getNumber()) {
                    isUniqNumber = false;
                    break;
                }
        } while(!isUniqNumber);
        return generatedNumber;
    }

    @Override
    public int getMessagesReceived(){return messagesReceived;}

    @Override
    public int getNumber(){return number;}

    @Override
    public void setMessagesRemove(boolean val){
        this.messagesRemove = val;
        panel.refresh();
    }

    @Override
    public void remove() {
        VRDManager.getInstance().removeVRD(this);
        this.number = 0;
    }

    @Override
    public void setPanel(Refresh panel) {
        this.panel = panel;
    }
}

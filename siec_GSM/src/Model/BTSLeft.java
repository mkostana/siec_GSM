package Model;
import Control.LayerControl;
import Control.Refresh;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BTSLeft extends Thread implements LayerControl {
    private int number;
    private int pendingSMS;
    private int processedSMS;
    private List<SMS> smsList;
    private static int counter=0;
    private Refresh panel;

    public BTSLeft(){
        this.number = counter++;
        this.pendingSMS = 0;
        this.processedSMS = 0;
        smsList = new ArrayList<>();
        start();
    }

    @Override
    public void run(){
        while(true){
            if(smsList.size() == 0){
                try{
                    synchronized (this) {
                        wait();
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

            try{
                sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            SMS sms = smsList.get(0);

            BSCManager.getInstance(0).receiveSMS(sms);

            smsList.remove(sms);

            pendingSMS--;
            processedSMS++;
            panel.refresh();
        }
    }

    public void receiveSMS(SMS sms){
        smsList.add(sms);
        pendingSMS++;
        panel.refresh();
        synchronized (this) {
            notify();
        }
    }

    public int getPendingSMS(){
        return pendingSMS;
    }

    @Override
    public void setPanel(Refresh panel) {
        this.panel = panel;
    }

    @Override
    public int getLayer() {
        return 0;
    }

    public int getNumber(){return number;}

    public int getProcessedSMS(){return processedSMS;}
}

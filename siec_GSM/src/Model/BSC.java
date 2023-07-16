package Model;

import Control.LayerControl;
import Control.Refresh;

import java.util.ArrayList;
import java.util.List;

public class BSC extends Thread implements LayerControl {
    private int number;
    private int pendingSMS;
    private int processedSMS;
    private List<SMS> smsList;
    private static List<Integer> counter = new ArrayList<>();
    private Refresh panel;
    private int layer;

    public BSC(int layer){
        if(layer > counter.size()-1)
            counter.add(0);
        this.number = counter.get(layer);
        counter.set(layer, counter.get(layer)+1);
        this.pendingSMS = 0;
        this.processedSMS = 0;
        this.smsList = new ArrayList<>();
        this.layer = layer;
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
                int processingTime = (int)(Math.random()*11) + 5;
                processingTime *= 1000;
                sleep(processingTime);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            SMS sms = smsList.get(0);

            if(layer == BSCManager.getNumberOfLayers()-1)
                BTSRightManager.getInstance().receiveSMS(sms);
            else
                BSCManager.getInstance(layer+1).receiveSMS(sms);

            smsList.remove(sms);
            pendingSMS--;
            processedSMS++;

            panel.refresh();
        }
    }

    public void receiveSMS(SMS sms) {
        smsList.add(sms);
        pendingSMS++;
        panel.refresh();
        synchronized (this) {
            notify();
        }
    }

    @Override
    public int getPendingSMS(){
        return pendingSMS;
    }

    @Override
    public void setPanel(Refresh panel) {
        this.panel = panel;
    }

    @Override
    public int getProcessedSMS(){
        return processedSMS;
    }

    @Override
    public int getNumber(){
        return number;
    }

    public int getLayer(){return layer;}

    public List<SMS> getSmsList(){
        return smsList;
    }
}

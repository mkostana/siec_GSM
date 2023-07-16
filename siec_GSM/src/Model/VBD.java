package Model;
import Control.Refresh;
import Control.VBDControl;

import java.util.Random;

public class VBD extends Thread implements VBDControl {
    private int number;
    private int frequency;
    private Status status;
    private String message;
    private int messagesSent;
    private boolean exist;

    public VBD(String message){
        this.exist = true;
        this.number = generateNumber();
        this.frequency = 5;
        this.status = Status.ACTIVE;
        this.message = message;
        this.messagesSent = 0;
        start();
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
    public void run(){
        while(exist){
            if(status == Status.WAITING){
                try {
                    synchronized (this){
                        wait();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            try{
                int interval = frequency*1000;
                sleep(interval);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            sendSMS();
        }
    }



    private void sendSMS(){
        VRDManager vrdManager = VRDManager.getInstance();
        VRD receiver = vrdManager.getRandomVRD();
        if(receiver == null) {
            try {
                synchronized (this) {
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            receiver = vrdManager.getRandomVRD();
        }
        SMS sms = new SMS(message, this, receiver.getNumber());
        BTSLeftManager.getInstance().receiveSMS(sms);
        messagesSent++;
    }

    public void stopVBD(){
        status = Status.WAITING;
    }

    public void startVBD(){
        status = Status.ACTIVE;
        synchronized (this) {
            notify();
        }
    }

    @Override
    public int getNumber(){return  number;}

    @Override
    public int getMessagesSent(){return messagesSent;}

    @Override
    public int getFrequency(){
        return frequency;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public void setFrequency(int value) {
        this.frequency = value;
    }

    @Override
    public void setStatus(Status status) {
        if(status == Status.ACTIVE)
            synchronized (this) {
                notify();
            }
        this.status = status;
    }

    @Override
    public void remove() {
        this.exist = false;
    }

    public void wakeUp(){
        synchronized (this){
            notify();
        }
    }
}

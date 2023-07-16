package Control;

import Model.Status;

public interface VBDControl {

    int getNumber();
    int getMessagesSent();
    int getFrequency();
    Status getStatus();
    void setFrequency(int value);
    void setStatus(Status status);
    void remove();
}

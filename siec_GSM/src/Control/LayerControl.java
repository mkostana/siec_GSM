package Control;

public interface LayerControl {
    int getNumber();
    int getProcessedSMS();
    int getPendingSMS();
    void setPanel(Refresh panel);
    int getLayer();
}

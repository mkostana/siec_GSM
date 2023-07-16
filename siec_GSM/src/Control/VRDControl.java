package Control;

public interface VRDControl {
    int getNumber();
    int getMessagesReceived();
    void setMessagesRemove(boolean val);
    void remove();
    void setPanel(Refresh panel);
}

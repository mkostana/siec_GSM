package Model;
public class SMS {
    private String message;
    private VBD sender;
    private int receiverNumber;

    public SMS(String message, VBD sender, int receiverNumber){
        this.message = message;
        this.sender = sender;
        this.receiverNumber = receiverNumber;
    }

    public int getReceiverNumber(){
        return receiverNumber;
    }
    public String getMessage(){return message;}
    public VBD getSender(){return sender;}
}

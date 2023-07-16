package Model;

public class NumberNotFoundException extends Exception{
    public NumberNotFoundException(){
        super("VRD with this number does not exist.");
    }
}

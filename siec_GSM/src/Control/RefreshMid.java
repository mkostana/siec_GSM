package Control;

public interface RefreshMid {
    void refresh();
    void addBSC(LayerControl layerControl);
    void addLeftBTS(LayerControl layerControl);
    void addRightBTS(LayerControl layerControl);
    void addNewBSCLayer(Addable es, LayerManagerControl bscMC);
    void removeBSCLayer();
}

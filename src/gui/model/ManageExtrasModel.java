package gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ManageExtrasModel {

    private ObservableList<String> observableExtras;

    public ManageExtrasModel(){
        observableExtras = FXCollections.observableArrayList();
    }

    public void addExtra(String string){
        observableExtras.add(string);
    }

    public void removeExtra(String string){
        observableExtras.remove(string);
    }

    public ObservableList<String> getObservableExtras() {
        return observableExtras;
    }
}

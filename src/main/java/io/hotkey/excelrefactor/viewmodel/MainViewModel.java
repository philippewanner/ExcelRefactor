package io.hotkey.excelrefactor.viewmodel;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MainViewModel implements ViewModel{

    private final StringProperty logText = new SimpleStringProperty("-- Begin");
    private final DoubleProperty progress = new SimpleDoubleProperty(0.0);
    private final StringProperty searchText = new SimpleStringProperty();
    private final StringProperty replaceText = new SimpleStringProperty();
    private final StringProperty sheetnameFilter = new SimpleStringProperty();

    public StringProperty logText() {
        return logText;
    }

    public void addLogText(String message) {
        logText.set(logText.get().concat("\n").concat(message));
    }

    public DoubleProperty progress() {
        return progress;
    }

    public StringProperty searchText() { return searchText;}

    public StringProperty replaceText() { return replaceText;}

    public StringProperty sheetnameFilter() { return sheetnameFilter;}
}

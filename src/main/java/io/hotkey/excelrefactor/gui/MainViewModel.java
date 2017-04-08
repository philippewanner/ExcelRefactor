package io.hotkey.excelrefactor.gui;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by philippewanner on 08.04.17.
 */
public class MainViewModel implements ViewModel{

    private final StringProperty logText = new SimpleStringProperty("-- Begin");
    private final DoubleProperty progress = new SimpleDoubleProperty(0.0);

    public StringProperty logText() {
        return logText;
    }

    public String getLogText() {
        return logText.get();
    }

    public void setLogText(String message) {
        logText.set(message);
    }

    public void addLogText(String message) {
        logText.set(logText.get().concat(message));
    }

    public DoubleProperty progress() {
        return progress;

    }
}

package io.hotkey.excelrefactor.gui.progress;

import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;

/**
 * Created by philippewanner on 08.04.17.
 */
public class ProgressView extends HBox {

    private ProgressBar progressBar;

    public ProgressView() {
        super();
        initialisePane();
    }

    private void initialisePane() {

        progressBar = new ProgressBar();
        progressBar.prefWidthProperty().bind(this.prefWidthProperty());

        getChildren().add(progressBar);
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }
}

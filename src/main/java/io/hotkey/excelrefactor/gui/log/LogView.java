package io.hotkey.excelrefactor.gui.log;

import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

/**
 * Created by philippewanner on 08.04.17.
 */
public class LogView extends HBox {

    private TextArea textArea;

    public LogView() {

        super();
        initialiseView();
    }

    private void initialiseView() {

        textArea = new TextArea();
        textArea.prefWidthProperty().bind(prefWidthProperty());
        textArea.prefHeightProperty().bind(prefHeightProperty());

        getChildren().add(textArea);
    }
}

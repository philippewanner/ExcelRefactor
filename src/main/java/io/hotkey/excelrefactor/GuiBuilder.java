package io.hotkey.excelrefactor;


import io.hotkey.excelrefactor.gui.MainPane;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Created by philippewanner on 07.04.17.
 */
public class GuiBuilder {

    public static Pane buildRootPane() {

        VBox root = new VBox();
        root.setStyle("-fx-background-color: #555555;");
        root.setPadding(new Insets(10));
        root.setSpacing(8);
        root.minHeight(350);
        root.minWidth(1100);

        return root;
    }

    public static Pane buildMainPane() {
        return new MainPane();
    }

    public static Pane buildProgressPane() {
        return new HBox();
    }

    public static Pane buildLogPane() {
        return new HBox();
    }


}

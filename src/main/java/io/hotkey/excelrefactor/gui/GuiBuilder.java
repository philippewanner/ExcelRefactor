package io.hotkey.excelrefactor.gui;


import io.hotkey.excelrefactor.gui.log.LogView;
import io.hotkey.excelrefactor.gui.main.MainView;
import io.hotkey.excelrefactor.gui.progress.ProgressView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by philippewanner on 07.04.17.
 */
public class GuiBuilder {

    public static Pane buildRootPane(Stage stage) {

        VBox root = new VBox();
        root.setStyle("-fx-background-color: #555555;");
        //root.setPadding(new Insets(10));
        //root.setSpacing(8);
        root.minHeight(350);
        root.minWidth(1200);

        return root;
    }

    public static HBox buildMainPane(Pane rootPane) {

        MainView mainView = new MainView();
        mainView.setStyle("-fx-background-color: #777777;");
        mainView.setPadding(new Insets(8));
        mainView.prefWidthProperty().bind(rootPane.widthProperty());
        mainView.setSpacing(8);
        mainView.setAlignment(Pos.CENTER);
        return mainView;
    }

    public static HBox buildProgressPane(Pane rootPane) {

        ProgressView progressView = new ProgressView();
        progressView.prefWidthProperty().bind(rootPane.widthProperty());
        progressView.setStyle("-fx-background-color: beige");
        progressView.setAlignment(Pos.CENTER);
        return progressView;
    }

    public static HBox buildLogPane(Pane rootPane) {

        LogView logView = new LogView();
        logView.prefWidthProperty().bind(rootPane.widthProperty());
        logView.setAlignment(Pos.CENTER);
        return logView;
    }


}

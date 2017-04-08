package io.hotkey.excelrefactor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Pane rootPane = initialiseGui(primaryStage);

        Scene scene = new Scene(rootPane, rootPane.getPrefWidth(), rootPane.getPrefHeight());

        primaryStage.setTitle("Excel Refactor");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Pane initialiseGui(Stage stage) {

        Pane rootPane = GuiBuilder.buildRootPane();

        Pane mainPane = GuiBuilder.buildMainPane();
        rootPane.getChildren().add(mainPane);

        Pane progressPane = GuiBuilder.buildProgressPane();
        rootPane.getChildren().add(progressPane);

        Pane logPane = GuiBuilder.buildLogPane();
        rootPane.getChildren().add(logPane);

        return rootPane;
    }
}
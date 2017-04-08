package io.hotkey.excelrefactor;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import io.hotkey.excelrefactor.gui.GuiBuilder;
import io.hotkey.excelrefactor.gui.MainView;
import io.hotkey.excelrefactor.gui.MainViewModel;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Excel Refactor");

        ViewTuple<MainView, MainViewModel> viewTuple = FluentViewLoader.javaView(MainView.class).load();

        Parent root = viewTuple.getView();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

//        Pane rootPane = initialiseGui(primaryStage);
//
//        Scene scene = new Scene(rootPane, rootPane.getPrefWidth(), rootPane.getPrefHeight());
//
//        primaryStage.setTitle("Excel Refactor");
//        primaryStage.setScene(scene);
//        primaryStage.setMinWidth(1100);
//        primaryStage.setMinHeight(200);
//        primaryStage.show();
    }

    private Pane initialiseGui(Stage stage) {

        Pane rootPane = GuiBuilder.buildRootPane(stage);

        Pane mainPane = GuiBuilder.buildMainPane(rootPane);
        rootPane.getChildren().add(mainPane);

        HBox progressPane = GuiBuilder.buildProgressPane(rootPane);
        rootPane.getChildren().add(progressPane);

        Pane logPane = GuiBuilder.buildLogPane(rootPane);
        rootPane.getChildren().add(logPane);

        return rootPane;
    }
}
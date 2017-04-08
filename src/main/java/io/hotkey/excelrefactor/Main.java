package io.hotkey.excelrefactor;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import io.hotkey.excelrefactor.view.MainView;
import io.hotkey.excelrefactor.viewmodel.MainViewModel;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        ViewTuple<MainView, MainViewModel> viewTuple = FluentViewLoader.javaView(MainView.class).load();

        Parent root = viewTuple.getView();

        primaryStage.setTitle("Excel Refactor");
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(400);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
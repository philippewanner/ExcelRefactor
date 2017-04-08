package io.hotkey.excelrefactor.gui;

import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.JavaView;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created by philippewanner on 08.04.17.
 */
public class MainView extends VBox implements JavaView<MainViewModel>, Initializable {

    // Action pane
    private final HBox actionPane = new HBox();
    // Action pane - Dropping
    private final VBox dropPane = new VBox();
    private final Text dropText = new Text("Drop here");
    // Action pane - SearchReplace
    private final VBox searchReplacePane = new VBox();
    private final Text searchText = new Text("looking for...");
    private final TextField searchTextField = new TextField();
    private final Text replaceText = new Text("Replace by...");
    private final TextField replaceTextField = new TextField();
    // Action pane - Filter
    private final VBox filterPane = new VBox();
    private final Text sheetnameFilterText = new Text("sheet name to match");
    private final TextField sheetnameFilterTextField = new TextField();
    // Action pane - Start
    private final Button startButton = new Button("Start");
    // Progress
    private final VBox progressPane = new VBox();
    private final ProgressBar progressBar = new ProgressBar();
    // Log
    private final TextArea logArea = new TextArea();

    @InjectViewModel
    private MainViewModel viewModel;

    public MainView() {

        actionPaneGatherNodes();
        actionPaneConfigureEvents();
        actionPaneConfigureStyle();

        progressAndLogConfigureStyle();

        mainViewGatherNodes();
        mainViewConfigureStyle();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logArea.textProperty().bind(viewModel.logText());
        progressBar.progressProperty().bind(viewModel.progress());
    }

    private void progressAndLogConfigureStyle() {

        progressBar.prefWidthProperty().bind(widthProperty());

        logArea.prefHeightProperty().bind(heightProperty());
    }

    private void mainViewConfigureStyle(){
        setStyle("-fx-background-color: #777777;");
        setAlignment(Pos.CENTER);
        setPadding(new Insets(10, 10, 10, 10));
    }

    private void mainViewGatherNodes() {
        getChildren().add(actionPane);
        getChildren().add(progressBar);
        getChildren().add(logArea);
    }

    private void actionPaneConfigureEvents() {
        // Drag over the surface
        dropPane.setOnDragOver(event -> mouseDragOver(event));
        // Dropping over surface
        dropPane.setOnDragDropped(event -> mouseDragDropped(event));
        // Drag exited the surface
        dropPane.setOnDragExited(event -> mouseDragExited(event));
    }

    private void actionPaneConfigureStyle() {
        dropPane.setAlignment(Pos.CENTER);
        setDropAreaStyleAsStandard();
        dropPane.setAlignment(Pos.CENTER);
        dropPane.prefWidthProperty().bind(actionPane.widthProperty().divide(2));
        dropPane.prefWidth(400);
        dropPane.prefHeight(400);

        searchText.setFill(Color.WHITESMOKE);
        replaceText.setFill(Color.WHITESMOKE);
        searchReplacePane.setAlignment(Pos.CENTER);
        searchReplacePane.prefWidthProperty().bind(actionPane.widthProperty().divide(4));

        sheetnameFilterText.setFill(Color.WHITESMOKE);
        filterPane.setAlignment(Pos.CENTER);
        filterPane.prefWidthProperty().bind(actionPane.widthProperty().divide(4));

        startButton.prefWidthProperty().bind(actionPane.widthProperty().divide(4));

        actionPane.setAlignment(Pos.CENTER);
        actionPane.prefWidthProperty().bind(widthProperty());
        actionPane.prefHeightProperty().bind(heightProperty());
    }

    private void actionPaneGatherNodes() {

        // Action pane - Dropping
        dropPane.getChildren().add(dropText);
        // Action pane - SearchReplace
        searchReplacePane.getChildren().add(searchText);
        searchReplacePane.getChildren().add(searchTextField);
        searchReplacePane.getChildren().add(replaceText);
        searchReplacePane.getChildren().add(replaceTextField);

        // Action pane - Filter
        filterPane.getChildren().add(sheetnameFilterText);
        filterPane.getChildren().add(sheetnameFilterTextField);

        // Action pane
        actionPane.getChildren().add(dropPane);
        addArrowImage(actionPane);
        actionPane.getChildren().add(searchReplacePane);
        addArrowImage(actionPane);
        actionPane.getChildren().add(filterPane);
        addArrowImage(actionPane);
        actionPane.getChildren().add(startButton);
    }

    private void setDropAreaStyleAsAcceptable() {
        dropPane.setStyle("-fx-border-width: 4px; -fx-border-color: #ADFF2F; -fx-border-style: dashed;");
        dropText.setText("File(s) is acceptable");
        dropText.setFill(Color.GREENYELLOW);
    }

    private void setDropAreaStyleAsInacceptable() {
        dropText.setText("File(s) is not acceptable");
        dropText.setFill(Color.CORAL);
        dropPane.setStyle("-fx-border-width: 4px; -fx-border-color: #FF7F50; -fx-border-style: dashed;");
    }

    private void setDropAreaStyleAsStandard() {
        dropPane.setStyle("-fx-border-width: 4px; -fx-border-color: white; -fx-border-style: dashed;");
        dropText.setFill(Color.WHITESMOKE);
        dropText.setText("Drop here");
        dropText.setFont(Font.font(null, FontWeight.BOLD, 24));
    }

    private void mouseDragOver(final DragEvent event) {

        Dragboard db = event.getDragboard();
        if (db.hasFiles() && isAcceptedExtension(db)) {
            event.acceptTransferModes(TransferMode.COPY);
            setDropAreaStyleAsAcceptable();
        } else {
            setDropAreaStyleAsInacceptable();
            event.consume();
        }
    }

    private void mouseDragExited(DragEvent event) {

        setDropAreaStyleAsStandard();
        event.consume();
    }

    private void mouseDragDropped(final DragEvent event) {

        Dragboard db = event.getDragboard();
        if (db.hasFiles() && isAcceptedExtension(db)) {
            List<String> filePaths = db.getFiles().stream().map(f -> f.getAbsolutePath()).collect(Collectors.toList());
            System.out.println(filePaths);
            event.setDropCompleted(true);
        } else {
            event.setDropCompleted(false);
        }
        event.consume();
    }

    private boolean isAcceptedExtension(Dragboard db) {
        return db.getFiles()
                .stream()
                .allMatch(f -> f.getName().toLowerCase().endsWith(".xlsx") || f.getName().toLowerCase().endsWith(".xls"));
    }

    private void addArrowImage(HBox hBox) {

        ImageView arrowImage0 = new ImageView(new Image("whiteArrow.png"));
        arrowImage0.setFitHeight(60);
        arrowImage0.setFitWidth(60);
        arrowImage0.setPreserveRatio(true);

        hBox.getChildren().add(arrowImage0);
    }
}
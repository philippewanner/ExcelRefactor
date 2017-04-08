package io.hotkey.excelrefactor.gui.main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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

import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by philippewanner on 07.04.17.
 */
public class MainView extends HBox {

    private HBox dropPane;
    private Text dropText;
    private VBox actionPane;
    private Text searchText;
    private TextField searchTextFied;
    private Text replaceText;
    private TextField replaceTextFied;
    private VBox filterPane;
    private Text sheetnameFilterText;
    private TextField sheetnameFilterTextField;
    private VBox buttonPane;

    public MainView() {
        super();
        initialisePane();
    }

    public String getDropText() {
        return dropText.getText();
    }

    private void initialisePane() {

        addDropPane();
        addArrowImage();
        addAction();
        addArrowImage();
        addFilterPane();
        addArrowImage();
        addButtonPane();
    }

    private void addArrowImage() {

        ImageView arrowImage0 = new ImageView(new Image("whiteArrow.png"));
        arrowImage0.setFitHeight(60);
        arrowImage0.setFitWidth(60);
        arrowImage0.setPreserveRatio(true);

        getChildren().add(arrowImage0);
    }

    private void addDropPane() {

        dropPane = new HBox();

        dropPane.minHeight(200);
        dropPane.setPrefHeight(400);
        dropPane.setMaxHeight(400);

        dropPane.minWidth(100);
        dropPane.setPrefWidth(400);
        dropPane.setMaxWidth(Double.MAX_VALUE);

        // Drag over the surface
        dropPane.setOnDragOver(event -> mouseDragOver(event));
        // Dropping over surface
        dropPane.setOnDragDropped(event -> mouseDragDropped(event));
        // Drag exited the surface
        dropPane.setOnDragExited(event -> mouseDragExited(event));

        dropPane.setStyle("-fx-border-width: 4px; -fx-border-color: #ffffff; -fx-border-style: dashed;");
        dropPane.setAlignment(Pos.CENTER);

        dropText = new Text();
        dropText.setText("Drop here");
        dropText.setFill(Color.WHITESMOKE);
        dropText.setFont(Font.font(null, FontWeight.BOLD, 24));

        dropPane.getChildren().add(dropText);

        getChildren().add(dropPane);
    }


    private void addAction() {

        actionPane = new VBox();
        actionPane.minHeight(200);
        actionPane.setPrefHeight(400);
        actionPane.setMaxHeight(400);
        actionPane.minWidth(200);
        actionPane.setPrefWidth(200);
        actionPane.setMaxWidth(200);
        actionPane.setAlignment(Pos.CENTER_LEFT);

        searchText = new Text("Search");
        searchText.setFill(Color.WHITESMOKE);
        searchText.setFont(Font.font(null, FontWeight.NORMAL, 14));

        searchTextFied = new TextField();

        replaceText = new Text("Replace with");
        replaceText.setFill(Color.WHITESMOKE);
        replaceText.setFont(Font.font(null, FontWeight.NORMAL, 14));

        replaceTextFied = new TextField();

        actionPane.getChildren().add(searchText);
        actionPane.getChildren().add(searchTextFied);
        actionPane.getChildren().add(replaceText);
        actionPane.getChildren().add(replaceTextFied);
        getChildren().add(actionPane);
    }

    private void addFilterPane() {

        filterPane = new VBox();

        filterPane.minHeight(200);
        filterPane.setPrefHeight(400);
        filterPane.setMaxHeight(400);

        filterPane.minWidth(150);
        filterPane.setPrefWidth(150);
        filterPane.setMaxWidth(150);

        filterPane.setAlignment(Pos.CENTER_LEFT);

        sheetnameFilterText = new Text("Filter by sheet name");
        sheetnameFilterText.setFill(Color.WHITESMOKE);
        sheetnameFilterText.setFont(Font.font(null, FontWeight.NORMAL, 14));

        sheetnameFilterTextField = new TextField();

        filterPane.getChildren().add(sheetnameFilterText);
        filterPane.getChildren().add(sheetnameFilterTextField);
        getChildren().add(filterPane);

    }

    private void addButtonPane() {

        buttonPane = new VBox();

        buttonPane.minHeight(200);
        buttonPane.prefHeight(400);
        buttonPane.maxHeight(400);

        buttonPane.minWidth(200);
        buttonPane.prefWidth(200);
        buttonPane.maxWidth(200);

        buttonPane.setAlignment(Pos.CENTER);

        Button startButton = new Button();
        startButton.setText("Start");

        buttonPane.getChildren().add(startButton);
        getChildren().add(buttonPane);
    }

    private void mouseDragOver(final DragEvent event) {

        Dragboard db = event.getDragboard();
        if (db.hasFiles() && isAcceptedExtension(db)) {
            event.acceptTransferModes(TransferMode.COPY);
            dropPane.setStyle("-fx-border-width: 4px; -fx-border-color: green; -fx-border-style: dashed;");
            dropText.setText("File(s) is acceptable");
            dropText.setFill(Color.GREEN);
        } else {
            dropText.setText("File(s) is not acceptable");
            dropText.setFill(Color.CRIMSON);
            dropPane.setStyle("-fx-border-width: 4px; -fx-border-color: crimson; -fx-border-style: dashed;");
            event.consume();
        }
    }

    private void mouseDragExited(DragEvent event) {

        dropPane.setStyle("-fx-border-width: 4px; -fx-border-color: white; -fx-border-style: dashed;");
        dropText.setFill(Color.WHITESMOKE);
        dropText.setText("Drop here");
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
}

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import components.yumebuttons;

public class DiaryReadScene {

    public static Scene createScene(Stage stage) {

        TextArea entryDisplay = new TextArea();
        entryDisplay.setEditable(false);
        entryDisplay.setWrapText(true);
        entryDisplay.getStyleClass().add("entry-display");

        Label title = new Label("「 Past Entries 」");
        title.getStyleClass().add("diary-header");

        HBox titleBox = new HBox(title);
        titleBox.setAlignment(Pos.CENTER);

        Label noEntries = new Label("NO ENTRIES FOUND");
        noEntries.getStyleClass().add("empty-diary");

        ListView<File> entryList = new ListView<>();
        entryList.setPrefWidth(250);
        entryList.setMinWidth(250);

        File folder = new File("Diary");
        File[] files = folder.listFiles();

        if (files != null && files.length > 0) {

            for (File file : files) {
                entryList.getItems().add(file);
            }

            entryList.setCellFactory(list -> new ListCell<>() {

                @Override
                protected void updateItem(File file, boolean empty) {

                    super.updateItem(file, empty);

                    if (empty || file == null) {
                        setText(null);
                    } else {
                        setText(file.getName());
                    }
                }
            });

            entryList.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldFile, selectedFile) -> {

                    if (selectedFile == null)
                        return;

                    try (Scanner reader = new Scanner(selectedFile)) {

                        StringBuilder content = new StringBuilder();

                        while (reader.hasNextLine()) {
                            content.append(reader.nextLine()).append("\n");
                        }

                        entryDisplay.setText(content.toString());

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });

            entryList.getSelectionModel().selectFirst();
        }

        yumebuttons backButton = new yumebuttons("Back");

        backButton.setOnAction(e ->
                stage.setScene(HomeScene.createScene(stage)));

        HBox bottomBox = new HBox(backButton);
        bottomBox.setAlignment(Pos.CENTER_RIGHT);

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(35));
        VBox.setVgrow(layout, Priority.ALWAYS);

        if (files == null || files.length == 0) {

            layout.setAlignment(Pos.CENTER);
            layout.getChildren().addAll(
                    titleBox,
                    noEntries,
                    bottomBox
            );

        } else {

            HBox contentBox = new HBox(20, entryList, entryDisplay);

            HBox.setHgrow(entryDisplay, Priority.ALWAYS);
            VBox.setVgrow(contentBox, Priority.ALWAYS);

            layout.getChildren().addAll(
                    titleBox,
                    contentBox,
                    bottomBox
            );
        }

        StackPane card = new StackPane(layout);
        card.getStyleClass().add("main-card");

        card.setPrefSize(850, 550);
        card.setMinSize(850, 550);
        card.setMaxSize(850, 550);

        StackPane root = new StackPane(card);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 1200, 800);

        scene.getStylesheets().add(
                new File("resources/css/style.css")
                        .toURI()
                        .toString());

        return scene;
    }
}
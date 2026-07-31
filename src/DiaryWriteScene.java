import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import components.yumebuttons;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DiaryWriteScene {

    public static Scene createScene(Stage stage) {

        // -------------------------------
        // Background diary page
        // -------------------------------

        ImageView diaryImage = new ImageView(
                new Image(new File("resources/images/diary.png").toURI().toString()));

        diaryImage.setFitWidth(900);
        diaryImage.setPreserveRatio(true);

        // -------------------------------
        // Date
        // -------------------------------

        String today = LocalDate.now().format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Label dateLabel = new Label(today);
        dateLabel.getStyleClass().add("diary-date");

        // -------------------------------
        // Entry title
        // -------------------------------

        TextField titleField = new TextField();
        titleField.setPromptText("Dear Diary . . .");
        titleField.getStyleClass().add("diary-title");

        // -------------------------------
        // Entry text
        // -------------------------------

        TextArea writingArea = new TextArea();
        writingArea.setPromptText("Dear diary...");
        writingArea.getStyleClass().add("diary-area");

        VBox.setVgrow(writingArea, Priority.ALWAYS);

        // -------------------------------
        // Buttons
        // -------------------------------

        yumebuttons saveButton = new yumebuttons("Save ♡");
        yumebuttons backButton = new yumebuttons("Back");

        HBox buttonBox = new HBox(10, saveButton, backButton);
        buttonBox.setAlignment(Pos.CENTER);

        // -------------------------------
        // Save button
        // -------------------------------

        saveButton.setOnAction(e -> {

            File folder = new File("Diary");

            if (!folder.exists()) {
                folder.mkdir();
            }

            String entryTitle = titleField.getText();
            String entryText = writingArea.getText();

            String safeDate = today.replace("/", "-");
            String safeTitle = entryTitle.replaceAll("[\\\\/:*?\"<>|]", "_");

            String filename = "Diary/" + safeDate + "-" + safeTitle + ".txt";

            try (FileWriter writer = new FileWriter(filename)) {

                writer.write("Date: " + today + "\n");
                writer.write("Title: " + entryTitle + "\n\n");
                writer.write(entryText);

            } catch (IOException ex) {
                ex.printStackTrace();
            }

            stage.setScene(DiaryMenuScene.createScene(stage));
        });

        // -------------------------------
        // Back button
        // -------------------------------

        backButton.setOnAction(e ->
                stage.setScene(DiaryMenuScene.createScene(stage)));

        // -------------------------------
        // Diary content
        // -------------------------------

        VBox diaryContent = new VBox(18);

        diaryContent.setAlignment(Pos.TOP_CENTER);
        diaryContent.setPadding(new Insets(70, 90, 70, 90));
        diaryContent.setMaxWidth(650);

        diaryContent.getChildren().addAll(
                dateLabel,
                titleField,
                writingArea,
                buttonBox
        );

        // -------------------------------
        // Root
        // -------------------------------

        StackPane root = new StackPane();
        root.getChildren().addAll(diaryImage, diaryContent);

        // -------------------------------
        // Scene
        // -------------------------------

        Scene scene = new Scene(root, 1100, 750);

        scene.getStylesheets().add(
                new File("resources/css/style.css")
                        .toURI()
                        .toString());

        return scene;
    }
}
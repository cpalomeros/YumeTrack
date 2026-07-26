import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;



public class DiaryWriteScene {

    public static Scene createScene(Stage stage) {

        //Title 
        Label title = new Label("New Entry");
        title.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");

        // Writing area
        TextArea writingArea = new TextArea();
        writingArea.setPromptText("Dear Diary...");  

        // Buttons
        Button saveButton = new Button("Save");
        Button cancelButton = new Button("Cancel");

        // Bottom area
        HBox bottomArea = new HBox(saveButton, cancelButton);
        bottomArea.setSpacing(10);

        // Configure components
        writingArea.setPrefHeight(400);

        // Button actions
        saveButton.setOnAction(e -> {
            String entryText = writingArea.getText();
            // Save the entry (you can implement the saving logic here)
            System.out.println("Entry saved: " + entryText);
            stage.setScene(DiaryMenuScene.createScene(stage)); // Go back to main scene
        });

        cancelButton.setOnAction(e -> {
            stage.setScene(DiaryMenuScene.createScene(stage)); // Go back to main scene
        });

        // Main content

        VBox layout = new VBox(20, title, writingArea, bottomArea);

        // Main layout

        layout.setPadding(new Insets(20));

        // Create scene
    Scene scene = new Scene(layout);
    return scene;
    }



}
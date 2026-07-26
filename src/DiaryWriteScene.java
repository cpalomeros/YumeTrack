import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.IOException;




public class DiaryWriteScene {

    public static Scene createScene(Stage stage) {

        //Title 

        String today = LocalDate.now().format(
             DateTimeFormatter.ofPattern("dd/MM/yyyy") );

        
        Label title = new Label(today);
        title.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");

        // Writing area
        TextField writingField = new TextField();
        writingField.setPromptText("New Entry...");

        TextArea writingArea = new TextArea();
        writingArea.setPromptText("Dear Diary...");  

        // Buttons
        Button saveButton = new Button("Save");
        Button cancelButton = new Button("Cancel");

        // Bottom area
        HBox bottomArea = new HBox(saveButton, cancelButton);
        bottomArea.setSpacing(10);

        // Configure components
        writingField.setPrefHeight(50);
        writingArea.setPrefHeight(400);

        // Button actions
        saveButton.setOnAction(e -> {

            File folder = new File("Diary");

            if (!folder.exists()) {
            folder.mkdir();
            };

            
            String entryTitle = writingField.getText();
            String safeDate = today.replaceAll("/", "-");
            String safeTitle = entryTitle.replaceAll("[\\\\/:*?\"<>|]", "_");
            String entryText = writingArea.getText();
            String filename = "Diary/" + safeDate + "-" + safeTitle + ".txt";

            
            try {
    FileWriter writer = new FileWriter(filename);
    


            writer.write("Date:" + today + "\n");
            writer.write("Title:" + entryTitle + "\n\n");
            writer.write(entryText);

            writer.close();

    writer.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }


            // Save the entry 
            System.out.println("Entry saved: " + safeTitle + entryText);


            stage.setScene(DiaryMenuScene.createScene(stage)); // Go back to main scene
        });

        cancelButton.setOnAction(e -> {
            stage.setScene(DiaryMenuScene.createScene(stage)); // Go back to main scene
        });

        // Main content

        VBox layout = new VBox(20, title, writingField, writingArea, bottomArea);

        // Main layout

        layout.setPadding(new Insets(20));

        // Create scene
    Scene scene = new Scene(layout);
    return scene;
    }



}
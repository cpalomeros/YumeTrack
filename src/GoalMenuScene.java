import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;






public class GoalMenuScene {
    
    public static Scene creatScene(Stage stage){

        // Title
        Label title = new Label("Goals");
        Label noGoals = new Label();
        title.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");

        // Goal list
        ListView<File> goalList = new ListView<>();
        

        // Goal display / information (optional)
                
        TextArea goalDisplay = new TextArea();
        goalDisplay.setEditable(false);
        goalDisplay.setWrapText(true);

        // Goal Folder
        File folder = new File("Goals");
        File[] files = folder.listFiles();

        // Load Goals
        if (files == null || files.length == 0) {

            noGoals.setText("No goals yet.");

        } else {

            // Add files to the list
            for (File file : files) {
                goalList.getItems().add(file);
            }

            // Show only the goal names
            goalList.setCellFactory(list -> new ListCell<>() {

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

        // Input field for new goals

        TextField newGoalField = new TextField();
        newGoalField.setPromptText("What's next...?");

        // Buttons

        // - Add goal
        Button addGoalButton = new Button("Add Goal");

        // - Remove goal
        Button removeGoalButton = new Button("Remove Goal");
        
        
        // - Mark complete (optional)
        Button markCompleButton = new Button("Complete");

        // - Back
        Button backButton = new Button("Back");

        // Button actions

        // - Add goal
        addGoalButton.setOnAction(e -> {
        
            if (!folder.exists()) {
                folder.mkdir();
                };

            String goalName = title.getText();

            try {
                FileWriter writer = new FileWriter(goalName);

                writer.write(goalName);
                writer.close();

             }
        catch (IOException ex) {
            ex.printStackTrace();

        }
    }
);

        // - Remove goal
        // - Mark complete
        // - Back
        backButton.setOnAction (e -> {
        Scene homeScene = HomeScene.createScene(stage);
        stage.setScene(homeScene);

        });

        // Bottom button layout

        // Main layout
        // - Add title
        // - Add goal list
        // - Add input area
        // - Add buttons







        // Create scene
        Scene scene = new Scene(layout);
        return scene;

    }
}}

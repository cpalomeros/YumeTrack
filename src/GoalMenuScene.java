import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.scene.control.DatePicker;
import components.DiaryButtons;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;






public class GoalMenuScene {
    
    public static Scene createScene(Stage stage){

        // Title
        Label title = new Label("Goals");
        Label noGoals = new Label();
          title.getStyleClass().add("diary-header");

        // Goal list
        ListView<File> goalList = new ListView<>();
        

        // Goal display / information (optional)
                
        TextArea goalDisplay = new TextArea();
        goalDisplay.setEditable(false);
        goalDisplay.setWrapText(true);

        DatePicker dueDatePicker = new DatePicker();
        dueDatePicker.setPromptText("Goal Due Date");


        

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
                goalList.refresh();
            }

            // Show only the goal names
            goalList.setCellFactory(list -> new ListCell<File>() {

                @Override
                protected void updateItem(File file, boolean empty) {

                    super.updateItem(file, empty);

                    if (empty || file == null) {
                        setText(null);
                    } else {
                        setText(file.getName().replace(".txt", ""));
                    }
                }
            });}

        // Input field for new goals

        TextField newGoalField = new TextField();
        newGoalField.setPromptText("What's next...?");
        newGoalField.getStyleClass().add("newgoal");


        TextArea descriptionField = new TextArea();
        descriptionField.setPromptText("Description");
        descriptionField.setWrapText(true);
        descriptionField.setPrefRowCount(3);
        // Buttons


        DiaryButtons addGoalButton = new DiaryButtons("Add Goal");

        DiaryButtons removeGoalButton = new DiaryButtons("Remove Goal");
        
        
        DiaryButtons markCompleteButton = new DiaryButtons("Complete");

        DiaryButtons backButton = new DiaryButtons("Back");

        // Button actions


    addGoalButton.setOnAction(e -> {

    if (!folder.exists()) {
        folder.mkdir();
    }

    String goalName = newGoalField.getText().trim();
    String description = descriptionField.getText().trim();

    if (goalName.isEmpty()) {
        return;
    }

    try {

        File file = new File(folder, goalName + ".txt");
        FileWriter writer = new FileWriter(file);

        writer.write("Goal: " + goalName + "\n\n");
        writer.write("Description:\n");
        writer.write(description + "\n\n");
        writer.write("Due Date: " + dueDatePicker.getValue() + "\n");
        writer.write("STATUS: Not Completed\n");

        writer.close();

        goalList.getItems().add(file);

        newGoalField.clear();
        descriptionField.clear();
        dueDatePicker.setValue(null);

        noGoals.setVisible(false);
        goalList.refresh();

        goalList.setCellFactory(list -> new ListCell<File>() {
            @Override
            protected void updateItem(File file, boolean empty) {
                super.updateItem(file, empty);

                if (empty || file == null) {
                    setText(null);
                } else {
                    setText(file.getName().replace(".txt", ""));
                }
            }
        });

    } catch (IOException ex) {
        ex.printStackTrace();
    }

});
        // - Remove goal
        removeGoalButton.setOnAction(e -> {
            
            File selectedFile = goalList.getSelectionModel().getSelectedItem();
            
            if (selectedFile == null) { return;
            }
            selectedFile.delete();
            goalList.getItems().remove(selectedFile);}

        );

        // - Mark complete

        markCompleteButton.setOnAction(e -> {
            File selectedFile = goalList.getSelectionModel().getSelectedItem();
            if (selectedFile == null) { return;
            }
            if (selectedFile.getName().startsWith("✓")) {
                return;
            }
            File parent = selectedFile.getParentFile();
            String name = selectedFile.getName();
            File newFile = new File(parent, "✓" + name );
            selectedFile.renameTo(newFile);
            int index = goalList.getItems().indexOf(selectedFile);
            goalList.getItems().set(index, newFile);

            goalList.setCellFactory(list -> new ListCell<File>() {
                @Override protected void updateItem(File file, boolean empty) {
                    super.updateItem(file, empty);

                     if (empty || file == null) {
                    setText(null);
                    } else {
                    setText(file.getName().replace(".txt", ""));
                    }
                }
            });



        });




        // - Back
        backButton.setOnAction (e -> {
        Scene homeScene = HomeScene.createScene(stage);
        stage.setScene(homeScene);

        });

        // Bottom button layout
        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.BOTTOM_CENTER);
        buttonBox.setSpacing(10);
        buttonBox.setPadding(new Insets(10, 10, 10, 10));
        buttonBox.getChildren().addAll(removeGoalButton, markCompleteButton, addGoalButton ,backButton);
        
        HBox goalBox = new HBox();
        goalBox.setAlignment(Pos.CENTER);
        goalBox.setSpacing(10);
        goalBox.getChildren().addAll(newGoalField, dueDatePicker);
        HBox.setHgrow(newGoalField, Priority.ALWAYS);
        newGoalField.setMaxWidth(Double.MAX_VALUE);
        
        // Main layout

        VBox  mainBox = new VBox();
        mainBox.getStyleClass().add("goalcard");
        mainBox.setSpacing(10);
        mainBox.setAlignment(Pos.CENTER);
        mainBox.getChildren().addAll(title, noGoals, goalBox,descriptionField, goalList, goalDisplay);
        noGoals.setVisible(goalList.getItems().isEmpty());







    
    
        BorderPane layout = new BorderPane();
        layout.getStyleClass().add("list-view");
        layout.setCenter(mainBox);
        BorderPane.setMargin(mainBox, new Insets(10, 10, 10, 10));
        layout.setBottom(buttonBox);


        // Create scene
        Scene scene = new Scene(layout);

        
        scene.getStylesheets().add(
                new File("resources/css/style.css")
                        .toURI()
                        .toString());

        return scene;
}



}
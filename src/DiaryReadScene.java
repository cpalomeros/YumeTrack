import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;



public class DiaryReadScene {

    public static Scene createScene(Stage stage) {

        // Title
        Label title = new Label("• • •");
        Label noEntries = new Label();
        title.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");

        HBox titleBox = new HBox();
        titleBox.setAlignment(Pos.TOP_CENTER);
        titleBox.getChildren().addAll(title);

        

        // Entry list
        ListView<File> entryList = new ListView<>();

        // Entry display
        TextArea entryDisplay = new TextArea();
        entryDisplay.setEditable(false);
        entryDisplay.setWrapText(true);

        // Diary folder
        File folder = new File("Diary");
        File[] files = folder.listFiles();

        // Load diary entries
        if (files == null || files.length == 0) {

            noEntries.setText("No diary entries found.");

        } else {

            // Add files to the list
            for (File file : files) {
                entryList.getItems().add(file);
            }

            // Show only the file names
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

            // Open the selected diary entry
            entryList.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldFile, selectedFile) -> {

                    if (selectedFile != null) {

                        try {

                            Scanner reader = new Scanner(selectedFile);
                            StringBuilder content = new StringBuilder();

                            while (reader.hasNextLine()) {
                                content.append(reader.nextLine()).append("\n");
                            }

                            reader.close();

                            entryDisplay.setText(content.toString());

                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                    }

                }
            );
        }


        // Buttons

        Button backbutton = new Button("Back");


        // Bottom area
        HBox bottomBox = new HBox();
        bottomBox.setAlignment(Pos.BOTTOM_RIGHT);
        bottomBox.getChildren().add(backbutton);


        // Configure components

        // Button actions

                backbutton.setOnAction(e -> {
            Scene homeScene = HomeScene.createScene(stage);
            stage.setScene(homeScene);
        });

        // Main content


        // Main layout


        VBox layout = new VBox(10);

        if (files == null || files.length == 0) {
            layout.getChildren().add(noEntries);
        } else {
            
            layout.getChildren().addAll(titleBox);
            layout.getChildren().addAll(entryList, entryDisplay);
        }

        layout.getChildren().add(bottomBox);
        layout.setSpacing(10);
        VBox.setMargin(entryList ,  new Insets(10 , 10 , 10 , 10));
        VBox.setMargin(entryDisplay , new Insets(10 , 10 , 10 , 10));

        // Create scene
        Scene scene = new Scene(layout);
        return scene;
    }
}
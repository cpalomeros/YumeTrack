import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;


public class DiaryMenuScene {

    public static Scene createScene(Stage stage) {

        // Title
        Label title = new Label("Diary");
        title.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");


        // Buttons
        Button backbutton = new Button("Back");
        Button writebutton = new Button("Write Entry");
        Button viewbutton = new Button("Past Entries");


        // Button area
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(writebutton, viewbutton);


        // Bottom area
        HBox bottomBox = new HBox();
        bottomBox.setAlignment(Pos.BOTTOM_RIGHT);
        bottomBox.getChildren().add(backbutton);


        // Main content
        VBox diaryMenu = new VBox(20);
        diaryMenu.setAlignment(Pos.CENTER);
        diaryMenu.getChildren().addAll(title, buttonBox);



        // Button actions
        writebutton.setOnAction(e -> {
            Scene writeScene = DiaryWriteScene.createScene(stage);
            stage.setScene(writeScene);
        });

        viewbutton.setOnAction(e -> {
            Scene readScene = DiaryReadScene.createScene(stage);
            stage.setScene(readScene);
        });

        // Back button action
        backbutton.setOnAction(e -> {
            Scene homeScene = HomeScene.createScene(stage);
            stage.setScene(homeScene);
        });


        // Main layout
        BorderPane layout = new BorderPane();
        layout.setCenter(diaryMenu);
        layout.setBottom(bottomBox);


        // Create scene
        Scene scene = new Scene(layout);

        return scene;
    }
}
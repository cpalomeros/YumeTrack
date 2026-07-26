import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class DiaryMenuScene {
    public static Scene createScene(Stage stage) {
        VBox diaryMenuBox = new VBox();
        // Add components to diaryMenuBox
        Button backbutton = new Button("Back");

        diaryMenuBox.getChildren().addAll(backbutton);

        backbutton.setOnAction( e -> {
            Scene homescene = HomeScene.createScene(stage);
            stage.setScene(homescene);
        });

        
        Scene scene = new Scene(diaryMenuBox);
        return scene;
    }
}

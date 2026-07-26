import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SettingScene {
    
    public static Scene createScene(Stage stage) {
        VBox settinglayoutBox = new VBox();

        Button backbutton = new Button("Back");

        settinglayoutBox.getChildren().addAll(backbutton);

        backbutton.setOnAction( e -> {
            Scene mainmenu = MainMenuScene.createScene(stage);
            stage.setScene(mainmenu);
        });

        return new Scene(settinglayoutBox);
    }
}

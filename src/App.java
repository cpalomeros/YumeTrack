import java.io.File;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.image.Image;


public class App extends Application {


    // Override the start method to set up the main window
    @Override
    public void start(Stage stage) {

        // Window properties
        stage.setTitle("推し活 ");
        stage.setWidth(600);
        stage.setHeight(800);



        // Set the scene to the stage
        stage.setScene(MainMenuScene.createScene(stage));

        stage.getIcons().add(
        new Image(new File("resources/images/icon.png").toURI().toString())
        );

        // Show the window
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}
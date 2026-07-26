import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.stage.Stage; 

public class MainMenuScene {
    
    public static Scene createScene(Stage stage) {
        //Layout 
        // I want to make a cozy layout for the main menu, for that I will be using vBox.

        VBox mainmenusceneBox = new VBox();

        Button startButton = new Button("Start");
       
        HBox settingsBox = new HBox();
        Button settingsButton = new Button("Settings");
        Button exitButton = new Button("Exit");

        // Add buttons to the layout
        mainmenusceneBox.getChildren().addAll( startButton, settingsBox );
        settingsBox.getChildren().addAll(settingsButton, exitButton);

        mainmenusceneBox.setAlignment(javafx.geometry.Pos.CENTER);
        settingsBox.setAlignment(javafx.geometry.Pos.CENTER);
        
        mainmenusceneBox.setSpacing(10);
        settingsBox.setSpacing(10);
        //Once I have the buttons I will style them.
        

        // when the start button is pressed, the scene will change into the homeScene.


        startButton.setOnAction( e -> {
            Scene homeScene = HomeScene.createScene(stage);
            stage.setScene(homeScene);
        });



        settingsButton.setOnAction( e -> {
            Scene settingScene = SettingScene.createScene(stage);
            stage.setScene(settingScene);
        });

        exitButton.setOnAction( e -> {
            stage.close();
        });
    



        return new Scene(mainmenusceneBox);
    
    
} }


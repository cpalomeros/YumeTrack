import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.stage.Stage; 

public class HomeScene {
    
    public static Scene createScene(Stage stage) {
        

        
        VBox homelayoutBox = new VBox();

        Button diarybutton = new Button("Diary");
        Button goalbutton = new Button("Goals");
        Button oshibutton = new Button("Kanade");
        Button backbutton = new Button("Back");

        homelayoutBox.getChildren().addAll(diarybutton, goalbutton, oshibutton, backbutton);


        
                backbutton.setOnAction( e -> {
                
            Scene mainmenu = MainMenuScene.createScene(stage);
            stage.setScene(mainmenu);
        });
          
        Scene scene = new Scene(homelayoutBox);
        scene.getStylesheets().add("css/style.css");


  
        return scene;
    
    
}
}

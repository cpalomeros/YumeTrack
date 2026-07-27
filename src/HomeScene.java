import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.stage.Stage; 
import javafx.scene.layout.HBox;

public class HomeScene {
    
    public static Scene createScene(Stage stage) {
        

        
        VBox homelayoutBox = new VBox();

      homelayoutBox.setAlignment(javafx.geometry.Pos.CENTER);
 

        HBox diarygoalsBox = new HBox();
     diarygoalsBox.setAlignment(javafx.geometry.Pos.CENTER);


        Button diarybutton = new Button("Diary");
        diarybutton.setPrefSize(125, 50);

        Button goalbutton = new Button("Goals");
        goalbutton.setPrefSize(75, 50);

        Button oshibutton = new Button("Kanade");
        oshibutton.setPrefSize(200, 100);

        Button backbutton = new Button("Back");
        backbutton.setPrefSize(200, 50);

    homelayoutBox.setSpacing(10);
 
        homelayoutBox.getChildren().addAll(diarygoalsBox, oshibutton, backbutton);
        diarygoalsBox.getChildren().addAll(diarybutton, goalbutton);
        

        
                backbutton.setOnAction( e -> {
                
            Scene mainmenu = MainMenuScene.createScene(stage);
            stage.setScene(mainmenu);
        });

        diarybutton.setOnAction( e -> {
            Scene diarymenu = DiaryMenuScene.createScene(stage);
            stage.setScene(diarymenu);
        });

        

          
        Scene scene = new Scene(homelayoutBox);
        scene.getStylesheets().add("css/style.css");


  
        return scene;
    
    
}
}

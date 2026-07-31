import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage; 
import javafx.scene.layout.HBox;
import components.yumebuttons;

public class HomeScene {
    
    public static Scene createScene(Stage stage) {
        

        
        VBox homelayoutBox = new VBox();

      homelayoutBox.setAlignment(javafx.geometry.Pos.CENTER);
 

        HBox diarygoalsBox = new HBox();
     diarygoalsBox.setAlignment(javafx.geometry.Pos.CENTER);


        yumebuttons diarybutton = new yumebuttons("Diary");
        diarybutton.setPrefSize(125, 50);
        

        yumebuttons goalbutton = new yumebuttons("Goals");
        goalbutton.setPrefSize(75, 50);

        yumebuttons oshibutton = new yumebuttons("Kanade");
        oshibutton.setPrefSize(200, 100);

        yumebuttons backbutton = new yumebuttons("Back");
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

        goalbutton.setOnAction( e -> {
            Scene goalmenu = GoalMenuScene.createScene(stage);
            stage.setScene(goalmenu);
        });

        

          
        Scene scene = new Scene(homelayoutBox);
        scene.getStylesheets().add(
             new java.io.File("resources/css/style.css")
            .toURI()
            .toString()
            );


  
        return scene;
    
    
}
}

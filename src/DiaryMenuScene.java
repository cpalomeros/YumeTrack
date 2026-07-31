import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import components.yumebuttons;

public class DiaryMenuScene {

    public static Scene createScene(Stage stage) {

        // Root
        StackPane root = new StackPane();
        root.setAlignment(Pos.CENTER);

        // Card
        VBox card = new VBox(18);
        card.setAlignment(Pos.CENTER);
        card.getStyleClass().add("main-card");

        card.setPrefSize(500, 320);
        card.setMaxSize(
                Region.USE_PREF_SIZE,
                Region.USE_PREF_SIZE
        );

        // Title
        Label title = new Label("DIARY");
        title.getStyleClass().add("title");

        Label divider = new Label("───── ⋆⋅☆⋅⋆ ─────");
        divider.getStyleClass().add("subtitle");

        VBox.setMargin(divider, new Insets(0,0,15,0));

        // Buttons

        yumebuttons writeButton = new yumebuttons("Write Entry");
        writeButton.setPrefSize(170, 50);

        yumebuttons viewButton = new yumebuttons("Past Entries");
        viewButton.setPrefSize(170, 50);

        yumebuttons backButton = new yumebuttons("Back");
        backButton.setPrefWidth(355);

        HBox row = new HBox(15);
        row.setAlignment(Pos.CENTER);
        row.getChildren().addAll(writeButton, viewButton);

        card.getChildren().addAll(
                title,
                divider,
                row,
                backButton
        );

        root.getChildren().add(card);

        // Actions

        writeButton.setOnAction(e ->
                stage.setScene(DiaryWriteScene.createScene(stage)));

        viewButton.setOnAction(e ->
                stage.setScene(DiaryReadScene.createScene(stage)));

        backButton.setOnAction(e ->
                stage.setScene(HomeScene.createScene(stage)));

        // Scene

        Scene scene = new Scene(root);

        scene.getStylesheets().add(
                new java.io.File("resources/css/style.css")
                        .toURI()
                        .toString());

        return scene;
    }
}
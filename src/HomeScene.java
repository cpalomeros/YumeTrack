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

public class HomeScene {

    public static Scene createScene(Stage stage) {

        // Root
        StackPane root = new StackPane();
        root.setAlignment(Pos.CENTER);

        // Card
        VBox card = new VBox(18);
        card.setAlignment(Pos.CENTER);
        card.getStyleClass().add("main-card");

        card.setPrefSize(520, 380);
        card.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        // Title
        Label title = new Label("DASHBOARD");
        title.getStyleClass().add("title");

        Label subtitle = new Label("───── ⋆⋅☆⋅⋆ ─────");
        subtitle.getStyleClass().add("subtitle");

        VBox.setMargin(title, new Insets(0, 0, 8, 0));
        VBox.setMargin(subtitle, new Insets(0, 0, 20, 0));

        // First row
        HBox topRow = new HBox(15);
        topRow.setAlignment(Pos.CENTER);

        yumebuttons diaryButton = new yumebuttons("Diary");
        diaryButton.setPrefSize(170, 60);

        yumebuttons goalButton = new yumebuttons("Goals");
        goalButton.setPrefSize(170, 60);

        topRow.getChildren().addAll(diaryButton, goalButton);

        // Kanade
        yumebuttons kanadeButton = new yumebuttons("Oshikatsu");
        kanadeButton.setPrefSize(355, 100);

        // Back
        yumebuttons backButton = new yumebuttons("Back");
        backButton.setPrefSize(355, 45);

        card.getChildren().addAll(
                title,
                subtitle,
                topRow,
                kanadeButton,
                backButton
        );

        root.getChildren().add(card);

        // Actions

        backButton.setOnAction(e ->
                stage.setScene(MainMenuScene.createScene(stage)));

        diaryButton.setOnAction(e ->
                stage.setScene(DiaryMenuScene.createScene(stage)));

        goalButton.setOnAction(e ->
                stage.setScene(GoalMenuScene.createScene(stage)));

        // Scene

        Scene scene = new Scene(root);

        scene.getStylesheets().add(
                new java.io.File("resources/css/style.css")
                        .toURI()
                        .toString());

        return scene;
    }
}
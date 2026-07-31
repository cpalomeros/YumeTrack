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

public class MainMenuScene {

    public static Scene createScene(Stage stage) {

        // Root
        StackPane root = new StackPane();
        root.setAlignment(Pos.CENTER);

        // Card
        VBox card = new VBox(18); // spacing
        card.setAlignment(Pos.CENTER);
        card.getStyleClass().add("main-card");

        card.setPrefSize(500, 280);
        card.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        card.setSpacing(18);

        // Title
        Label title = new Label("YumeTrack");
        title.getStyleClass().add("title");

        // Decorative line
        Label subtitle = new Label("───── ⋆⋅☆⋅⋆ ─────");
        subtitle.getStyleClass().add("subtitle");

        VBox.setMargin(title, new Insets(0, 0, 8, 0));
        VBox.setMargin(subtitle, new Insets(0, 0, 15, 0));

        // Buttons
        yumebuttons startButton = new yumebuttons("Start");
        startButton.setPrefWidth(170);

        yumebuttons settingsButton = new yumebuttons("Settings");
        yumebuttons exitButton = new yumebuttons("Exit");

        HBox bottomButtons = new HBox(12);
        bottomButtons.setAlignment(Pos.CENTER);
        bottomButtons.getChildren().addAll(settingsButton, exitButton);

        // Version
        Label versiontext = new Label("v0.0.1 alpha");
        versiontext.getStyleClass().add("version");

        card.getChildren().addAll(
                title,
                subtitle,
                startButton,
                bottomButtons,
                versiontext
        );

        root.getChildren().add(card);

        // Actions
        startButton.setOnAction(e ->
                stage.setScene(HomeScene.createScene(stage)));

        settingsButton.setOnAction(e ->
                stage.setScene(SettingScene.createScene(stage)));

        exitButton.setOnAction(e ->
                stage.close());

        Scene scene = new Scene(root);
        scene.getStylesheets().add(
                new java.io.File("resources/css/style.css")
                        .toURI()
                        .toString());

        return scene;
    }
}
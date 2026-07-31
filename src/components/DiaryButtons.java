package components;

import javafx.scene.control.Button;

public class DiaryButtons extends Button {

    public DiaryButtons(String text) {

        super(text);

        getStyleClass().add("diarybuttons");

        setOnMouseEntered(e -> setTranslateY(-2));
        setOnMouseExited(e -> setTranslateY(0));

        setOnMousePressed(e -> setTranslateY(2));
        setOnMouseReleased(e ->
            setTranslateY(isHover() ? -2 : 0)
        );
    }
}
package components;


import javafx.scene.control.Button;

public class yumebuttons extends Button {

public yumebuttons(String text) {

    
    super(text);

    getStyleClass().add("yumebuttons");

    setOnMouseEntered(e -> setTranslateY(-2));
    setOnMouseExited(e -> setTranslateY(0));

    setOnMousePressed(e -> setTranslateY(2));
    setOnMouseReleased(e ->
        setTranslateY(isHover() ? -2 : 0)
    );
}}


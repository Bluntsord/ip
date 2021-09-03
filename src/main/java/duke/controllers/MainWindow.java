package duke.controllers;

import duke.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for duke.controllers.MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Kobold.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Croc.png"));
    private static String startUpText = "Oh my..... Looks like u have an old scroll..";

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        Logic.preload();
        String text = startUpText + "\n" + DataHandlerLayer.getLogAsString();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(text, dukeImage)
        );
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = null;
        try {
            String temp = Logic.checkIfSpecialComand(input);
            if (temp == null) {
                response = "There are no task my dear summoner <3";
            } else {
                response = temp;
            }
        } catch (InvalidCommandException e) {
            e.printStackTrace();
        }
        System.out.println(response);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
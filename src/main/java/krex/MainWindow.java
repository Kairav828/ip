package krex;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import krex.Krex;

public class MainWindow {

    private final Krex krex;
    private final VBox dialogContainer = new VBox(10);
    private final TextField userInput = new TextField();
    private final Button sendButton = new Button("Send");

    public MainWindow(Krex krex) {
        this.krex = krex;
    }

    public Parent getRoot() {
        sendButton.setOnAction(e -> handleInput());

        HBox inputBox = new HBox(10, userInput, sendButton);

        ScrollPane scrollPane = new ScrollPane(dialogContainer);
        scrollPane.setFitToWidth(true);

        BorderPane root = new BorderPane();
        root.setCenter(scrollPane);
        root.setBottom(inputBox);

        dialogContainer.getChildren().add(new Label("Hello! I'm Krex. What can I do for you?"));

        return root;
    }

    private void handleInput() {
        String input = userInput.getText();

        // show user input
        dialogContainer.getChildren().add(new Label("You: " + input));

        // get bot response
        String response = krex.getResponse(input);
        dialogContainer.getChildren().add(new Label("Krex: " + response));

        // clear input field
        userInput.clear();

        // disable input after bye
        if (krex.isExit()) {
            userInput.setDisable(true);
            sendButton.setDisable(true);
        }
    }

}

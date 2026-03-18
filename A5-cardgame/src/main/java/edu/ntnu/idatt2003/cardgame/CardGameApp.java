package edu.ntnu.idatt2003.cardgame;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * JavaFX application for the card game.
 * Shows a hand of 5 cards and allows the user to check hand properties.
 */
public class CardGameApp extends Application {

    private final DeckOfCards deck = new DeckOfCards();
    private HandOfCards currentHand = null;

    // Display fields
    private Label handLabel;
    private TextField sumField;
    private TextField heartsField;
    private TextField flushField;
    private TextField queenField;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Card Game");

        // --- Hand display area ---
        handLabel = new Label("Press 'Deal Hand' to start");
        handLabel.setFont(Font.font("Monospaced", FontWeight.BOLD, 16));
        handLabel.setWrapText(true);
        handLabel.setAlignment(Pos.CENTER);

        StackPane cardArea = new StackPane(handLabel);
        cardArea.setStyle("-fx-border-color: #888; -fx-border-width: 2; -fx-background-color: #e8f5e9;");
        cardArea.setPrefSize(600, 300);

        // --- Buttons ---
        Button dealButton = new Button("Deal Hand");
        dealButton.setPrefWidth(130);
        dealButton.setOnAction(e -> dealHand());

        Button checkButton = new Button("Check Hand");
        checkButton.setPrefWidth(130);
        checkButton.setOnAction(e -> checkHand());

        VBox buttonBox = new VBox(15, dealButton, checkButton);
        buttonBox.setAlignment(Pos.CENTER);

        HBox topArea = new HBox(20, cardArea, buttonBox);
        topArea.setAlignment(Pos.CENTER);

        // --- Info fields ---
        sumField   = makeReadOnlyField(8);
        heartsField = makeReadOnlyField(18);
        flushField = makeReadOnlyField(5);
        queenField = makeReadOnlyField(5);

        GridPane infoGrid = new GridPane();
        infoGrid.setHgap(10);
        infoGrid.setVgap(8);
        infoGrid.setAlignment(Pos.CENTER_LEFT);

        infoGrid.add(new Label("Sum of faces:"), 0, 0);
        infoGrid.add(sumField, 1, 0);
        infoGrid.add(new Label("Cards of hearts:"), 2, 0);
        infoGrid.add(heartsField, 3, 0);
        infoGrid.add(new Label("Flush:"), 0, 1);
        infoGrid.add(flushField, 1, 1);
        infoGrid.add(new Label("Queen of spades:"), 2, 1);
        infoGrid.add(queenField, 3, 1);

        // --- Root layout ---
        VBox root = new VBox(20, topArea, infoGrid);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        primaryStage.setScene(new Scene(root, 930, 510));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void dealHand() {
        currentHand = new HandOfCards(deck.dealHand(5));
        handLabel.setText(currentHand.getHandAsString());

        // Clear analysis fields until user presses Check Hand
        sumField.clear();
        heartsField.clear();
        flushField.clear();
        queenField.clear();
    }

    private void checkHand() {
        if (currentHand == null) {
            handLabel.setText("Deal a hand first!");
            return;
        }
        sumField.setText(String.valueOf(currentHand.sumOfFaces()));
        heartsField.setText(currentHand.heartsAsString());
        flushField.setText(currentHand.isFlush() ? "Yes" : "No");
        queenField.setText(currentHand.hasQueenOfSpades() ? "Yes" : "No");
    }

    private TextField makeReadOnlyField(int columns) {
        TextField tf = new TextField();
        tf.setPrefColumnCount(columns);
        tf.setEditable(false);
        tf.setStyle("-fx-background-color: #f5f5f5;");
        return tf;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

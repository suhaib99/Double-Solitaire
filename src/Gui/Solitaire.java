package Gui;

import apple.laf.JRSUIConstants;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import main.Board;
import main.FoundationPile;
import main.TablePile;

public class Solitaire extends Application {

    private static final int HEIGHT = 500;
    private static final int WIDTH = 680;
    private static final String TITLE = "Double-Solitaire";
    private DiscardPileView discardPileView;
    private DeckView deckView;
    private CardPileView[] cardStacks = new CardPileView[7];
    private FoundationPileView[] foundationStacks = new FoundationPileView[4];

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle(TITLE);

        GridPane root = new GridPane();

        root.setStyle("-fx-background-color: green");

        root.setVgap(10);
        root.setHgap(10);

        Board blueBoard = new Board(1);

        discardPileView = new DiscardPileView(blueBoard);
        root.add(discardPileView, 1, 0);

        deckView = new DeckView(blueBoard);
        root.add(deckView, 0, 0);

        TablePile[] tablePiles = blueBoard.getTablePiles();

        for (int i = 0; i < tablePiles.length; i++){
            cardStacks[i] = new CardPileView(tablePiles[i], blueBoard);
            root.add(cardStacks[i], i, 1);
        }

        FoundationPile[] foundationPiles = blueBoard.getFoundationPiles();

        for (int i = 0; i < foundationPiles.length; i++){
            foundationStacks[i] = new FoundationPileView(foundationPiles[i], blueBoard);
            root.add(foundationStacks[i], i+3, 0);
        }



        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.show();
    }
}

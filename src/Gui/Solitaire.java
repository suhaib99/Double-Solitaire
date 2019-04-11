/**
 *
 * Adapted from code by Martin P. Robillard
 *
 * See: https://github.com/prmr/Solitaire
 */
package Gui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.control.Button;
import main.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Solitaire extends Application {

    private static final int HEIGHT = 900;
    private static final int WIDTH = 800;
    private static final String TITLE = "Double-Solitaire";
    private DiscardPileView discardPileViewblue;
    private DiscardPileView discardPileViewred;
    private DeckView deckViewblue;
    private DeckView deckViewred;
    private CardPileView[] cardStacksblue = new CardPileView[7];
    private CardPileView[] cardStacksred = new CardPileView[7];
    private FoundationPileView[] foundationStacksblue = new FoundationPileView[4];
    private FoundationPileView[] FoundationStacksred = new FoundationPileView[4];

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle(TITLE);

        VBox Game = new VBox();

        HBox root = new HBox();

        root.setStyle("-fx-background-color: green");
        root.setSpacing(5);

        Game.setStyle("-fx-background-color: green");

        Game.setSpacing(125);

        GridPane Blue = new GridPane();
        GridPane Red = new GridPane();

        Blue.setStyle("-fx-background-color: green");

        Blue.setVgap(10);
        Blue.setHgap(10);

        Red.setStyle("-fx-background-color: green");

        Red.setHgap(10);
        Red.setVgap(10);

        Button endTurn = new Button("Next Turn");

        BufferedReader bufferedReader = new BufferedReader(new FileReader("SavedState.txt"));
        String[] blueSaves = new String[12];
        String[] redSaves = new String[12];

        for (int i = 0; i < 12; i++){
            blueSaves[i] = bufferedReader.readLine();
        }

        for (int i = 0; i < 12; i++){
            redSaves[i] = bufferedReader.readLine();
        }

        Board blueBoard = new Board(blueSaves, 1);
        Board redBoard = new Board(redSaves, 2);

        redBoard.setTurn(true);
        blueBoard.setTurn(false);

        bufferedReader.close();

        endTurn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (redBoard.getTurn()) {
                    redBoard.setTurn(false);
                    blueBoard.setTurn(true);
                } else if (blueBoard.getTurn()) {
                    blueBoard.setTurn(false);
                    redBoard.setTurn(true);
                }
            }
        });

        discardPileViewblue = new DiscardPileView(blueBoard);
        Blue.add(discardPileViewblue, 1, 0);
        discardPileViewred = new DiscardPileView(redBoard);
        Red.add(discardPileViewred, 1, 0);

        deckViewblue = new DeckView(blueBoard);
        deckViewred = new DeckView(redBoard);
        Blue.add(deckViewblue, 0, 0);
        Red.add(deckViewred, 0, 0);

        TablePile[] blueBoardTablePiles = blueBoard.getTablePiles();

        for (int i = 0; i < blueBoardTablePiles.length; i++){
            cardStacksblue[i] = new CardPileView(blueBoardTablePiles[i], blueBoard);
            Blue.add(cardStacksblue[i], i, 1);
        }

        TablePile[] redBoardTablePiles = redBoard.getTablePiles();

        for(int i = 0; i < redBoardTablePiles.length; i++){
            cardStacksred[i] = new CardPileView(redBoardTablePiles[i], redBoard);
            Red.add(cardStacksred[i], i, 1);
        }

        FoundationPile[] blueBoardFoundationPiles = blueBoard.getFoundationPiles();

        for (int i = 0; i < blueBoardFoundationPiles.length; i++){
            foundationStacksblue[i] = new FoundationPileView(blueBoardFoundationPiles[i], blueBoard);
            Blue.add(foundationStacksblue[i], i+3, 0);
        }

        FoundationPile[] redBoardFoundationPiles = redBoard.getFoundationPiles();

        for (int i = 0; i < redBoardFoundationPiles.length; i++){
            FoundationStacksred[i] = new FoundationPileView(redBoardFoundationPiles[i], redBoard);
            Red.add(FoundationStacksred[i], i+3, 0);
        }

        root.getChildren().add(Game);
        root.getChildren().add(endTurn);

        Game.getChildren().addAll(Blue, Red);

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                try{
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("SavedState.txt"));
                    CardPile[] bluePiles = blueBoard.allPiles();
                    CardPile[] redPiles = redBoard.allPiles();

                    SaveCodeState(bluePiles, bufferedWriter);
                    SaveCodeState(redPiles, bufferedWriter);
                    bufferedWriter.close();


                }catch(IOException e) {
                    System.out.println("Cannot Save");
                 }
            }
        });

        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.show();
    }

    private static void SaveCodeState(CardPile[] bluePiles, BufferedWriter bufferedWriter) throws IOException {
        for (int i = 0; i < 12; i++){
            String result = "";
            ArrayList<Card> cards = bluePiles[i].getCardList();
            for (Card card: cards){
                if (card.getFaceUp()){
                    result += "1/" + card.toString() + Transfer.SEPARATOR;
                } else {
                    result += "0/" + card.toString() + Transfer.SEPARATOR;
                }
            }
            if (result.length() > 0){
                result = result.substring(0, result.length()-1);
            }
            bufferedWriter.write(result+"\n");

        }
    }
}

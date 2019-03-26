package main;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GameSinglePlayer extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    final static int STACKVOFFSET = 17;
    final static int VOFFSET = 30;
    final static int HOFFSET = 30;
    final static int canvasWidth = 900;
    final static int canvasHeight = 500;

    private CardPile fromPile;
    private boolean holding = false;
    private ArrayList<Card> selectedCards;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        // Vertical offset between stacked cards
        Board blueBoard = new Board(1);
        Board redBoard = new Board(2);

        primaryStage.setTitle("Double Solitaire");

        Group root = new Group();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        Canvas canvas = new Canvas(canvasWidth, canvasHeight);

        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Main Game Loop
        new AnimationTimer(){     //allows to create a timer, that is called in each frame while it is active
            @Override
            public void handle(long now) {

                gc.setFill(new Color(0, 0.33, 0, 1));
                gc.fillRect(0.0, 0.0, canvasWidth, canvasHeight);

                ArrayList<Image>[] images = blueBoard.display();

                for (int i = 1; i < 5; i++){            //Display the foundation card images
                    if (images[i] != null){
                        for (Image foundationImage : images[i]){
                            gc.drawImage(foundationImage, blueBoard.getFoundationPiles()[i-1].getX(), blueBoard  // draw the card image at the right position
                                    .getFoundationPiles()[i-1].getY());

                        }
                    } else if (images[i] == null) {   //If the array is empty draw a dark blank rectangle at the spot where the card is supposed to be
                        gc.setFill(new Color(0, 0.5, 0, 1));
                        gc.fillRect(blueBoard.getFoundationPiles()[i-1].getX(), blueBoard.getFoundationPiles()[i-1]
                                .getY(), Card.WIDTH, Card.HEIGHT);
                    }
                }

                int county = 0;
                for (Image discardImages: images[0]){    //Display the discard pile
                    gc.drawImage(discardImages, blueBoard.getDiscard().getCardList()
                            .get(county).getX(), blueBoard.getDiscard().getCardList()
                            .get(county).getY());
                    county++;
                }

                for (int i = 5; i < 12; i++){
                    int counter = 0;
                    for (Image tablePile: images[i]){    //Display the table pile
                        gc.drawImage(tablePile, blueBoard.getTablePiles()[i-5].getCardList().get(counter).getX(),
                                blueBoard.getTablePiles()[i-5].getCardList().get(counter).getY());
                        counter++;
                    }
                }

                canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {  //picks up the card
                        if (blueBoard.getDiscard().contains(event.getSceneX(), event.getSceneY()) && !holding && blueBoard.
                                getDiscard().top().getFaceUp()) {
                            blueBoard.getDiscard().top().setHeld(true);
                            fromPile = blueBoard.discard;
                        }

                        if (!holding){
                            for (int i = 0; i < blueBoard.tablePiles.length; i++) {
                                if (blueBoard.getTablePiles()[i].contains(event.getSceneX(), event.getSceneY())) {
                                    for (Card card : blueBoard.getTablePiles()[i].getCardList()) {
                                        if (card.getFaceUp())
                                            card.setHeld(true);
                                    }

                                    fromPile = blueBoard.getTablePiles()[i];
                                    holding = true;
                                }
                            }
                        }

                    }
                });

                canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {

                        Card discardTop = blueBoard.getDiscard().top();

                        if (discardTop.getHeld()) {
                            if (discardTop.getFaceUp()) {

                                discardTop.setX(event.getSceneX() - Card.WIDTH / 2.0);
                                discardTop.setY(event.getSceneY() - Card.HEIGHT / 2.0);

                                holding = true;
                                selectedCards = blueBoard.discard.select();
                            }
                        }


                        for (int i = 0; i < blueBoard.tablePiles.length; i++) {
                            if (blueBoard.getTablePiles()[i].top().getHeld()) {
                                selectedCards = blueBoard.tablePiles[i].select();

                                int counter1 = 0;
                                for (Card card : selectedCards) {
                                    if (card.getFaceUp()) {
                                        card.setX(event.getSceneX() - Card.WIDTH / 2.0);
                                        card.setY(event.getSceneY() + counter1 * STACKVOFFSET - Card.HEIGHT / 2.0);
                                        counter1++;
                                    }
                                }
                            }
                        }
                    }
                });

                canvas.setOnMouseReleased(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {

                        if (holding) {
                            for (int i = 0; i < blueBoard.getTablePiles().length; i++) {
                                if (blueBoard.getTablePiles()[i].hoveringOverPile(event.getSceneX(), event.getSceneY())) {
                                    blueBoard.move(fromPile, selectedCards, blueBoard.getTablePiles()[i]);
                                    removeFromPrev();
                                    blueBoard.getTablePiles()[i].topDown();

                                    holding = false;
                                    break;
                                }
                            }

                            for (int i = 0; i < blueBoard.getFoundationPiles().length; i++){
                                if (blueBoard.getFoundationPiles()[i].contains(event.getSceneX(), event.getSceneY())) {
                                    blueBoard.move(fromPile, selectedCards, blueBoard.getFoundationPiles()[i]);
                                    removeFromPrev();
                                    blueBoard.getTablePiles()[i].topDown();
                                        
                                    holding = false;
                                    break;
                                }
                            }

                            if (holding){
                                for (Card card: selectedCards){
                                    card.setX(fromPile.getX());
                                    card.setY(fromPile.getY());
                                    card.setHeld(false);
                                    holding = false;
                                }
                            }
                        }
                    }
                });

                canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (blueBoard.getDiscard().contains(event.getSceneX(), event.getSceneY()) && !holding){
                            blueBoard.discard.flipThrough();
                        }
                    }
                });

                if (blueBoard.gameOver()){
                    clearBoard();

                    gc.setFill(new Color(0, 0, 1, 1));
                    gc.fillText("GAME OVER BLUE WINS", (double)canvasWidth/2, (double)canvasHeight/2);
                } else if (redBoard.gameOver()){
                    clearBoard();

                    gc.setFill(new Color(1, 0, 0, 1));
                    gc.fillText("GAME OVER RED WINS", (double)canvasWidth/2, (double)canvasHeight/2);
                }
            }


            void clearBoard() {
                gc.setFill( new Color(0, 0.33, 0, 1.0) );
                gc.fillRect(0,0, canvasWidth, canvasHeight);

            }
        }.start();

        //Displaying the contents of the stage
        primaryStage.show();
    }

    private void removeFromPrev() {
        if (!selectedCards.isEmpty()){
            int index = fromPile.getIndex(selectedCards.get(selectedCards.size()-1));
            if (index != -1) {
                for (int j = index; j < fromPile.getNoCards(); j++) {
                    fromPile.getCardList().get(j).setHeld(false);
                    fromPile.pop();
                }
            }
        }
    }
}



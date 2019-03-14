package main;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import jdk.internal.org.objectweb.asm.Handle;
import javafx.scene.canvas.Canvas;
import java.io.FileInputStream;
import java.util.ArrayList;

public class MainVisual extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        // Vertical offset between stacked cards
        final int STACKVOFFSET = 17;
        final int VOFFSET = 30;
        final int HOFFSET = 30;
        final int canvasWidth = 900;
        final int canvasHeight = 500;

        Board blueBoard = new Board(1);
        Board redBoard = new Board(2);

        primaryStage.setTitle("Double Solitaire");

        Group root = new Group();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        Canvas canvas = new Canvas(canvasWidth, canvasHeight);

        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });


        // Main Game Loop
        new AnimationTimer(){
            @Override
            public void handle(long now) {
                gc.setFill(new Color(0, 0.33, 0, 1));
                gc.fillRect(0.0, 0.0, canvasWidth, canvasHeight);

                ArrayList<Image>[] images = blueBoard.display();

                for (Image discardImages: images[0]){
                    gc.drawImage(discardImages, blueBoard.getDiscard().getX(), blueBoard.getDiscard().getY());
                }

                for (int i = 1; i < 5; i++){
                    if (images[i] != null){
                        for (Image foundationImage : images[i]){
                            gc.drawImage(foundationImage, blueBoard.getFoundationPiles()[i-1].getX(), blueBoard.getFoundationPiles()[i-1].getY());

                        }
                    } else if (images[i] == null) {
                        gc.setFill(new Color(0, 0.5, 0, 1));
                        gc.fillRect(blueBoard.getFoundationPiles()[i-1].getX(), blueBoard.getFoundationPiles()[i-1].getY(), Card.WIDTH, Card.HEIGHT);

                    }
                }

                for (int i = 5; i < 12; i++){
                    int counter = 0;
                    for (Image tablePile: images[i]){
                        gc.drawImage(tablePile, blueBoard.getTablePiles()[i-5].getX(), blueBoard.getTablePiles()[i-5].getY()+ (counter)*STACKVOFFSET);
                        counter++;
                    }
                }





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
                gc.setFill( new Color(255, 255, 255, 1.0) );
                gc.fillRect(0,0, canvasWidth, canvasHeight);

            }
        }.start();

        //Displaying the contents of the stage
        primaryStage.show();
    }
}



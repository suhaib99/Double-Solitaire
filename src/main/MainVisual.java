package main;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import jdk.internal.org.objectweb.asm.Handle;
import javafx.scene.canvas.Canvas;
import java.io.FileInputStream;

public class MainVisual extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        // Vertical offset between stacked cards
        final int STACKVOFFSET = 17;
        final int VOFFSET = 70;
        final int HOFFSET = 50;
        final int canvasWidth = 1800;
        final int canvasHeight = 1000;

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
        new AnimationTimer(){
            @Override
            public void handle(long now) {

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



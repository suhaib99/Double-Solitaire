package main;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
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
        final int VOFFSET = 50;
        final int HOFFSET = 50;
        final int canvasWidth = 1800;
        final int canvasHeight = 1000;

        Board gameBoard = new Board();

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

                if (gameBoard.gameOver()){
                    gc.setFill( new Color(255, 255, 255, 1.0) );
                    gc.fillRect(0,0, 512,512);

                    gc.setFill(new Color(0, 0, 0, 1));
                    gc.fillText("GAME OVER", (double)canvasWidth/2, (double)canvasHeight/2);
                }
            }
        }.start();

        //Displaying the contents of the stage
        primaryStage.show();
    }
}



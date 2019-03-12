package main;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class MainVisual extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Double Solitaire");

        Card testCard = new Card(1, 1);

        //Creating a Group object
        Group root = new Group(testCard.displayCard(50, 50));

        //Creating a scene object
        Scene scene = new Scene(root, 1800, 800);

        //Adding scene to the stage
        primaryStage.setScene(scene);

        //Displaying the contents of the stage
        primaryStage.show();
    }
}



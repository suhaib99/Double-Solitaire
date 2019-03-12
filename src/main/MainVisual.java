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


        Image image = new Image(new FileInputStream("Double-Solitaire/src/res/Decks/pngs/diamondAce.png"));

        //Setting the image view
        ImageView imageView = new ImageView(image);

        //Setting the position of the image
        imageView.setX(50);
        imageView.setY(25);

        //setting the fit height and width of the image view
        imageView.setFitHeight(200);
        imageView.setFitWidth(100);

        //Setting the preserve ratio of the image view
        imageView.setPreserveRatio(true);

        //Creating a Group object
        Group root = new Group(imageView);


        //Creating a scene object
        Scene scene = new Scene(root, 1800, 800);


        //Adding scene to the stage
        primaryStage.setScene(scene);

        //Displaying the contents of the stage
        primaryStage.show();
    }
}



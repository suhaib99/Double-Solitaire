package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import static javafx.scene.paint.Color.GREEN;

public class Menu extends Application {

  private BorderPane layout = new BorderPane();
  private Scene scene;

  public static void main(String[] args){
      launch(args);
    }

    public void start(Stage primaryStage) throws Exception
    {

        Pane root = new Pane();
        scene = new Scene(layout,700,700, GREEN);

        Button first;
        first = new Button("New Game");
        Button second = new Button("Load Game");
        Button third = new Button("Quit Game ");

        first.setLayoutX(290);
        first.setLayoutY(250);
        second.setLayoutX(290);
        second.setLayoutY(300);
        third.setLayoutX(290);
        third.setLayoutY(350);
        primaryStage.setTitle("Double_Solitaire");

        root.getChildren().addAll(first,second,third);
        primaryStage.setScene(new Scene(root,700,750));

        primaryStage.show();
    }

}

package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class Menu extends Application {

  private BorderPane layout = new BorderPane();
  private Scene scene;

  public static void main(String[] args){
      launch(args);
    }

    public void start(Stage primaryStage) throws Exception
    {

        Pane root = new Pane();


        Button first;
        first = new Button("New Game");
        Button second = new Button("Load Game");
        Button third = new Button("Quit Game ");

        Image image = new Image(new FileInputStream("src\\res\\Decks\\pngs\\MenuScreen.png"));
        ImageView mv = new ImageView(image);

        Text text = new Text();
        text.setText("DOUBLE SOLITAIRE");
        text.setX(385);
        text.setY(50);
        text.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR,40));
        first.setLayoutX(575);
        first.setLayoutY(250);
        second.setLayoutX(575);
        second.setLayoutY(300);
        third.setLayoutX(575);
        third.setLayoutY(350);
        primaryStage.setTitle("Double_Solitaire");
        root.getChildren().addAll(mv);
        root.getChildren().addAll(first,second,third,text);
        primaryStage.setScene(new Scene(root,1200,630));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}

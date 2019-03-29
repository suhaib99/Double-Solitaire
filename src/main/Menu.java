package main;
/*
import Gui.Solitaire;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class Menu extends Application {

  private BorderPane layout = new BorderPane();
  private Scene scene;

  public static void main(String[] args){
      launch(args);
    }

    public void start(Stage primaryStage) throws Exception
    {
        Pane root = new Pane();

        Button newGame = new Button("New Game");

        newGame.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Solitaire.launch();
            }
        });

        Button loadGame = new Button("Load Game");

        loadGame.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try{
                    BufferedReader reader = new BufferedReader(new FileReader("SavedState.txt"));
                    String[] lines = new String[12];
                    for (int i = 0; i < lines.length; i++){
                        lines[i] = reader.readLine();
                    }




                } catch( IOException e){
                    System.out.println("File Not Found Starting New Game");
                    Solitaire.launch();
                }
            }
        });

        Button quitGame = new Button("Quit Game ");

        Image image = new Image(new FileInputStream("src/res/Decks/pngs/MenuScreen.png"));
        ImageView mv = new ImageView(image);

        Text text = new Text();
        text.setText("DOUBLE SOLITAIRE");
        text.setX(385);
        text.setY(50);
        text.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR,40));
        newGame.setLayoutX(575);
        newGame.setLayoutY(250);
        loadGame.setLayoutX(575);
        loadGame.setLayoutY(300);
        quitGame.setLayoutX(575);
        quitGame.setLayoutY(350);
        primaryStage.setTitle("Double_Solitaire");
        root.getChildren().addAll(mv);
        root.getChildren().addAll(newGame, loadGame, quitGame ,text);
        primaryStage.setScene(new Scene(root,Solitaire.WIDTH,Solitaire.HEIGHT));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}
*/
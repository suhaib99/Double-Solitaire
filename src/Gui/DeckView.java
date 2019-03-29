/**
 *
 * Adapted from code by Martin P. Robillard
 *
 * See: https://github.com/prmr/Solitaire
 */

package Gui;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import main.Board;
import main.GameListeners;
import main.Card;

public class DeckView extends HBox implements GameListeners {

    private static final String BUTTON_STYLE_NORMAL = "-fx-background-color: transparent; -fx-padding: 5,5,5,5;";
    private static final String BUTTON_STYLE_PRESSED = "-fx-background-color: transparent; -fx-padding: 6 4 4 6;";

    private Board gameBoard;

    DeckView(Board gameBoard){
        this.gameBoard = gameBoard;
        final Button deckButton = new Button();
        deckButton.setStyle(BUTTON_STYLE_NORMAL);
        deckButton.setGraphic(new ImageView(new Card(1, 1).getBackInstance(gameBoard.getTeam())));

        deckButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ((Button)(event.getSource())).setStyle(BUTTON_STYLE_PRESSED);
            }
        });

        deckButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ((Button)(event.getSource())).setStyle(BUTTON_STYLE_NORMAL);
                gameBoard.flipThroughDiscard();
            }
        });

        getChildren().add(deckButton);
        gameBoard.addListener(this);
    }


    @Override
    public void gameStateChanged() {
        ((Button)getChildren().get(0)).setGraphic(new ImageView(new Card(1, 1).getBackInstance(gameBoard.getTeam())));
    }
}

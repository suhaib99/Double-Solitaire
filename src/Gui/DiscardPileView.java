/**
 *
 * Adapted from code by Martin P. Robillard
 *
 * See: https://github.com/prmr/Solitaire
 */

package Gui;

import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import main.Board;
import main.GameListeners;
import main.Card;

public class DiscardPileView extends HBox implements GameListeners {
    private static final int PADDING = 5;
    private CardDragHandler dragHandler;
    private Board gameBoard;
    DiscardPileView(Board gameBoard){
        setPadding(new Insets(PADDING));
        this.gameBoard = gameBoard;
        final ImageView imageView = new ImageView(new Card(1, 1).getBackInstance(gameBoard.getTeam()));
        imageView.setVisible(false);
        getChildren().add(imageView);
        dragHandler = new CardDragHandler(imageView, gameBoard.getDiscard());
        imageView.setOnDragDetected(dragHandler);
        gameBoard.addListener(this);
    }

    @Override
    public void gameStateChanged() {
        getChildren().get(0).setVisible(true);
        Card topCard = gameBoard.getDiscard().top();
        ImageView imageView = (ImageView) getChildren().get(0);
        if (!gameBoard.getDiscard().empty()) {
            imageView.setImage(topCard.displayCard(gameBoard.getTeam()));
            dragHandler.setCard(topCard);
        } else {
            imageView.setImage(new Card(1, 1).getBackInstance(gameBoard.getTeam()));
        }
    }

}

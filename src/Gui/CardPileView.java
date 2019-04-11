/**
 *
 * Adapted from code by Martin P. Robillard
 *
 * See: https://github.com/prmr/Solitaire
 */

package Gui;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.StackPane;
import main.CardPile;
import main.Board;
import main.Card;
import main.GameListeners;

public class CardPileView extends StackPane implements GameListeners {
    private static final int Y_OFFSET = 17;
    private static final int PADDING = 5;

    private CardPile cardPile;
    private Board gameBoard;

    CardPileView(CardPile cardPile, Board gameBoard){
        this.cardPile = cardPile;
        this.gameBoard = gameBoard;
        setPadding(new Insets(PADDING));
        setAlignment(Pos.TOP_CENTER);
        buildLayout();
        gameBoard.addListener(this);
    }

    private void buildLayout(){
        getChildren().clear();

        int offset = 0;
        if (cardPile.empty()) {
            // Placeholder image
            ImageView image = new ImageView(new Card(1,1 ).getBackInstance(gameBoard.getTeam()));
            image.setVisible(false);
            getChildren().add(image);
            return;
        }

        for (Card card: cardPile.getCardList()){
            final ImageView image = new ImageView(card.displayCard(gameBoard.getTeam()));
            image.setTranslateY(Y_OFFSET * offset);
            offset++;
            getChildren().add(image);

            setOnDragOver(createDragOverHandler(image, card));
            setOnDragEntered(createDragEnteredHandler(image, card));
            setOnDragExited(createDragExitedHandler(image, card));
            setOnDragDropped(createDragDroppedHandler(image, card));

            if (card.getFaceUp()){
                image.setOnDragDetected(createDragDetectedHandler(image, card));
            }
        }
    }

    private EventHandler<MouseEvent> createDragDetectedHandler(final ImageView imageView, final Card card){
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (gameBoard.getTurn()){
                Dragboard db = imageView.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putString(Transfer.serialize(gameBoard.getSubStack(card, cardPile)));
                db.setContent(content);
                }
                event.consume();
            }
        };
    }

    private EventHandler<DragEvent> createDragOverHandler(final ImageView imageView, final Card card){
        return new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                if (event.getGestureSource() != imageView && event.getDragboard().hasString() && gameBoard.getTurn()){
                    Transfer cardTransfer = new Transfer(event.getDragboard().getString(), gameBoard);
                    Card top = cardTransfer.getTop();
                    if (top != null && cardPile.canAccept(cardTransfer.getTop())){
                        event.acceptTransferModes(TransferMode.MOVE);
                    }
                }
                event.consume();
            }
        };
    }

    private EventHandler<DragEvent> createDragEnteredHandler(final ImageView image, final Card card){
        return new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                if (gameBoard.getTurn()){
                Transfer cardTransfer = new Transfer(event.getDragboard().getString(), gameBoard);
                Card top = cardTransfer.getTop();
                if (top != null && cardPile.canAccept(cardTransfer.getTop())){
                    image.setEffect(new DropShadow());
                }
                }
                event.consume();
            }
        };
    }

    private EventHandler<DragEvent> createDragExitedHandler(final ImageView image, final Card card){
        return new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                if (gameBoard.getTurn())
                    image.setEffect(null);
                event.consume();
            }
        };
    }

    private EventHandler<DragEvent> createDragDroppedHandler(final ImageView image, final Card card){
        return new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
               if (gameBoard.getTurn()) {
                   Dragboard db = event.getDragboard();
                   boolean success = false;
                   if (db.hasString()) {
                       Transfer transfer = new Transfer(db.getString(), gameBoard);
                       Card top = transfer.getTop();
                       if (top != null && cardPile.canAccept(top)) {
                           gameBoard.move(transfer.getOrigin(), transfer.getTop(), cardPile);
                       }
                       success = true;
                   }

                   event.setDropCompleted(success);
               }
                event.consume();

            }
        };
    }

    public void gameStateChanged(){
        buildLayout();
    }
}

/**
 *
 * Adapted from code by Martin P. Robillard
 *
 * See: https://github.com/prmr/Solitaire
 */

package Gui;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import main.Board;
import main.Card;
import main.FoundationPile;
import main.GameListeners;

public class FoundationPileView extends StackPane implements GameListeners {
    private static final int PADDING = 5;

    private static final String BORDER_STYLE = "-fx-border-color: lightgray;"
            + "-fx-border-width: 3;" + " -fx-border-radius: 10.0";
    private static final String BORDER_STYLE_DRAGGED = "-fx-border-color: darkgray;"
            + "-fx-border-width: 3;" + " -fx-border-radius: 10.0";
    private static final String BORDER_STYLE_NORMAL = "-fx-border-color: lightgray;"
            + "-fx-border-width: 3;" + " -fx-border-radius: 10.0";

    private CardDragHandler dragHandler;
    private Board gameBoard;
    private FoundationPile foundationPile;

    FoundationPileView(FoundationPile foundationPile, Board gameBoard){
        this.gameBoard = gameBoard;
        this.foundationPile = foundationPile;
        setPadding(new Insets(PADDING));
        setStyle(BORDER_STYLE);
        final ImageView imageView = new ImageView(new Card(1, 1).getBackInstance(gameBoard.getTeam()));
        imageView.setVisible(false);
        getChildren().add(imageView);
        this.dragHandler = new CardDragHandler(imageView, foundationPile);
        imageView.setOnDragDetected(dragHandler);
        setOnDragOver(createOnDragOverHandler(imageView));
        setOnDragEntered(createOnDragEnteredHandler());
        setOnDragExited(createOnDragExitedHandler());
        setOnDragDropped(createOnDragDroppedHandler());
        gameBoard.addListener(this);


    }

    @Override
    public void gameStateChanged() {
        if (this.foundationPile.empty()){
            getChildren().get(0).setVisible(false);
        } else {
            getChildren().get(0).setVisible(true);
            Card top = foundationPile.top();
            ImageView image = (ImageView) getChildren().get(0);
            image.setImage(top.displayCard(gameBoard.getTeam()));
            dragHandler.setCard(top);
        }
    }

    private EventHandler<DragEvent> createOnDragExitedHandler(){
        return new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                setStyle(BORDER_STYLE_NORMAL);
                event.consume();
            }
        };
    }

    private EventHandler<DragEvent> createOnDragEnteredHandler(){
        return new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Transfer transfer = new Transfer(event.getDragboard().getString(), gameBoard);
                if (transfer.size()== 1 && foundationPile.canAccept(transfer.getTop())){
                    setStyle(BORDER_STYLE_DRAGGED);
                }
                event.consume();
            }
        };
    }
    private EventHandler<DragEvent> createOnDragDroppedHandler(){
        return new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasString()){
                    Transfer transfer = new Transfer(event.getDragboard().getString(), gameBoard);
                    if (transfer.size() == 1 && foundationPile.canAccept(transfer.getTop())) {
                        gameBoard.move(transfer.getOrigin(), transfer.getTop(), foundationPile);
                    }
                    success = true;
                }

                event.setDropCompleted(success);
                event.consume();

            }
        };
    }
    private EventHandler<DragEvent> createOnDragOverHandler(ImageView imageView){
        return new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                if (event.getGestureSource() != imageView && event.getDragboard().hasString()){
                    Transfer transfer = new Transfer(event.getDragboard().getString(), gameBoard);
                    if (transfer.size() == 1 && foundationPile.canAccept(transfer.getTop())){
                        event.acceptTransferModes(TransferMode.MOVE);
                    }
                    event.consume();
                }
            }
        };
    }
}

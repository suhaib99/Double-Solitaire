package Gui;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import main.Card;

public class CardDragHandler implements EventHandler<MouseEvent> {
    private static final ClipboardContent CLIPBOARD_CONTENT= new ClipboardContent();

    private Card card;
    private ImageView imageView;

    CardDragHandler(ImageView image){
        imageView = image;
    }

    void setCard(Card card){
        this.card = card;
    }

    @Override
    public void handle(MouseEvent event){
        Dragboard db = imageView.startDragAndDrop(TransferMode.ANY);
        CLIPBOARD_CONTENT.putString(card.toString());
        db.setContent(CLIPBOARD_CONTENT);
        event.consume();
    }
}

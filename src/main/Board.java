package main;

import javax.swing.text.html.ImageView;
import javafx.scene.image.Image;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;

public class Board {

    protected DeckPile deck = new DeckPile();

    protected TablePile[] tablePiles;
    protected FoundationPile[] foundationPiles;

    public int team;


    protected DiscardPile discard = new DiscardPile();

    Board(int team) {

        int VOFFSET = 30;
        int HOFFSET = 30;
        int cardWidth = Card.WIDTH;
        int cardHeight = Card.HEIGHT;

        tablePiles = new TablePile[7];

        this.team = team;

        for (int i = 0; i < 7; i++) {
            int j = i + 1;

            tablePiles[i] = new TablePile(j, deck);

            tablePiles[i].setX(HOFFSET + i * (HOFFSET + cardWidth));
            tablePiles[i].setY(2 * VOFFSET + cardHeight);

        }

        foundationPiles = new FoundationPile[4];

        for (int i = 0; i < 4; i++) {
            foundationPiles[i] = new FoundationPile();

            foundationPiles[i].setX(4 * HOFFSET + 3 * cardWidth + i * (cardWidth+HOFFSET));
            foundationPiles[i].setY(VOFFSET);
        }

        for (Card card : deck.getCardList()) {
            discard.addCard(card);

            discard.setX(HOFFSET);
            discard.setY(VOFFSET);
        }
    }

    void move(CardPile pileFrom, CardPile pileTo) {
        if (pileTo.canAccept(pileFrom.top())) {
            pileTo.addCard(pileFrom.pop());
        }
    }

    void flipThroughDiscard() {
        discard.flipThrough();
    }

    boolean gameOver() {
        if (!discard.empty())
            return false;

        for (int i = 1; i <= 7; i++) {
            if (!tablePiles[i - 1].empty()) {
                return false;
            }
        }
        return true;
    }

    public DiscardPile getDiscard() {
        return discard;
    }

    public FoundationPile[] getFoundationPiles() {
        return foundationPiles;
    }

    public TablePile[] getTablePiles() {
        return tablePiles;
    }

    public int getTeam() {
        return team;
    }

    /**
     * discard at [0]
     * foundation at [1-4]
     * table piles at [5-11]
     * @return Image[]: array of images arraylists to display
     */
    ArrayList<Image>[] display(){
        ArrayList<Image>[] images = new ArrayList[12];

        images[0] = discard.display(this.team);

        for (int i = 1; i < 5; i++){
            if (!foundationPiles[i-1].empty())
                images[i] = foundationPiles[i-1].display(this.team);
            else{
                images[i] = null;
            }
        }

        for (int i = 5; i < 12; i++){
            images[i] = tablePiles[i-5].display(this.team);
        }
        return images;

        /*
        String[] foundationLetters = new String[4];
        for (int i = 0; i < foundationPiles.length; i++) {
            if (!foundationPiles[i].empty())
                foundationLetters[i] = foundationPiles[i].top().getName();
        }

        for (int i = 0; i < foundationLetters.length; i++){
            if (foundationLetters[i] == null){
                foundationLetters[i] = "[]";
            }
        }

        System.out.println("D\tF1 F2 F3 F4\n" + discard.top().getName() + "\t" + foundationLetters[0]
                + " " + foundationLetters[1] + foundationLetters[2]
                + " " + foundationLetters[3]
                + "\n"+ discard.top().getSuitName() +"\n-----------------\n\t1      2      3      4      5      6      7\n\t"
                + tablePiles[0].top().getName() + "      " + tablePiles[1].top().getName()
                + "       " + tablePiles[2].top().getName() + "      " + tablePiles[3].top().getName()
                + "      " +
                tablePiles[4].top().getName() + "       " + tablePiles[5].top().getName() + "      " +
                tablePiles[6].top().getName() + "\n\t" + tablePiles[0].top().getSuitName() + " " +
                tablePiles[1].top().getSuitName()+ " " + tablePiles[2].top().getSuitName() + " " +
                tablePiles[3].top().getSuitName() + " " + tablePiles[4].top().getSuitName() + " " +
                tablePiles[5].top().getSuitName() + " " + tablePiles[6].top().getSuitName() + "\n");
       */
    }

    public static void main(String[] args){
        Board testBoard = new Board(1);

        System.out.println(testBoard.deck.getCardList());
        System.out.println(testBoard.tablePiles[0].top().getSuit());
        System.out.println(testBoard.discard.getCardList());
        System.out.println(testBoard.tablePiles[4].getCardList());
        System.out.println(testBoard.tablePiles[6].getCardList());

    }

}

/**
 *
 * Adapted from code by Martin P. Robillard
 *
 * See: https://github.com/prmr/Solitaire
 */

package Gui;

import main.Board;
import main.Card;
import main.CardPile;

import java.util.ArrayList;

public class Transfer {

    public static final String SEPARATOR = ";";

    private CardPile origin;

    private Card[] cards;

    /**
     * Creates transfer from serialized string
     * @param sString: serialized string
     */
    Transfer(String sString, Board board){
        assert sString != null && sString.length() > 0;
        String[] values = sString.split(SEPARATOR);
        origin = board.getCardPile(values[0]);

        cards = new Card[values.length-1];
        for (int i = 1; i < values.length; i++){
            cards[i-1] = board.getCard(values[i]);
        }
        assert cards.length > 0;
        assert origin != null;

    }

    public static String serialize(CardPile pile){
        String result = "";
        ArrayList<Card> cards = pile.getCardList();

        result += pile.toString() + SEPARATOR;

        for (Card card: cards) {
            result += card.toString() + SEPARATOR;
        }

        // Remove last SEPARATOR
        if (result.length() > 0){
            result = result.substring(0, result.length()-1);
        }

        return result;
    }

    public Card[] getArray(){
        return cards;
    }

    public CardPile getOrigin() {
        return origin;
    }

    public Card getTop(){
        return cards[0];
    }

    public int size(){
        return cards.length;
    }
}

package main;

import java.util.ArrayList;
import java.util.Collections;

public class DeckPile extends CardPile {

    DeckPile() {
        CardPile deckPile = new CardPile();
        CardPile shuffledPile = new CardPile();

        for (int i = 1; i <= 13; i++) {
            for (int j = 1; j <= 4; j++) {
                deckPile.addCard(new Card(i, j));
            }
        }

        ArrayList<Card> shuffledList = deckPile.getCardList();
        Collections.shuffle(shuffledList);

        for (Card card:shuffledList){
            shuffledPile.addCard(card);
        }
    }
}

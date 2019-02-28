package main;

import java.util.ArrayList;

public class TablePile extends CardPile {


    TablePile(int numCards, DeckPile deck){
        ArrayList<Card> cards = deck.select(numCards);
        for (Card card: cards){
            addCard(card);
        }
    }
}

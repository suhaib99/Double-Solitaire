package main;

import java.util.ArrayList;
import java.util.Collections;

public class DeckPile extends CardPile {

    private CardPile deckPile = new CardPile();
    private CardPile shuffledPile = new CardPile();

    DeckPile() {

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



    @Override
    ArrayList<Card> getCardList(){
        return shuffledPile.getCardList();
    }

    @Override
    void select(int start, int end){


    }

    @Override
    int getNoCards(){
        return this.getCardList().size();
    }

    public static void main(String[] args){
        DeckPile testDeck = new DeckPile();
        System.out.println(testDeck.getNoCards());

    }
}

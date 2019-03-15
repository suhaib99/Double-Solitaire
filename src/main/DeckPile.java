package main;

import java.util.ArrayList;
import java.util.Collections;

public class DeckPile extends CardPile {

    private CardPile startPile = new CardPile();
    private CardPile shuffledPile = new CardPile();

    DeckPile() {
        for (int i = 1; i <= 13; i++) {
            for (int j = 1; j <= 4; j++) {
                startPile.addCard(new Card(j, i));
            }
        }
        ArrayList<Card> shuffledList = startPile.getCardList();
        Collections.shuffle(shuffledList);

        shuffledPile.addCard(shuffledList);
    }



    @Override
    ArrayList<Card> getCardList(){
        return shuffledPile.getCardList();
    }

    @Override
    Card top(){
        return shuffledPile.top();
    }

    @Override
    Card pop(){
        return shuffledPile.pop();
    }

    @Override
    ArrayList<Card> select(int end){
        ArrayList<Card> temp_list = new ArrayList<>();
        for (int i = 0; i < end; i++) {
            temp_list.add(shuffledPile.pop());

        }

        Collections.reverse(temp_list);

        return temp_list;
    }

    @Override
    int getNoCards(){
        return shuffledPile.getCardList().size();
    }

    public static void main(String[] args){
        DeckPile testDeck = new DeckPile();
        System.out.println(testDeck.getCardList());

    }
}

package main;

import java.util.ArrayList;

public class CardPile {
    private ArrayList<Card> cardList = new ArrayList<>();

    ArrayList<Card> getCardList(){
        return new ArrayList<>(cardList);
    }

    boolean empty(){
        return cardList.isEmpty();
    }

    Card top(){
        return cardList.get(cardList.size()-1);
    }

    Card pop(){
        Card return_val = this.top();
        cardList.remove(cardList.size()-1);
        return return_val;
    }

    void select(int start, int end){
        //do nothing
    }

    void addCard(Card card){
        cardList.add(card);
    }

    int getNoCards(){
        return cardList.size();
    }

}
